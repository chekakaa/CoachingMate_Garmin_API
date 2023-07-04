//package coachingmateanalytics.coachingmate.controller;
//
//import coachingmateanalytics.coachingmate.dao.UserDao;
//import coachingmateanalytics.coachingmate.dao.UserDaoImpl;
//import coachingmateanalytics.coachingmate.entity.UserPartner;
//import coachingmateanalytics.coachingmate.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.http.ResponseEntity;
//
//import javax.annotation.Resource;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//public class RegistryControllerTest {
//
//    @Autowired
//    private RegistryController registryController;
//
//    @Resource
//    UserService userService;
//    @Resource
//    UserDao userDao;
//
//
//    @Test
//    public void testRegister(){
//
//        String fullName="testCase";
//        String userName="testYyh";
//        String password="testYyhPassword";
//        String email="testYyh@gmail.com";
//
//        if (userDao.findUserByUsername(userName)!=null){
//            userDao.deleteUserByUsername(userName);
//        }
//        // actual result
//        ResponseEntity<UserPartner> responseEntityRegister=registryController.register(fullName,userName,password,email);
//        // expected result
//        UserPartner userPartner=userService.loginCheck(userName, password);
//        assertEquals(ResponseEntity.ok(userPartner).toString(),responseEntityRegister.toString());
//
//
//    }
//
//}
