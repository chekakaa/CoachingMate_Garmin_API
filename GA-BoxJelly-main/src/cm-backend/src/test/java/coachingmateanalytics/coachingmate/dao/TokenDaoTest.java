package coachingmateanalytics.coachingmate.dao;

import coachingmateanalytics.coachingmate.common.utils.Consts;
import coachingmateanalytics.coachingmate.entity.DashboardStastic;
import coachingmateanalytics.coachingmate.entity.RequestToken;
import coachingmateanalytics.coachingmate.entity.UserPartner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TokenDaoTest {

    @Autowired
    TokenDao tokenDao;
    @Autowired
    UserDao userDao;
    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void testSaveRequestToken(){
        RequestToken reqToken = new RequestToken("testUser", 123, "testToken", "testSecret", "Not Connected");
        tokenDao.saveRequestToken(reqToken.getUsername(), reqToken.getUserId(), reqToken.getToken(), reqToken.getSecret(), reqToken.getGarminstatus());
        Query query = new Query(Criteria.where("token").is(reqToken.getToken()));
        RequestToken foundToken= mongoTemplate.findOne(query, RequestToken.class, Consts.MONGODB_TOKEN_COLLECTIN_NAME);
        assertEquals(reqToken.toString(),foundToken.toString());
        mongoTemplate.remove(query, DashboardStastic.class, Consts.MONGODB_TOKEN_COLLECTIN_NAME); // delete Reqtoken by token in mongodb

    }
    @Test
    public void testFindByToken(){
        RequestToken reqToken = new RequestToken("testUser", 123, "testToken", "testSecret", "Not Connected");
        tokenDao.saveRequestToken(reqToken.getUsername(), reqToken.getUserId(), reqToken.getToken(), reqToken.getSecret(), reqToken.getGarminstatus());

        // find the saved token by its token
        RequestToken foundToken = tokenDao.findByToken("testToken");
        assertEquals(reqToken.toString(),foundToken.toString());
        Query query = new Query(Criteria.where("token").is(reqToken.getToken()));
        mongoTemplate.remove(query, DashboardStastic.class, Consts.MONGODB_TOKEN_COLLECTIN_NAME); // delete Reqtoken by token in mongodb

    }
    @Test
    public void testSaveAccessToken(){

        UserPartner testUser = new UserPartner();
        testUser.setUsername("testUser");
        testUser.setEmail("testUser@gmail.com");
        testUser.setPassword("password");
        testUser.setToken("token");
        testUser.setTokenDate(new Date());
        userDao.saveUser(testUser);
        //
        Assertions.assertNull(testUser.getUserAccessToken());
        Assertions.assertNull(testUser.getUserAccessSecret());

        tokenDao.saveAccessToken("testAccessToken", "testAccessSecret", "testUser");

        // find the user partner by its username
        UserPartner testUserActual=userDao.findUserByUsername(testUser.getUsername());

        assertEquals("testAccessToken", testUserActual.getUserAccessToken());
        assertEquals("testAccessSecret", testUserActual.getUserAccessSecret());
        userDao.deleteUserByUsername(testUser.getUsername());

    }
    @Test
    public void testDeleteAccessToken(){
        UserPartner testUser = new UserPartner();
        testUser.setUsername("testUser");
        testUser.setEmail("testUser@gmail.com");
        testUser.setPassword("password");
        testUser.setToken("token");
        testUser.setTokenDate(new Date());
        testUser.setUserAccessToken("userAccessToken");
        testUser.setUserAccessSecret("userAccessSecret");
        userDao.saveUser(testUser);

        assertEquals("userAccessToken", testUser.getUserAccessToken());

        tokenDao.deleteAccessToken(testUser.getUsername());

        Assertions.assertNull(testUser.getUserAccessToken());
        Assertions.assertNull(testUser.getUserAccessSecret());

        userDao.deleteUserByUsername(testUser.getUsername());
    }
}
