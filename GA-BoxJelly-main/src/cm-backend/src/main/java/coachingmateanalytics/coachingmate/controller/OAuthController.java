package coachingmateanalytics.coachingmate.controller;

import coachingmateanalytics.coachingmate.dao.UserDao;
import coachingmateanalytics.coachingmate.entity.RequestToken;
import coachingmateanalytics.coachingmate.entity.UserPartner;
import coachingmateanalytics.coachingmate.service.OAuthService;
import coachingmateanalytics.coachingmate.common.utils.Consts;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * OAuthController handles the core OAuth process.
 */
@Controller
@RequestMapping("/auth")
public class OAuthController {
    private static final Logger logger = LoggerFactory.getLogger(OAuthController.class);

    @Autowired
    OAuthService oauthService;

    @Autowired
    UserDao userDao;

    @Value("${requestToken.url}")
    private String requestTokenUrl;

    @Value("${oauthAccessToken.url}")
    private String oauthAccessTokenUrl;

    /**
     * Initiates the request_token portion of the 3-legged OAuth process, then redirects to the oauthConfirm step
     * for the user to enter their Garmin Connect username and password.
     *
     * @param username User's username
     * @return A ResponseEntity with the manual oauthConfirm page URL
     */
    @RequestMapping("/requestToken")
    @ApiOperation(value = "oauth RequestToken", notes = "Acquire Unauthorized Request Token and Token Secret")
    public ResponseEntity<Map<String, String>> oauthRequestToken(@ApiParam(required = true, type = "String") String username) {
        logger.debug("user " + username + " request token");
        RequestToken reqToken = oauthService.getRequestToken(requestTokenUrl, username);
        String result = "";
        if (reqToken != null) {
            result = oauthService.getOAuthConfirmURL(reqToken.getToken());
            logger.debug("the redirect url : " + result);
        }
        Map<String, String> map = new HashMap<>();
        map.put("url", result);
        return ResponseEntity.accepted().body(map);
    }

    /**
     * Third OAuth leg. Provides the OAuth token and the verifier value from the first two steps
     * to generate a user access token associated with the user.
     *
     * @param oauthToken         OAuth token
     * @param oauthVerifierValue OAuth verifier value
     * @return String indicating authorization success
     */
    @GetMapping("/accessToken")
    @ApiOperation(value = "callback url", notes = "this is used to receive the request from garmin connect")
    public String oauthAccessToken(@ApiParam(required = true, type = "String")
                                    @RequestParam(value = "oauth_token") String oauthToken,
                                    @ApiParam(required = true, type = "String")
                                    @RequestParam(value = "oauth_verifier") String oauthVerifierValue) {
        if (oauthVerifierValue != null && !oauthVerifierValue.isEmpty()) {
            String accessTokenUrl = oauthAccessTokenUrl + Consts.URL_DELIMTER + Consts.OAUTH_VERIFIER + Consts.VALUE_DELIMTER + oauthVerifierValue;
            oauthService.generateAccessToken(accessTokenUrl, oauthToken);

        }

        return "authoriseSuccess";
    }
    /**
     * Generates a user access token if it doesn't exist, or displays user data if it does.
     *
     * @param username User's username
     * @return User's username or redirection URL
     */
    @RequestMapping(value = "/userAccessToken", method = RequestMethod.POST)
    @ApiOperation(value = "generate Access Token", notes = "when user first invoke this url, it will authorise" +
            "the user, when this user has been authorised, it just needs to get the token from mongodb")
    // method generates an access token for the user if it doesn't exist and displays the user data if it does. 
    // If the user has been authorized, the method only needs to get the token from the database (MongoDB).
    public String generateToken(@ApiParam(required = true, type = "String") @RequestParam String username) {
        UserPartner userPartner = userDao.findUserByUsername(username);
        if (userPartner.getUserAccessToken() != null) {
            return userPartner.getUsername();
        } else {
            return "redirect:/auth/requestToken/" + username;
        }
    }

}