//package coachingmateanalytics.coachingmate.controller;
//
//import coachingmateanalytics.coachingmate.common.utils.Consts;
//import coachingmateanalytics.coachingmate.entity.Activity;
//import coachingmateanalytics.coachingmate.entity.UserPartner;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.SplittableRandom;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//@SpringBootTest
//public class LoginControllerTest {
//
//    @Autowired
//    LoginController loginController;
//@Autowired
//MongoTemplate mongoTemplate;
//
//    @Test
//    void testLogin(){
//        String username = "0424";
//        String password = "AAaa11..";
//        String jsonUser = "{\n" +
//                "    \"userId\" : \"898329415\",\n" +
//                "    \"username\" : \"0424\",\n" +
//                "    \"fullname\" : \"0424\",\n" +
//                "    \"email\" : \"cxvjier@gmail.com\",\n" +
//                "    \"password\" : \"$2a$10$mxeHz85HLm5eBUU.9T/6ZeTyMsi4a7CyvMSM9C2hXGe/A0BS3686e\",\n" +
//                "    \"token\" : \"523ed521-7e7d-48ed-a08d-28fb12e757b8\",\n" +
//                "    \"tokenDate\" : \"2023-04-24T04:00:37.361+0000\",\n" +
//                "    \"userAccessSecret\" : \"gHRxkZnGWB6WQKXYl15pMoG4e2uDkr65cYe\",\n" +
//                "    \"userAccessToken\" : \"2d7c25b2-27f5-4974-a82f-2a9e41502b9f\"\n" +
//                "}\n";
//        UserPartner userObject = JSONObject.parseObject(jsonUser,UserPartner.class);
//        UserPartner userPartner = loginController.login(userObject);
//        assertEquals(userObject.toString(), userPartner.toString());
//    }
//
//    //code review: changeable token
//    @Test
//    void testGetInfo(){
//
//        String userToken="6b21685b-b12a-42b8-9972-415f361a6f4d";
//        Map<String, String> params=new HashMap<>();
//        params.put("token",userToken);
//        String jsonUser = "{\n" +
//                "    \"userId\" : \"898329415\",\n" +
//                "    \"username\" : \"0424\",\n" +
//                "    \"fullname\" : \"0424\",\n" +
//                "    \"email\" : \"cxvjier@gmail.com\",\n" +
//                "    \"password\" : \"$2a$10$mxeHz85HLm5eBUU.9T/6ZeTyMsi4a7CyvMSM9C2hXGe/A0BS3686e\",\n" +
//                "    \"token\" : \"6b21685b-b12a-42b8-9972-415f361a6f4d\",\n" +
//                "    \"tokenDate\" : \"2023-04-24T04:00:37.361+0000\",\n" +
//                "    \"userAccessSecret\" : \"gHRxkZnGWB6WQKXYl15pMoG4e2uDkr65cYe\",\n" +
//                "    \"userAccessToken\" : \"2d7c25b2-27f5-4974-a82f-2a9e41502b9f\"\n" +
//                "}\n";
//
//        UserPartner userObject = JSONObject.parseObject(jsonUser,UserPartner.class);
//        UserPartner userPartner = loginController.getInfo(params);
//        assertEquals(userObject.toString(), userPartner.toString());
//    }
//}
