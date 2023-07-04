package coachingmateanalytics.coachingmate.controller;

import java.util.List;

import javax.annotation.Resource;

import coachingmateanalytics.coachingmate.entity.Activity;
import coachingmateanalytics.coachingmate.entity.ActivityDetails;
import coachingmateanalytics.coachingmate.entity.DashboardStastic;
import coachingmateanalytics.coachingmate.service.ActivityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Date: 24/9/20 15:51
 * @Description: Activity Data Retrieve Controller
 */
@RestController
@EnableAutoConfiguration
@Slf4j
@RequestMapping("/activity")
public class ActivityDataRetrieveController {

    @Resource
    ActivityService activityService;

    @PostMapping("/getActivityByAccessToken")
    @ApiOperation(value = "Retrieve Data By Access Token", notes = "Query all activity data of specific user")
    public ResponseEntity<List<Activity>> getActivityByAccessToken(@ApiParam(required = true, type = "String")
                                                                       @RequestParam("accessToken") String accessToken) {
        List<Activity> allByaccessToken = activityService.selectActivityByAccessToken(accessToken);
        return ResponseEntity.ok(allByaccessToken);
    }

    @PostMapping("/getActivityDetailsByAccessToken")
    @ApiOperation(value = "Retrieve Data By Access Token", notes = "Query all activity data of specific user")
    public ResponseEntity<List<ActivityDetails>> getActivityDetailsByAccessToken(@ApiParam(required = true, type = "String")
                                                                                     @RequestParam("accessToken") String accessToken) {
        List<ActivityDetails> activityDetailsList = activityService.selectActivityDetailsByAccessToken(accessToken);
        return ResponseEntity.ok(activityDetailsList);
    }

    @PostMapping("/getActivityByAccessTokenAndType")
    @ApiOperation(value = "Retrieve Data By Access Token and Activity Type", notes = "Query all activity data of specific user")
    public List<Activity> getActivityByAccessTokenAndType(@ApiParam(required = true, type = "String")
                                                                   @RequestParam("accessToken") String accessToken,
                                                           @RequestParam("activityType") String activityType) {
        return activityService.selectActivityByAccessTokenAndType(accessToken, activityType);
    }

    @PostMapping("/getActivityDetailsByActivityId")
    @ApiOperation(value = "Retrieve Activity Details By Activity Id", notes = "Query activity details data of specific activity")
    public ActivityDetails getActivityDetailsByActivityId(@RequestParam("activityId") String activityId) {
        return activityService.selectActivityDetailsByActivityId(activityId);
    }

    @PostMapping("/getDashboardStatisticsByAccessToken")
    @ApiOperation(value = "Retrieve Dashboard Statistics By Access Token", notes = "Query dashboard statistics data of specific user")
    public DashboardStastic getDashboardStatisticsByAccessToken(@RequestParam("accessToken") String accessToken) {
        return activityService.selectDashboardStatisticsByAccessToken(accessToken);
    }
}
