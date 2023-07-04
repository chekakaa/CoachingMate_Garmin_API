package coachingmateanalytics.coachingmate.controller;


import coachingmateanalytics.coachingmate.common.enums.ResponseCode;
import coachingmateanalytics.coachingmate.common.exceptions.BusinessException;
import coachingmateanalytics.coachingmate.entity.*;
import coachingmateanalytics.coachingmate.service.ActivityService;
import coachingmateanalytics.coachingmate.common.utils.FileUtils;
import coachingmateanalytics.coachingmate.common.utils.JsonUtils;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import coachingmateanalytics.coachingmate.dao.*;
import coachingmateanalytics.coachingmate.service.*;


import com.fasterxml.jackson.core.JsonProcessingException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class GarminPushController {
    private static final Logger logger = LoggerFactory.getLogger(GarminPushController.class);
    public static final String STORE_PATH="D:/coachingmate/public/garmin_raw/";

    @Autowired
    ActivityService activityService;

    @Resource
    UserService userService;

    @Autowired
    TokenDao tokenDao;


    //configure this url to enpoint configuration, and the garmin endpoint will transfer the data to this server
//    @PostMapping("/pushActivity", produces = "application/json")
    @RequestMapping(value = "/pushActivity", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "push data url", notes = "configure this url to enpoint configuration, " +
            "and the garmin endpoint will transfer the data to this server@RequestBody List<Activity> activityList")
    public ResponseEntity<String> acceptPushedActivity(@RequestBody String activities) {
        logger.info("start push data");

        //请求参数打印
        logger.info("Activities: " + activities);

        Gson gson = new GsonBuilder().create();

        ActivityBean activityBean = gson.fromJson(activities, ActivityBean.class);

        for(Activity activity : activityBean.getActivities()){
            if(activity != null) {
                activityService.insertActivity(activity);
            }
        }


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Location", "public/garmin_raw");
        return ResponseEntity.accepted().headers(httpHeaders).body("Accept the pushed activities");

    }
    //configure this url to enpoint configuration, and the garmin endpoint will transfer the data to this server
//    @PostMapping("/pushActivity", produces = "application/json")
    @RequestMapping(value = "/pushActivityDetail", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "push data url", notes = "configure this url to enpoint configuration, " +
            "and the garmin endpoint will transfer the data to this server@RequestBody List<Activity> activityList")
    public ResponseEntity<String> acceptPushedActivityDetail(@RequestBody String acceptPushedActivityDetail) {
        logger.info("start push data");

        //请求参数打印
//        logger.info("acceptPushedActivityDetail: " + acceptPushedActivityDetail);
        HttpHeaders httpHeaders = new HttpHeaders();
        try{
            Gson gson = new GsonBuilder().create();
            String accseeToken = "";

            ActivityDetailsBean activityDetailsBean = gson.fromJson(acceptPushedActivityDetail, ActivityDetailsBean.class);
            for(ActivityDetails activityDetails : activityDetailsBean.getActivityDetails()){
                if(activityDetails != null){
                    accseeToken = activityDetails.getUserAccessToken();
                    activityService.insertActivityDetail(activityDetails);
                }
            }

            activityService.statisticsActivities(accseeToken);

        } catch (Exception e){
            logger.info(String.valueOf(e));
        } finally {

            httpHeaders.set("Location", "public/garmin_raw");
            return ResponseEntity.accepted().headers(httpHeaders).body("Accept the pushed activities");
        }



    }


//
//configure this url to enpoint configuration, and the garmin endpoint will transfer the data to this server
    @PostMapping("/pushFile")
    @ApiOperation(value = "push data url", notes = "configure this url to enpoint configuration, " +
            "and the garmin endpoint will transfer the data to this server")
//    public ResponseEntity<String> acceptPushedFile1(@RequestBody MultipartFile file) {
    public ResponseEntity<String> acceptPushedFile1(@RequestBody String info) throws IOException {
        logger.debug("start push data");
        logger.info("MultipartFile :" + info);
//        Pattern pattern = Pattern.compile("");
//        Matcher matcher = pattern.matcher(info);
//        String activitylist_str = info.split(":", 2)[1];
//        activitylist_str = activitylist_str.substring(2, activitylist_str.length() - 4);
//        ActivityFile activityFile = JsonUtils.string2Obj("{" + activitylist_str + "}", ActivityFile.class);
        Map<String, Object> jsonmap = JSONObject.parseObject(info,Map.class);
        System.out.println(jsonmap.get("activityFiles").toString());
//        Object activityFileList = jsonmap.get("activityFiles").toString();
        List<ActivityFile> activityFileList=JSONObject.parseArray(jsonmap.get("activityFiles").toString(),ActivityFile.class);


//        ActivityFile activityFile= JSONObject.parseObject(info,ActivityFile.class);
//        for (ActivityFile activityFile : activityFileList){
//            logger.info("Download File:" + activityFile.getCallbackURL());
//            FileUtils.downloadFile(activityFile.getCallbackURL(), "./test.fit");
//            logger.info("Download Completed");
//        }
//        logger.info("Download File:" + activityFile.getCallbackURL());
//        FileUtils.downloadFile(activityFile.getCallbackURL(), "./test.fit");
//        logger.info("Download Completed");
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set("Location", "public/garmin_raw");
        return ResponseEntity.accepted().headers(httpHeaders).body("Accept the pushed file");

    }

    @PostMapping("/disconnect")
    @ApiOperation(value = "Revoke access token and disconnect from Garmin Connect", notes = "Revokes the access token for the specified user, effectively disconnecting from Garmin Connect.")
    public ResponseEntity<String> disconnect(@ApiParam(required = true, type = "String") @RequestParam String username) {
        UserDaoImpl userdaoimpl = new UserDaoImpl();
        UserPartner userPartner = userService.getUser(username);
        if (userPartner.getUserAccessToken() == null) {
            throw new BusinessException(ResponseCode.NOT_AUTHORISED);
        } else {
            OAuthService oAuthService = new OAuthService();
            if (userService.tokenCheck(userPartner.getUserAccessToken())) {
                oAuthService.revokeAccessToken(userPartner.getUserAccessToken());
                userdaoimpl.selectUserByToken(null);
                userdaoimpl.saveUser(userPartner);
            }
            else {
                tokenDao.deleteAccessToken(username);
            }
            return ResponseEntity.ok().body("User " + username + " has been disconnected.");
        }
    }


}
