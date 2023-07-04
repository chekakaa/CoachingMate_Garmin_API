package coachingmateanalytics.coachingmate.service;

import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * OAuthConfiguration class to handle OAuth configuration and RestTemplate setup.
 */
@Service
public class OauthConfiguration {

    @Value("${oauth.consumerkey}")
    private String consumerKey;

    @Value("${oauth.consumerSecret}")
    private String consumerSecret;

    private ThreadLocal<String> userAccessToken = new ThreadLocal<>();
    private ThreadLocal<String> userAccessSecret = new ThreadLocal<>();

    private RestTemplate signedRequestTemplate;
    private HttpEntity<String> httpEntity;

    @PostConstruct
    public void init() {
        signedRequestTemplate = new RestTemplateBuilder().additionalInterceptors(
                (ClientHttpRequestInterceptor) (request, body, execution) -> {
                    DefaultOAuthConsumer consumer = new DefaultOAuthConsumer(consumerKey, consumerSecret);

                    // Sign with user access token and secret if available, otherwise sign with consumer credentials
                    if (userAccessToken.get() != null && userAccessSecret.get() != null) {
                        consumer.setTokenWithSecret(userAccessToken.get(), userAccessSecret.get());
                    }

                    try {
                        consumer.sign(new HttpRequestAdapter(request, body));
                    } catch (OAuthMessageSignerException | OAuthExpectationFailedException
                            | OAuthCommunicationException e) {
                        throw new RuntimeException("Unable to sign request with consumer_key=" + consumerKey, e);
                    }
                    return execution.execute(request, body);
                }).build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        httpEntity = new HttpEntity<>(headers);
    }

    public RestTemplate getRestTemplate() {
        return getRestTemplate(null, null);
    }

    public RestTemplate getRestTemplate(String userAccessToken, String userAccessSecret) {
        this.userAccessToken.set(userAccessToken);
        this.userAccessSecret.set(userAccessSecret);

        return signedRequestTemplate;
    }

    public HttpEntity<String> httpEntity() {
        return httpEntity;
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public void setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }

    public ThreadLocal<String> getUserAccessToken() {
        return userAccessToken;
    }

    public void setUserAccessToken(ThreadLocal<String> userAccessToken) {
        this.userAccessToken = userAccessToken;
    }

    public ThreadLocal<String> getUserAccessSecret() {
        return userAccessSecret;
    }

    public void setUserAccessSecret(ThreadLocal<String> userAccessSecret) {
        this.userAccessSecret = userAccessSecret;
    }

}
