package coachingmateanalytics.coachingmate.dao;

import coachingmateanalytics.coachingmate.entity.RequestToken;
import coachingmateanalytics.coachingmate.service.OauthConfiguration;
import coachingmateanalytics.coachingmate.common.utils.Consts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Token provider data access object for getting access token and secret
 * from Garmin API and saving it to MongoDB in the requestToken collection.
 */
@Component
public class TokenProviderDao {
    private static final Logger logger = LoggerFactory.getLogger(TokenProviderDao.class); // Logger for TokenProviderDao

    @Autowired
    OauthConfiguration oauthConfig;

    @Autowired
    TokenDao tokenDao;

    /**
     * Generates a request token secret from the provided URL.
     *
     * @param url the URL to generate the request token secret
     * @return the request token and secret
     */
    public String generateRequestTokenSecret(String url) {
        String tokenAndSecret = null;
        try { // Try to generate request token secret
            RestTemplate restTemplate = oauthConfig.getRestTemplate(); // Get restTemplate from oauthConfig
            ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, oauthConfig.httpEntity(), String.class); // Exchange request token secret
            tokenAndSecret = result.getBody().toString(); // Get request token secret
            logger.info("value in tokenSecret is: {}", tokenAndSecret); // Log request token secret
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return tokenAndSecret;
    }

    /**
     * Generates an access token secret from the provided URL and OAuth token.
     *
     * @param url        the URL to generate the access token secret
     * @param oauthToken the OAuth token
     * @return the access token secret and username
     */
    public String generateAccessTokenSecret(String url, String oauthToken) {
        String accessTokenSecret = null;
        RequestToken reqToken = tokenDao.findByToken(oauthToken);
        try { // Try to get access token secret from URL with oauthToken as parameter
            RestTemplate restTemplate = oauthConfig.getRestTemplate(oauthToken, reqToken.getSecret()); // Get restTemplate with oauthToken and secret
            ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.POST, oauthConfig.httpEntity(), String.class); // Exchange URL with POST method and httpEntity with String as response type
            accessTokenSecret = result.getBody().toString(); // Get access token secret from URL
            logger.info("value in accessTokenSecret is: {}", accessTokenSecret); // Log access token secret
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return accessTokenSecret + Consts.VARIABLE_DELIMTER + reqToken.getUsername(); // Return access token secret and username
    }
}
