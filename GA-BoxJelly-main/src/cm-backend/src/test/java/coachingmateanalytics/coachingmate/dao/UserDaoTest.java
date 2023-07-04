package coachingmateanalytics.coachingmate.dao;

import coachingmateanalytics.coachingmate.AppTest;
import coachingmateanalytics.coachingmate.common.utils.Consts;
import coachingmateanalytics.coachingmate.entity.UserPartner;
import com.mongodb.client.result.UpdateResult;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserDaoTest extends AppTest {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private MongoTemplate mongoTemplate;
    private UserPartner testUser;
    @BeforeEach
    public void setUp() {
        // create a test user
        testUser = new UserPartner();
        testUser.setUsername("testUser");
        testUser.setEmail("testUser@gmail.com");
        testUser.setPassword("password");
        testUser.setToken("token");
        testUser.setTokenDate(new Date());
        testUser.setUserAccessToken("userAccessToken");
        testUser.setUserAccessSecret("userAccessSecret");
    }
    @Test
    public void testSaveUser() throws Exception {
        setUp();
        userDao.saveUser(testUser);
        Query query = new Query(Criteria.where("username").is(testUser.getUsername()));
        UserPartner expectedUser = mongoTemplate.findOne(query, UserPartner.class, Consts.MONGODB_USER_COLLECTIN_NAME);

        assertEquals(expectedUser.toString(),testUser.toString());
        //After test delete the user in database
        userDao.deleteUserByUsername(testUser.getUsername());
    }


    @Test
    public void findUserByUsername(){

        setUp();
        userDao.saveUser(testUser);
        UserPartner userFromMongoDB = userDao.findUserByUsername(testUser.getUsername());
        assertEquals(testUser.toString(),userFromMongoDB.toString());

        // delete the test user from mongodb
        userDao.deleteUserByUsername(testUser.getUsername());
    }

    @Test
    public void findUserByEmail(){
        setUp();
        userDao.saveUser(testUser);
        UserPartner userFromMongoDB = userDao.findUserByEmail(testUser.getEmail());
        assertEquals(testUser.toString(),userFromMongoDB.toString());

        // delete the test user from mongodb
        userDao.deleteUserByUsername(testUser.getUsername());
    }


    @Test
    public void updateUser(){
        setUp();
        // save test user to mongodb
        userDao.saveUser(testUser);

        // update test user in mongodb
        testUser.setUserAccessToken("new access token");
        testUser.setUserAccessSecret("new access token secret");
        int updateCount= userDao.updateUser(testUser);
        // check if the update is successful
        assertEquals(1, updateCount);

        // find the test user from mongodb
        UserPartner userFromMongoDB = userDao.findUserByUsername(testUser.getUsername());

        // check if the update is successful
        assertEquals(testUser.getUserAccessToken(), userFromMongoDB.getUserAccessToken());
        assertEquals(testUser.getUserAccessSecret(), userFromMongoDB.getUserAccessSecret());
        // delete the test user from mongodb
        userDao.deleteUserByUsername(testUser.getUsername());
    }


    @Test
    public void deleteUserByUsername(){
        setUp();
        userDao.saveUser(testUser);
        Query query = new Query(Criteria.where("username").is(testUser.getUsername()));
        UserPartner userFromMongoDB = mongoTemplate.findOne(query, UserPartner.class, Consts.MONGODB_USER_COLLECTIN_NAME);
        //prove user has been inserted
        assertEquals(testUser.toString(),userFromMongoDB.toString());
        userDao.deleteUserByUsername(testUser.getUsername());
        userFromMongoDB = mongoTemplate.findOne(query, UserPartner.class, Consts.MONGODB_USER_COLLECTIN_NAME);

        Assertions.assertNull(userFromMongoDB);
    }

    @Test
    public void testSelectUserByToken(){
        setUp();
        userDao.saveUser(testUser);
        UserPartner userFromMongoDB=userDao.selectUserByToken(testUser.getToken());
        assertEquals(testUser.toString(),userFromMongoDB.toString());
        userDao.deleteUserByUsername(testUser.getUsername());
    }

}
