package coachingmateanalytics.coachingmate.service.serviceImpl;

import coachingmateanalytics.coachingmate.common.enums.ResponseCode;
import coachingmateanalytics.coachingmate.common.exceptions.BusinessException;
import coachingmateanalytics.coachingmate.dao.UserDao;

import coachingmateanalytics.coachingmate.entity.UserPartner;
import coachingmateanalytics.coachingmate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * UserService implementation that implements the UserService interface.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    /**
     * Login check for user credentials.
     *
     * @param username user's username
     * @param password user's password
     * @return UserPartner object if login is successful, otherwise null
     */
    @Override
    public UserPartner loginCheck(String username, String password) {
        UserPartner userPartner = userDao.findUserByUsername(username);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // use match to compare the user's password. password is raw password
        if(null == userPartner || !passwordEncoder.matches(password,userPartner.getPassword())) return null;
        String token = UUID.randomUUID().toString();
        userPartner.setTokenDate(new Date());
        userPartner.setToken(token);
        userDao.updateUser(userPartner);
        return userPartner;
    }

    /**
     * Retrieve user by username.
     *
     * @param username user's username
     * @return UserPartner object
     */
    @Override
    public UserPartner getUser(String username) {
        return userDao.findUserByUsername(username);
    }

    /**
     * Retrieve user by token.
     *
     * @param token user's token
     * @return UserPartner object
     */
    @Override
    public UserPartner getUserByToken(String token) {
        return userDao.selectUserByToken(token);
    }

    /**
     * Check if the user token is valid.
     *
     * @param token user's token
     * @return true if token is valid, otherwise false
     */
    @Override
    public Boolean tokenCheck(String token) {
        UserPartner userPartner = userDao.selectUserByToken(token);
        if (userPartner == null) return false;

        Date createDate = userPartner.getTokenDate();
        Date currDate = new Date();

        Calendar createCalendar = new GregorianCalendar();
        createCalendar.setTime(createDate);
        createCalendar.add(createCalendar.DATE, 1); //把日期往后增加一天,整数  往后推,负数往前移动
        createDate = createCalendar.getTime(); //这个时间就是日期往后推一天的结果
        if(createDate.compareTo(currDate) < 0) {
            return false;
        }
        userPartner.setTokenDate(currDate);
        userDao.updateUser(userPartner);
        return true;
    }

    @Override
    public UserPartner getUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    /**
     * Register a new user.
     *
     * @param fullname user's fullname
     * @param username user's username
     * @param password user's password
     * @param email    user's email
     * @return UserPartner object if registration is successful, otherwise null
     */
    @Override
    public UserPartner register(String fullname, String username, String password, String email) {
        UserPartner userPartner = userDao.findUserByUsername(username);
        UserPartner userPartner1 = userDao.findUserByEmail(email);
        int maxId=userDao.getMaxUserid();
        if(userPartner != null || userPartner1!= null) return null;
        Random random = new Random();
        UserPartner newUserPartner = new UserPartner();
        newUserPartner.setFullname(fullname);
        newUserPartner.setUsername(username);
        newUserPartner.setPassword(password);
        newUserPartner.setEmail(email);
        newUserPartner.setUserId(maxId+1);
        newUserPartner.setVerifed(false);
        userDao.saveUser(newUserPartner);
        return newUserPartner;
    }
    @Override
    public void updateUser(UserPartner userPartner){
        userDao.updateUser(userPartner);
    }
}
