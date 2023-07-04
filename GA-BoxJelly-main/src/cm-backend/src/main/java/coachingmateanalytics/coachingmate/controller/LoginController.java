package coachingmateanalytics.coachingmate.controller;

import coachingmateanalytics.coachingmate.common.enums.ResponseCode;
import coachingmateanalytics.coachingmate.common.exceptions.BusinessException;
import coachingmateanalytics.coachingmate.common.utils.Consts;
import coachingmateanalytics.coachingmate.dao.TokenDao;
import coachingmateanalytics.coachingmate.dao.UserDao;
import coachingmateanalytics.coachingmate.dao.UserDaoImpl;
import coachingmateanalytics.coachingmate.entity.RequestToken;
import coachingmateanalytics.coachingmate.entity.UserPartner;
import coachingmateanalytics.coachingmate.service.OAuthService;
import coachingmateanalytics.coachingmate.service.SendEmail;
import coachingmateanalytics.coachingmate.service.UserService;
import coachingmateanalytics.coachingmate.service.VercodeTime;
import coachingmateanalytics.coachingmate.service.serviceImpl.ActivityServiceImpl;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.result.UpdateResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
// work done：
// 1. clean up the code
// 2. add comments

/**
 * Login Controller for CoachingMate application
 */
@RestController
@EnableAutoConfiguration
@Slf4j
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    UserService userService;

    @Resource
    ActivityServiceImpl activityService;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private SendEmail sendEmail;

    @Autowired
    UserDaoImpl userDao;

    @Autowired
    TokenDao tokenDao;

    /**
     * User login
     * After using spring security, it may need to be deleted
     * @return UserPartner object
     * it will
     */
//    @RequestMapping(value = "/login1", method = RequestMethod.POST, produces = "application/json")
//    @ApiOperation(value = "login interface", notes = "user login")
//    public UserPartner login(@RequestBody UserPartner inputUser) {
//        UserPartner userPartner = userService.loginCheck(inputUser.getUsername(), inputUser.getPassword());
//        if (userPartner == null) throw new BusinessException(ResponseCode.USER_IS_NOT_EXISTED);
//        userPartner.setPassword("");
//        return userPartner;
//    }

    /**
     * Get user by token
     *
     * @param param Map containing user token
     * @return UserPartner object
     */
    @RequestMapping(value = "/getUserByToken", method = RequestMethod.POST, produces = "application/json")
    public UserPartner getInfo(@RequestBody Map<String, String> param) {
        UserPartner userPartner = userService.getUserByToken(param.get("token")); // get userPartner from userService
        if (userPartner == null)
            return null;
        userPartner.setPassword(""); // set password to empty
        return userPartner;
    }

//    /**
//     * Reset user password
//     *
//     * @param param Map containing user's username
//     * @return UserPartner object
//     */
//    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST, produces = "application/json")
//    public UserPartner resetPassword(@RequestBody Map<String, String> param) {
//        UserPartner userPartner = userService.getUser(param.get("username")); // get userPartner from userService
//        if (userPartner == null)
//            throw new BusinessException(ResponseCode.USER_IS_NOT_EXISTED);
//        userPartner.setPassword(""); // set password to empty
//        return userPartner;
//    }

    @RequestMapping(value = "/verifyEmail", method = RequestMethod.POST)
    @ApiOperation(value = "verify email address", notes = "verify email address")
    public ResponseEntity verifyEmail(@ApiParam(required = true, type = "String") String email) throws UnsupportedEncodingException {
//        UserPartner userObject = JSONObject.parseObject(email,UserPartner.class);
//        email = userObject.getEmail();
        Query query = Query.query(Criteria.where("email").is(email));
        if (!mongoTemplate.exists(query,"user")){
            sendEmail.sendVerEmail(email);
            return ResponseEntity.ok("Request success");
        }
        else {
            throw new BusinessException(ResponseCode.EMAIL_HAS_EXISTED);
        }
    }
//    @RequestMapping(value = "/verifyEmail/verify", method = RequestMethod.POST, produces = "application/json")
//    public ResponseEntity verify(@ApiParam(required = true, type = "String") String code, @ApiParam(required = true, type = "String") String email){
//        Date current = new Date();
//        boolean a = (Objects.equals(String.valueOf(VercodeTime.verCodeMap.get(email)), code.toString()));
//        boolean b = (VercodeTime.currentTimeMap.get(email) - current.getTime() <= 18000000);
//        System.out.println(VercodeTime.verCodeMap.get(email).toString());
//        System.out.println(code.toString());
//        System.out.println(a);
//        System.out.println(b);
//        if (a && b){
//            System.out.println(1);
//            UserPartner userPartner = userService.getUserByEmail(email);
//            userPartner.setVerifed(true);
//            userDao.updateUser(userPartner);
////            Query query = Query.query(Criteria.where("email").is(email));
////            Update update = new Update();
////            update.set("verified", true);
////            UpdateResult updateResult = mongoTemplate.updateFirst(query, update, LoginController.class);
////            System.out.println(updateResult.getModifiedCount());
//            return ResponseEntity.ok("Request success");
//        }
//        else {
//            return ResponseEntity.ok("Code expired or mismatched");
//        }
//    }

    @RequestMapping(value =  "/requestReset", method = RequestMethod.POST)
    @ApiOperation(value = "request reset password", notes = "request reset password")
    public ResponseEntity requestReset(@ApiParam(required = true, type = "String") String email) throws UnsupportedEncodingException {
        UserPartner userObject = JSONObject.parseObject(email,UserPartner.class);
        email = userObject.getEmail();
        Query query = Query.query(Criteria.where("email").is(email));
        if (mongoTemplate.exists(query,"user")){
            sendEmail.sendResetEmail(email);
            return ResponseEntity.ok("Request success");
        }
        else {
            System.out.println(email);
            throw new BusinessException(ResponseCode.EMAIL_IS_NOT_EXISTED);
        }
    }

    @RequestMapping(value =  "/requestReset/reset", method = RequestMethod.POST)
    @ApiOperation(value = "reset password", notes = "reset password")
    public ResponseEntity reset(@ApiParam(required = true, type = "String") String email, @ApiParam(required = true, type = "String") String password, @ApiParam(required = true, type = "String") String security) {
//        if (Objects.equals(oldpassword, newpassword)) return ResponseEntity.ok("Same password error");
//        UserPartner userObject = JSONObject.parseObject(email,UserPartner.class);
//        email = userObject.getEmail();

        Date current = new Date();
        boolean a = (Objects.equals(String.valueOf(VercodeTime.stringCodeMap.get(email)), security.toString()));
        boolean b = (VercodeTime.currentTimeMapString.get(email) - current.getTime() <= 18000000);
        System.out.println(VercodeTime.stringCodeMap.get(email).toString());
        System.out.println(security.toString());
        System.out.println(a);
        System.out.println(b);

        if (a&&b){
            UserPartner userPartner = userService.getUserByEmail(email);
//        UserPartner userObject1 = JSONObject.parseObject(password,UserPartner.class);
//        password = userObject1.getPassword();
            System.out.println(password);
            String username = userPartner.getUsername();
//        userPartner = userService.loginCheck(username, oldpassword);
//        if (userPartner == null) return ResponseEntity.ok("Wrong password provided");
            Query query = new Query(Criteria.where("username").is(username));
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            Update update = Update.update("password", passwordEncoder.encode(password));
            UpdateResult result = mongoTemplate.updateMulti(query, update, UserPartner.class, Consts.MONGODB_USER_COLLECTIN_NAME);
            System.out.println(result);
            return ResponseEntity.ok("Request success");
        }
        throw new BusinessException(ResponseCode.SECURITY_CODE_EXPIRED_OR_INCORRECT);
    }

    @RequestMapping(value = "/garminCheck", method = RequestMethod.POST)
    @ApiOperation(value = "check if account is connected to garmin", notes = "check if account is connected to garmin")
    public ResponseEntity garminCheck(@ApiParam(required = true, type = "String") String username) {
        UserPartner userPartner = userService.getUser(username);
        if (userPartner.getUserAccessToken() == null) {
            throw new BusinessException(ResponseCode.NOT_AUTHORISED);
        } else {
            System.out.println();
            if (userService.tokenCheck(userPartner.getUserAccessToken())) {
                return ResponseEntity.ok("Account is connected with garmin");
            }
            else {
                throw new BusinessException(ResponseCode.AUTHORISATION_EXPIRED);
            }
        }

    }
    @RequestMapping(value = "/tasks", method = RequestMethod.POST, produces = "application/json")
    public UserPartner getInfoTest(@RequestBody Map<String, String> param) {
        UserPartner userPartner = userService.getUserByToken(param.get("token")); // get userPartner from userService
        if (userPartner == null)
            return null;
        userPartner.setPassword(""); // set password to empty
        return userPartner;
    }

}
