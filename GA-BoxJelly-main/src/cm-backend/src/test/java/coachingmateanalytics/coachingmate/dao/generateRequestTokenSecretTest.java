package coachingmateanalytics.coachingmate.dao;

import coachingmateanalytics.coachingmate.common.utils.Consts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class generateRequestTokenSecretTest {

    @Autowired
    TokenProviderDao tokenProviderDao;
    @Value("${requestToken.url}")
    private String requestTokenUrl;
    @Test
    public void testGenerateRequestTokenSecret(){
        String tokenAndSecret=tokenProviderDao.generateRequestTokenSecret(requestTokenUrl);
        Assertions.assertTrue(tokenAndSecret.contains(Consts.OAUTH_TOKEN));
    }
}
