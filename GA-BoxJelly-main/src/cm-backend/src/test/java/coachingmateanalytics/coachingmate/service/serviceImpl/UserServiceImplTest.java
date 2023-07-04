package coachingmateanalytics.coachingmate.service.serviceImpl;

import coachingmateanalytics.coachingmate.CoachingmateApplication;
import coachingmateanalytics.coachingmate.dao.UserDaoImpl;
import coachingmateanalytics.coachingmate.entity.UserPartner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CoachingmateApplication.class)
class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserDaoImpl userDao;

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

    }
    @Test
    void testLoginCheck() {
        setUp();
        UserPartner userPartner = userService.loginCheck(testUser.getUsername(), testUser.getPassword());
        Assertions.assertNull(userPartner);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptionPassword=passwordEncoder.encode(testUser.getPassword());
        userService.register(testUser.getFullname(),testUser.getUsername(),encryptionPassword,testUser.getEmail());
        userPartner = userService.loginCheck(testUser.getUsername(), testUser.getPassword());
        assertEquals(testUser.getUsername(),userPartner.getUsername());
        assertEquals(encryptionPassword,userPartner.getPassword());
        assertEquals(testUser.getEmail(),userPartner.getEmail());
        userDao.deleteUserByUsername(testUser.getUsername());
    }

    @Test
    void testGetUser() {
        setUp();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptionPassword=passwordEncoder.encode(testUser.getPassword());
        userService.register(testUser.getFullname(),testUser.getUsername(),encryptionPassword,testUser.getEmail());

        UserPartner userPartner=userService.getUser(testUser.getUsername());
        assertEquals(testUser.getUsername(),userPartner.getUsername());
        assertEquals(encryptionPassword,userPartner.getPassword());
        assertEquals(testUser.getEmail(),userPartner.getEmail());
        userDao.deleteUserByUsername(testUser.getUsername());

    }



    @Test
    void testGetUserByToken() {
        setUp();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptionPassword=passwordEncoder.encode(testUser.getPassword());
        userService.register(testUser.getFullname(),testUser.getUsername(),encryptionPassword,testUser.getEmail());
        userDao.updateUser(testUser); // assign our assumed token

        UserPartner userPartner=userService.getUserByToken(testUser.getToken());
        assertEquals(testUser.getUsername(),userPartner.getUsername());
        assertEquals(encryptionPassword,userPartner.getPassword());
        assertEquals(testUser.getEmail(),userPartner.getEmail());
        userDao.deleteUserByUsername(testUser.getUsername());
    }



    @Test
    void testRegister() {
        setUp();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptionPassword=passwordEncoder.encode(testUser.getPassword());
        userService.register(testUser.getFullname(),testUser.getUsername(),encryptionPassword,testUser.getEmail());

        UserPartner userPartner = userService.loginCheck(testUser.getUsername(), testUser.getPassword());

        assertEquals(testUser.getUsername(),userPartner.getUsername());
        assertEquals(encryptionPassword,userPartner.getPassword());
        assertEquals(testUser.getEmail(),userPartner.getEmail());
        userDao.deleteUserByUsername(testUser.getUsername());

    }
}