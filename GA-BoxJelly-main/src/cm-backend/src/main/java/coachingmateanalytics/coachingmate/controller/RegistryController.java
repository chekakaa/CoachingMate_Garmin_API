package coachingmateanalytics.coachingmate.controller;

import coachingmateanalytics.coachingmate.common.enums.ResponseCode;
import coachingmateanalytics.coachingmate.common.exceptions.BusinessException;
import coachingmateanalytics.coachingmate.dao.UserDaoImpl;
import coachingmateanalytics.coachingmate.entity.UserPartner;
import coachingmateanalytics.coachingmate.service.UserService;
import coachingmateanalytics.coachingmate.service.VercodeTime;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

/**
 * RegistryController handles user registration operations.
 */
@RestController
@EnableAutoConfiguration
//@CrossOrigin("http://localhost:3000")
public class RegistryController {

    @Autowired
    UserService userService;

    @Autowired
    UserDaoImpl userDao;

    /**
     * Registers a new user with the provided fullname, username, password, and email.
     *
     * @param fullname User's full name
     * @param username User's username
     * @param password User's password
     * @param email    User's email
     * @return ResponseEntity with UserPartner object or a conflict status
     */
    @PostMapping("/register")
    @ApiOperation(value = "register interface", notes = "user registration")
    public ResponseEntity<UserPartner> register(@RequestBody UserPartner inputUser){
        String email = inputUser.getEmail();
        String fullname = inputUser.getFullname();
        String username = inputUser.getUsername();
        String password = inputUser.getPassword();
        String code = inputUser.getCode();
        try {
            Date current = new Date();
            boolean a = (Objects.equals(String.valueOf(VercodeTime.verCodeMap.get(email)), code.toString()));
            boolean b = (VercodeTime.currentTimeMap.get(email) - current.getTime() <= 18000000);
            System.out.println(VercodeTime.verCodeMap.get(email).toString());
            System.out.println(code.toString());
            System.out.println(a);
            System.out.println(b);
            if (a && b){
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                UserPartner user = userService.register(fullname,username, passwordEncoder.encode(password), email);
                if (user == null) throw new BusinessException(ResponseCode.EMAIL_OR_USERNAME_IS_NOT_EXISTED);
                System.out.println(1);
                UserPartner userPartner = userService.getUserByEmail(email);
                userPartner.setVerifed(true);
                userDao.updateUser(userPartner);
//            Query query = Query.query(Criteria.where("email").is(email));
//            Update update = new Update();
//            update.set("verified", true);
//            UpdateResult updateResult = mongoTemplate.updateFirst(query, update, LoginController.class);
//            System.out.println(updateResult.getModifiedCount());
                return ResponseEntity.ok(user);
            }
            else {
                throw new BusinessException(ResponseCode.SECURITY_CODE_EXPIRED_OR_INCORRECT);
            }
        }
        catch (Exception e){
            throw new BusinessException(ResponseCode.SECURITY_CODE_EXPIRED_OR_INCORRECT);
        }

//        return ResponseEntity.ok(user);
    }

}
