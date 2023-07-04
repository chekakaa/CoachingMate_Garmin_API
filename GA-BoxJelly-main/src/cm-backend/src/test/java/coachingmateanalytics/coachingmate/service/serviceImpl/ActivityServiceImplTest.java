package coachingmateanalytics.coachingmate.service.serviceImpl;

import coachingmateanalytics.coachingmate.CoachingmateApplication;
import coachingmateanalytics.coachingmate.common.utils.Consts;
import coachingmateanalytics.coachingmate.dao.ActivityDaoImpl;
import coachingmateanalytics.coachingmate.entity.Activity;
import coachingmateanalytics.coachingmate.entity.ActivityDetails;
import coachingmateanalytics.coachingmate.entity.UserPartner;
import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = CoachingmateApplication.class)
class ActivityServiceImplTest {

    @Autowired
    ActivityServiceImpl activityService;
    @Autowired
    MongoTemplate mongoTemplate;
    String accessToken="2d7c25b2-27f5-4974-a82f-2a9e41502b9f";
    private Activity expectedActivity;
    private ActivityDetails expectedActivityDetails;
    private List<Activity> expectedActivityList;
    private List<ActivityDetails> expectedActivityDetailsList;
    @BeforeEach
    public void setUp(){
        String jsonStringActivity="{\n" +
                "  \"durationInSeconds\": 10,\n" +
                "  \"averageSpeedInMetersPerSecond\": 0.233,\n" +
                "  \"averageHeartRateInBeatsPerMinute\": 92,\n" +
                "  \"distanceInMeters\": 2.42,\n" +
                "  \"activityName\": \"Running\",\n" +
                "  \"userId\": \"898329394\",\n" +
                "  \"deviceName\": \"forerunner935\",\n" +
                "  \"averagePaceInMinutesPerKilometer\": 71.53076,\n" +
                "  \"activityId\": 10,\n" +
                "  \"startTimeInSeconds\": 1650191523,\n" +
                "  \"userAccessToken\": \"2d7c25b2-27f5-4974-a82f-2a9e41502b9f\",\n" +
                "  \"startTimeOffsetInSeconds\": 36000,\n" +
                "  \"maxPaceInMinutesPerKilometer\": 6.8956003,\n" +
                "  \"maxHeartRateInBeatsPerMinute\": 95,\n" +
                "  \"summaryId\": \"8654408419\",\n" +
                "  \"maxSpeedInMetersPerSecond\": 2.417,\n" +
                "  \"activityType\": \"RUNNING\"\n" +
                "}";
        String jsonStringActivityList="["+jsonStringActivity+"]";
        String jsonStringActivityDetails=
                "{\n" +
                "  \"summary\": {\n" +
                "    \"durationInSeconds\": 10,\n" +
                "    \"averageSpeedInMetersPerSecond\": 1.362,\n" +
                "    \"averageHeartRateInBeatsPerMinute\": 88,\n" +
                "    \"distanceInMeters\": 13.39,\n" +
                "    \"activityName\": \"Running\",\n" +
                "    \"deviceName\": \"forerunner935\",\n" +
                "    \"steps\": 4,\n" +
                "    \"averageRunCadenceInStepsPerMinute\": 25.09375,\n" +
                "    \"averagePaceInMinutesPerKilometer\": 12.236906,\n" +
                "    \"activityId\": 10,\n" +
                "    \"startTimeInSeconds\": 1650195177,\n" +
                "    \"startTimeOffsetInSeconds\": 36000,\n" +
                "    \"maxPaceInMinutesPerKilometer\": 4.8676014,\n" +
                "    \"maxHeartRateInBeatsPerMinute\": 93,\n" +
                "    \"maxRunCadenceInStepsPerMinute\": 138,\n" +
                "    \"maxSpeedInMetersPerSecond\": 3.424,\n" +
                "    \"activityType\": \"RUNNING\"\n" +
                "  },\n" +
                "  \"activityId\": 10,\n" +
                "  \"userAccessToken\": \"2d7c25b2-27f5-4974-a82f-2a9e41502b9f\",\n" +
                "  \"summaryId\": \"10-detail\",\n" +
                "  \"laps\": [\n" +
                "    {\n" +
                "      \"startTimeInSeconds\": 1650195177\n" +
                "    }\n" +
                "  ],\n" +
                "  \"userId\": \"898329394\",\n" +
                "  \"samples\": [\n" +
                "    {\n" +
                "      \"startTimeInSeconds\": 1650195177,\n" +
                "      \"timerDurationInSeconds\": 0,\n" +
                "      \"movingDurationInSeconds\": 0,\n" +
                "      \"heartRate\": 84,\n" +
                "      \"elevationInMeters\": 33.20000076293945,\n" +
                "      \"speedMetersPerSecond\": 0,\n" +
                "      \"clockDurationInSeconds\": 0,\n" +
                "      \"stepsPerMinute\": 0,\n" +
                "      \"airTemperatureCelcius\": 32,\n" +
                "      \"totalDistanceInMeters\": 0,\n" +
                "      \"latitudeInDegree\": -37.813,\n" +
                "      \"longitudeInDegree\":144.96\n" +
                "    },\n" +
                "    {\n" +
                "      \"startTimeInSeconds\": 1650195178,\n" +
                "      \"timerDurationInSeconds\": 1,\n" +
                "      \"movingDurationInSeconds\": 0,\n" +
                "      \"heartRate\": 86,\n" +
                "      \"elevationInMeters\": 33.20000076293945,\n" +
                "      \"speedMetersPerSecond\": 0,\n" +
                "      \"clockDurationInSeconds\": 1,\n" +
                "      \"stepsPerMinute\": 0,\n" +
                "      \"airTemperatureCelcius\": 32,\n" +
                "      \"totalDistanceInMeters\": 0,\n" +
                "      \"latitudeInDegree\": -37.815,\n" +
                "      \"longitudeInDegree\":144.95\n" +
                "    },\n" +
                "    {\n" +
                "      \"startTimeInSeconds\": 1650195182,\n" +
                "      \"timerDurationInSeconds\": 5,\n" +
                "      \"movingDurationInSeconds\": 0,\n" +
                "      \"heartRate\": 129,\n" +
                "      \"elevationInMeters\": 31.799999237060547,\n" +
                "      \"speedMetersPerSecond\": 0,\n" +
                "      \"clockDurationInSeconds\": 5,\n" +
                "      \"stepsPerMinute\": 0,\n" +
                "      \"airTemperatureCelcius\": 32,\n" +
                "      \"totalDistanceInMeters\": 0,\n" +
                "      \"latitudeInDegree\": -37.816,\n" +
                "      \"longitudeInDegree\":144.94\n" +
                "    },\n" +
                "    {\n" +
                "      \"startTimeInSeconds\": 1650195185,\n" +
                "      \"timerDurationInSeconds\": 8,\n" +
                "      \"movingDurationInSeconds\": 3,\n" +
                "      \"heartRate\": 129,\n" +
                "      \"elevationInMeters\": 31,\n" +
                "      \"speedMetersPerSecond\": 3.4240000247955322,\n" +
                "      \"clockDurationInSeconds\": 8,\n" +
                "      \"stepsPerMinute\": 0,\n" +
                "      \"airTemperatureCelcius\": 32,\n" +
                "      \"totalDistanceInMeters\": 6.539999961853027,\n" +
                "      \"latitudeInDegree\": -37.817,\n" +
                "      \"longitudeInDegree\":144.93\n" +
                "    },\n" +
                "    {\n" +
                "      \"startTimeInSeconds\": 1650195187,\n" +
                "      \"timerDurationInSeconds\": 10,\n" +
                "      \"movingDurationInSeconds\": 5,\n" +
                "      \"heartRate\": 112,\n" +
                "      \"elevationInMeters\": 31,\n" +
                "      \"speedMetersPerSecond\": 3.4240000247955322,\n" +
                "      \"clockDurationInSeconds\": 10,\n" +
                "      \"stepsPerMinute\": 0,\n" +
                "      \"airTemperatureCelcius\": 32,\n" +
                "      \"totalDistanceInMeters\": 13.390000343322754,\n" +
                "      \"latitudeInDegree\": -37.818,\n" +
                "      \"longitudeInDegree\":144.92\n" +
                "    }\n" +
                "  ]\n" +
                "}\n" ;
        String jsonStringActivityDetailsList="["+jsonStringActivityDetails+"]";

        expectedActivity=JSONObject.parseObject(jsonStringActivity,Activity.class);
        expectedActivityDetails=JSONObject.parseObject(jsonStringActivityDetails,ActivityDetails.class);
        expectedActivityList= JSONObject.parseArray(jsonStringActivityList,Activity.class);
        expectedActivityDetailsList= JSONObject.parseArray(jsonStringActivityDetailsList,ActivityDetails.class);


    }
    @Test
    void selectActivityByAccessToken() {

        setUp();
        List<Activity> activityList = activityService.selectActivityByAccessToken(accessToken);
        assertEquals(expectedActivityList.toString(),activityList.toString());


    }

    @Test
    void selectActivityDetailsByAccessToken() {

        List<ActivityDetails> activityDetailsList = activityService.selectActivityDetailsByAccessToken(accessToken);
        assertEquals(expectedActivityDetailsList.toString(),activityDetailsList.toString());
    }

    @Test
    void selectActivityDetailsByActivityId() {
        String activityId = "10";
        ActivityDetails activityDetails = activityService.selectActivityDetailsByActivityId(activityId);
        assertEquals(expectedActivityDetails.toString(),activityDetails.toString());
    }

    @Test
    void insertActivity() {
        String activityId= "10000000";
        Activity activity = new Activity();
        activity.setActivityId(Long.parseLong(activityId));
        Query query = Query.query(Criteria.where("activityId").is(Long.valueOf(activityId)));
        Activity activityFromMongo= mongoTemplate.findOne(query, Activity.class, Consts.MONGODB_ACTIVITY_COLLECTIN_NAME); // find activity by activityId

        assertNull(activityFromMongo);
        activityService.insertActivity(activity);
        activityFromMongo= mongoTemplate.findOne(query, Activity.class, Consts.MONGODB_ACTIVITY_COLLECTIN_NAME); // find activity by activityId

        assertEquals(activity.toString(),activityFromMongo.toString());
        mongoTemplate.remove(query, Activity.class,Consts.MONGODB_ACTIVITY_COLLECTIN_NAME);

    }

    @Test
    void insertActivityDetail() {
        String activityDetailsId= "10000000";
        ActivityDetails activityDetails = new ActivityDetails();
        activityDetails.setActivityId(Long.parseLong(activityDetailsId));

        Query query = Query.query(Criteria.where("activityId").is(Long.valueOf(activityDetailsId)));
        ActivityDetails activityDetailsFromMongo= mongoTemplate.findOne(query, ActivityDetails.class, Consts.MONGODB_ACTIVITY_DETAILS_COLLECTIN_NAME); // find activityDetails by activityId

        assertNull(activityDetailsFromMongo);
        activityService.insertActivityDetail(activityDetails);
        activityDetailsFromMongo= mongoTemplate.findOne(query, ActivityDetails.class, Consts.MONGODB_ACTIVITY_DETAILS_COLLECTIN_NAME); // find activityDetails by activityId

        assertEquals(activityDetails.toString(),activityDetailsFromMongo.toString());
        mongoTemplate.remove(query, ActivityDetails.class,Consts.MONGODB_ACTIVITY_DETAILS_COLLECTIN_NAME);

    }


    @Test
    public void statisticsActivities(){
        String accessToken = "366f91a6-0279-40bc-866e-3fdc92a8f180";
        //activityService.statisticsActivities(accessToken);
    }
}