//package coachingmateanalytics.coachingmate.dao;
//
//import coachingmateanalytics.coachingmate.AppTest;
//import coachingmateanalytics.coachingmate.CoachingmateApplication;
//import coachingmateanalytics.coachingmate.common.utils.Consts;
//import coachingmateanalytics.coachingmate.entity.Activity;
//import coachingmateanalytics.coachingmate.entity.ActivityDetails;
//import coachingmateanalytics.coachingmate.entity.DashboardStastic;
//import coachingmateanalytics.coachingmate.entity.UserPartner;
//import com.alibaba.fastjson.JSONObject;
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//
//import java.util.Date;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
///**
// * @Date: 24/9/20 12:04
// * @Description:
// */
//
//public class ActivityDaoImplTest extends  AppTest{
//    private static final Logger logger = LoggerFactory.getLogger(ActivityDaoImplTest.class);
//
//    @Autowired
//    private ActivityDaoImpl activityDaoImpl;
//
//    String accessToken="2d7c25b2-27f5-4974-a82f-2a9e41502b9f";
//    @Autowired
//    MongoTemplate mongoTemplate;
//
//    @Test
//    public void saveActivity() {
//
//    }
//
//    @Test
//    public void testFindAllByAccessToken() {
//        List<Activity> activities=activityDaoImpl.findAllByAccessToken(accessToken);
//        String jsonStringActivity="[{\n" +
//                "  \"durationInSeconds\": 10,\n" +
//                "  \"averageSpeedInMetersPerSecond\": 0.233,\n" +
//                "  \"averageHeartRateInBeatsPerMinute\": 92,\n" +
//                "  \"distanceInMeters\": 2.42,\n" +
//                "  \"activityName\": \"Running\",\n" +
//                "  \"userId\": \"898329394\",\n" +
//                "  \"deviceName\": \"forerunner935\",\n" +
//                "  \"averagePaceInMinutesPerKilometer\": 71.53076,\n" +
//                "  \"activityId\": 10,\n" +
//                "  \"startTimeInSeconds\": 1650191523,\n" +
//                "  \"userAccessToken\": \"2d7c25b2-27f5-4974-a82f-2a9e41502b9f\",\n" +
//                "  \"startTimeOffsetInSeconds\": 36000,\n" +
//                "  \"maxPaceInMinutesPerKilometer\": 6.8956003,\n" +
//                "  \"maxHeartRateInBeatsPerMinute\": 95,\n" +
//                "  \"summaryId\": \"8654408419\",\n" +
//                "  \"maxSpeedInMetersPerSecond\": 2.417,\n" +
//                "  \"activityType\": \"RUNNING\"\n" +
//                "}]";
//        List<Activity> activityList= JSONObject.parseArray(jsonStringActivity,Activity.class);
//        assertEquals(activities.toString(),activityList.toString());
//
//    }
//
//    @Test
//    public void testSelectActivityDetailsByAccessToken() {
//        List<ActivityDetails> activityDetails=activityDaoImpl.selectActivityDetailsByAccessToken(accessToken);
//        String jsonStringActivityDetails="[\n" +
//                "{\n" +
//                "  \"summary\": {\n" +
//                "    \"durationInSeconds\": 10,\n" +
//                "    \"averageSpeedInMetersPerSecond\": 1.362,\n" +
//                "    \"averageHeartRateInBeatsPerMinute\": 88,\n" +
//                "    \"distanceInMeters\": 13.39,\n" +
//                "    \"activityName\": \"Running\",\n" +
//                "    \"deviceName\": \"forerunner935\",\n" +
//                "    \"steps\": 4,\n" +
//                "    \"averageRunCadenceInStepsPerMinute\": 25.09375,\n" +
//                "    \"averagePaceInMinutesPerKilometer\": 12.236906,\n" +
//                "    \"activityId\": 10,\n" +
//                "    \"startTimeInSeconds\": 1650195177,\n" +
//                "    \"startTimeOffsetInSeconds\": 36000,\n" +
//                "    \"maxPaceInMinutesPerKilometer\": 4.8676014,\n" +
//                "    \"maxHeartRateInBeatsPerMinute\": 93,\n" +
//                "    \"maxRunCadenceInStepsPerMinute\": 138,\n" +
//                "    \"maxSpeedInMetersPerSecond\": 3.424,\n" +
//                "    \"activityType\": \"RUNNING\"\n" +
//                "  },\n" +
//                "  \"activityId\": 10,\n" +
//                "  \"userAccessToken\": \"2d7c25b2-27f5-4974-a82f-2a9e41502b9f\",\n" +
//                "  \"summaryId\": \"10-detail\",\n" +
//                "  \"laps\": [\n" +
//                "    {\n" +
//                "      \"startTimeInSeconds\": 1650195177\n" +
//                "    }\n" +
//                "  ],\n" +
//                "  \"userId\": \"898329394\",\n" +
//                "  \"samples\": [\n" +
//                "    {\n" +
//                "      \"startTimeInSeconds\": 1650195177,\n" +
//                "      \"timerDurationInSeconds\": 0,\n" +
//                "      \"movingDurationInSeconds\": 0,\n" +
//                "      \"heartRate\": 84,\n" +
//                "      \"elevationInMeters\": 33.20000076293945,\n" +
//                "      \"speedMetersPerSecond\": 0,\n" +
//                "      \"clockDurationInSeconds\": 0,\n" +
//                "      \"stepsPerMinute\": 0,\n" +
//                "      \"airTemperatureCelcius\": 32,\n" +
//                "      \"totalDistanceInMeters\": 0,\n" +
//                "      \"latitudeInDegree\": -37.813,\n" +
//                "      \"longitudeInDegree\":144.96\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"startTimeInSeconds\": 1650195178,\n" +
//                "      \"timerDurationInSeconds\": 1,\n" +
//                "      \"movingDurationInSeconds\": 0,\n" +
//                "      \"heartRate\": 86,\n" +
//                "      \"elevationInMeters\": 33.20000076293945,\n" +
//                "      \"speedMetersPerSecond\": 0,\n" +
//                "      \"clockDurationInSeconds\": 1,\n" +
//                "      \"stepsPerMinute\": 0,\n" +
//                "      \"airTemperatureCelcius\": 32,\n" +
//                "      \"totalDistanceInMeters\": 0,\n" +
//                "      \"latitudeInDegree\": -37.815,\n" +
//                "      \"longitudeInDegree\":144.95\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"startTimeInSeconds\": 1650195182,\n" +
//                "      \"timerDurationInSeconds\": 5,\n" +
//                "      \"movingDurationInSeconds\": 0,\n" +
//                "      \"heartRate\": 129,\n" +
//                "      \"elevationInMeters\": 31.799999237060547,\n" +
//                "      \"speedMetersPerSecond\": 0,\n" +
//                "      \"clockDurationInSeconds\": 5,\n" +
//                "      \"stepsPerMinute\": 0,\n" +
//                "      \"airTemperatureCelcius\": 32,\n" +
//                "      \"totalDistanceInMeters\": 0,\n" +
//                "      \"latitudeInDegree\": -37.816,\n" +
//                "      \"longitudeInDegree\":144.94\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"startTimeInSeconds\": 1650195185,\n" +
//                "      \"timerDurationInSeconds\": 8,\n" +
//                "      \"movingDurationInSeconds\": 3,\n" +
//                "      \"heartRate\": 129,\n" +
//                "      \"elevationInMeters\": 31,\n" +
//                "      \"speedMetersPerSecond\": 3.4240000247955322,\n" +
//                "      \"clockDurationInSeconds\": 8,\n" +
//                "      \"stepsPerMinute\": 0,\n" +
//                "      \"airTemperatureCelcius\": 32,\n" +
//                "      \"totalDistanceInMeters\": 6.539999961853027,\n" +
//                "      \"latitudeInDegree\": -37.817,\n" +
//                "      \"longitudeInDegree\":144.93\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"startTimeInSeconds\": 1650195187,\n" +
//                "      \"timerDurationInSeconds\": 10,\n" +
//                "      \"movingDurationInSeconds\": 5,\n" +
//                "      \"heartRate\": 112,\n" +
//                "      \"elevationInMeters\": 31,\n" +
//                "      \"speedMetersPerSecond\": 3.4240000247955322,\n" +
//                "      \"clockDurationInSeconds\": 10,\n" +
//                "      \"stepsPerMinute\": 0,\n" +
//                "      \"airTemperatureCelcius\": 32,\n" +
//                "      \"totalDistanceInMeters\": 13.390000343322754,\n" +
//                "      \"latitudeInDegree\": -37.818,\n" +
//                "      \"longitudeInDegree\":144.92\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}\n" +
//                "]";
//
//
//        List<ActivityDetails> activityDetailsList=JSONObject.parseArray(jsonStringActivityDetails,ActivityDetails.class);
//
//        assertEquals(activityDetails.toString(),activityDetailsList.toString());
//    }
//
//    @Test
//    public void testInsertActivity(){
//        String jsonStringActivity="{\n" +
//                "  \"durationInSeconds\": 10,\n" +
//                "  \"averageSpeedInMetersPerSecond\": 0.233,\n" +
//                "  \"averageHeartRateInBeatsPerMinute\": 92,\n" +
//                "  \"distanceInMeters\": 2.42,\n" +
//                "  \"activityName\": \"Walking\",\n" +
//                "  \"userId\": \"898329394\",\n" +
//                "  \"deviceName\": \"forerunner935\",\n" +
//                "  \"averagePaceInMinutesPerKilometer\": 71.53076,\n" +
//                "  \"activityId\": 11,\n" +
//                "  \"startTimeInSeconds\": 1650191523,\n" +
//                "  \"userAccessToken\": \"7e88865e-eb7d-47c8-8418-80cd24bbd4e6\",\n" +
//                "  \"startTimeOffsetInSeconds\": 36000,\n" +
//                "  \"maxPaceInMinutesPerKilometer\": 6.8956003,\n" +
//                "  \"maxHeartRateInBeatsPerMinute\": 95,\n" +
//                "  \"summaryId\": \"8654408419\",\n" +
//                "  \"maxSpeedInMetersPerSecond\": 2.417,\n" +
//                "  \"activityType\": \"WALKING\"\n" +
//                "}";
//        Activity activity=JSONObject.parseObject(jsonStringActivity,Activity.class);
//        activityDaoImpl.insertActivity(activity);
//        Query query = Query.query(Criteria.where("activityId").is(Long.valueOf("11")));
//        Activity ActualActivity= mongoTemplate.findOne(query, Activity.class, Consts.MONGODB_ACTIVITY_COLLECTIN_NAME); // find activityDetails by activityId
//        assertEquals(activity.toString(),ActualActivity.toString());
//        mongoTemplate.remove(query, Activity.class,Consts.MONGODB_ACTIVITY_COLLECTIN_NAME); // delete inserted activity by activityId in mongodb
//
//    }
//    @Test
//    public void testInsertActivityDetails(){
//        String jsonStringActivityDetail="{\n" +
//                "  \"summary\": {\n" +
//                "    \"durationInSeconds\": 10,\n" +
//                "    \"averageSpeedInMetersPerSecond\": 1.362,\n" +
//                "    \"averageHeartRateInBeatsPerMinute\": 88,\n" +
//                "    \"distanceInMeters\": 13.39,\n" +
//                "    \"activityName\": \"Walking\",\n" +
//                "    \"deviceName\": \"forerunner935\",\n" +
//                "    \"steps\": 4,\n" +
//                "    \"averageRunCadenceInStepsPerMinute\": 25.09375,\n" +
//                "    \"averagePaceInMinutesPerKilometer\": 12.236906,\n" +
//                "    \"activityId\": 11,\n" +
//                "    \"startTimeInSeconds\": 1650195177,\n" +
//                "    \"startTimeOffsetInSeconds\": 36000,\n" +
//                "    \"maxPaceInMinutesPerKilometer\": 4.8676014,\n" +
//                "    \"maxHeartRateInBeatsPerMinute\": 93,\n" +
//                "    \"maxRunCadenceInStepsPerMinute\": 138,\n" +
//                "    \"maxSpeedInMetersPerSecond\": 3.424,\n" +
//                "    \"activityType\": \"WALKING\"\n" +
//                "  },\n" +
//                "  \"activityId\": 11,\n" +
//                "  \"userAccessToken\": \"7e88865e-eb7d-47c8-8418-80cd24bbd4e6\",\n" +
//                "  \"summaryId\": \"11-detail\",\n" +
//                "  \"laps\": [\n" +
//                "    {\n" +
//                "      \"startTimeInSeconds\": 1650195177\n" +
//                "    }\n" +
//                "  ],\n" +
//                "  \"userId\": \"898329394\",\n" +
//                "  \"samples\": [\n" +
//                "    {\n" +
//                "      \"startTimeInSeconds\": 1650195177,\n" +
//                "      \"timerDurationInSeconds\": 0,\n" +
//                "      \"movingDurationInSeconds\": 0,\n" +
//                "      \"heartRate\": 84,\n" +
//                "      \"elevationInMeters\": 33.20000076293945,\n" +
//                "      \"speedMetersPerSecond\": 0,\n" +
//                "      \"clockDurationInSeconds\": 0,\n" +
//                "      \"stepsPerMinute\": 0,\n" +
//                "      \"airTemperatureCelcius\": 32,\n" +
//                "      \"totalDistanceInMeters\": 0,\n" +
//                "      \"latitudeInDegree\": -37.813,\n" +
//                "      \"longitudeInDegree\":144.96\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"startTimeInSeconds\": 1650195178,\n" +
//                "      \"timerDurationInSeconds\": 1,\n" +
//                "      \"movingDurationInSeconds\": 0,\n" +
//                "      \"heartRate\": 86,\n" +
//                "      \"elevationInMeters\": 33.20000076293945,\n" +
//                "      \"speedMetersPerSecond\": 0,\n" +
//                "      \"clockDurationInSeconds\": 1,\n" +
//                "      \"stepsPerMinute\": 0,\n" +
//                "      \"airTemperatureCelcius\": 32,\n" +
//                "      \"totalDistanceInMeters\": 0,\n" +
//                "      \"latitudeInDegree\": -37.815,\n" +
//                "      \"longitudeInDegree\":144.95\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"startTimeInSeconds\": 1650195182,\n" +
//                "      \"timerDurationInSeconds\": 5,\n" +
//                "      \"movingDurationInSeconds\": 0,\n" +
//                "      \"heartRate\": 129,\n" +
//                "      \"elevationInMeters\": 31.799999237060547,\n" +
//                "      \"speedMetersPerSecond\": 0,\n" +
//                "      \"clockDurationInSeconds\": 5,\n" +
//                "      \"stepsPerMinute\": 0,\n" +
//                "      \"airTemperatureCelcius\": 32,\n" +
//                "      \"totalDistanceInMeters\": 0,\n" +
//                "      \"latitudeInDegree\": -37.816,\n" +
//                "      \"longitudeInDegree\":144.94\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"startTimeInSeconds\": 1650195185,\n" +
//                "      \"timerDurationInSeconds\": 8,\n" +
//                "      \"movingDurationInSeconds\": 3,\n" +
//                "      \"heartRate\": 129,\n" +
//                "      \"elevationInMeters\": 31,\n" +
//                "      \"speedMetersPerSecond\": 3.4240000247955322,\n" +
//                "      \"clockDurationInSeconds\": 8,\n" +
//                "      \"stepsPerMinute\": 0,\n" +
//                "      \"airTemperatureCelcius\": 32,\n" +
//                "      \"totalDistanceInMeters\": 6.539999961853027,\n" +
//                "      \"latitudeInDegree\": -37.817,\n" +
//                "      \"longitudeInDegree\":144.93\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"startTimeInSeconds\": 1650195187,\n" +
//                "      \"timerDurationInSeconds\": 10,\n" +
//                "      \"movingDurationInSeconds\": 5,\n" +
//                "      \"heartRate\": 112,\n" +
//                "      \"elevationInMeters\": 31,\n" +
//                "      \"speedMetersPerSecond\": 3.4240000247955322,\n" +
//                "      \"clockDurationInSeconds\": 10,\n" +
//                "      \"stepsPerMinute\": 0,\n" +
//                "      \"airTemperatureCelcius\": 32,\n" +
//                "      \"totalDistanceInMeters\": 13.390000343322754,\n" +
//                "      \"latitudeInDegree\": -37.818,\n" +
//                "      \"longitudeInDegree\":144.92\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}";
//        ActivityDetails activityDetails=JSONObject.parseObject(jsonStringActivityDetail,ActivityDetails.class);
//        activityDaoImpl.insertActivityDetails(activityDetails);
//
//        Query query = Query.query(Criteria.where("activityId").is(Long.valueOf("11")));
//        ActivityDetails actualActivityDetails= mongoTemplate.findOne(query, ActivityDetails.class, Consts.MONGODB_ACTIVITY_DETAILS_COLLECTIN_NAME); // find activityDetails by activityId
//        assertEquals(activityDetails.toString(),actualActivityDetails.toString());
//        mongoTemplate.remove(query, ActivityDetails.class,Consts.MONGODB_ACTIVITY_DETAILS_COLLECTIN_NAME); // delete inserted activity details by activityId in mongodb
//
//
//    }
//    @Test
//    public void testSelectActivityDetailsByActivityId(){
//        String stringActivityDetails="{\n" +
//                "  \"summary\": {\n" +
//                "    \"durationInSeconds\": 10,\n" +
//                "    \"averageSpeedInMetersPerSecond\": 1.362,\n" +
//                "    \"averageHeartRateInBeatsPerMinute\": 88,\n" +
//                "    \"distanceInMeters\": 13.39,\n" +
//                "    \"activityName\": \"Running\",\n" +
//                "    \"deviceName\": \"forerunner935\",\n" +
//                "    \"steps\": 4,\n" +
//                "    \"averageRunCadenceInStepsPerMinute\": 25.09375,\n" +
//                "    \"averagePaceInMinutesPerKilometer\": 12.236906,\n" +
//                "    \"activityId\": 10,\n" +
//                "    \"startTimeInSeconds\": 1650195177,\n" +
//                "    \"startTimeOffsetInSeconds\": 36000,\n" +
//                "    \"maxPaceInMinutesPerKilometer\": 4.8676014,\n" +
//                "    \"maxHeartRateInBeatsPerMinute\": 93,\n" +
//                "    \"maxRunCadenceInStepsPerMinute\": 138,\n" +
//                "    \"maxSpeedInMetersPerSecond\": 3.424,\n" +
//                "    \"activityType\": \"RUNNING\"\n" +
//                "  },\n" +
//                "  \"activityId\": 10,\n" +
//                "  \"userAccessToken\": \"2d7c25b2-27f5-4974-a82f-2a9e41502b9f\",\n" +
//                "  \"summaryId\": \"10-detail\",\n" +
//                "  \"laps\": [\n" +
//                "    {\n" +
//                "      \"startTimeInSeconds\": 1650195177\n" +
//                "    }\n" +
//                "  ],\n" +
//                "  \"userId\": \"898329394\",\n" +
//                "  \"samples\": [\n" +
//                "    {\n" +
//                "      \"startTimeInSeconds\": 1650195177,\n" +
//                "      \"timerDurationInSeconds\": 0,\n" +
//                "      \"movingDurationInSeconds\": 0,\n" +
//                "      \"heartRate\": 84,\n" +
//                "      \"elevationInMeters\": 33.20000076293945,\n" +
//                "      \"speedMetersPerSecond\": 0,\n" +
//                "      \"clockDurationInSeconds\": 0,\n" +
//                "      \"stepsPerMinute\": 0,\n" +
//                "      \"airTemperatureCelcius\": 32,\n" +
//                "      \"totalDistanceInMeters\": 0,\n" +
//                "      \"latitudeInDegree\": -37.813,\n" +
//                "      \"longitudeInDegree\":144.96\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"startTimeInSeconds\": 1650195178,\n" +
//                "      \"timerDurationInSeconds\": 1,\n" +
//                "      \"movingDurationInSeconds\": 0,\n" +
//                "      \"heartRate\": 86,\n" +
//                "      \"elevationInMeters\": 33.20000076293945,\n" +
//                "      \"speedMetersPerSecond\": 0,\n" +
//                "      \"clockDurationInSeconds\": 1,\n" +
//                "      \"stepsPerMinute\": 0,\n" +
//                "      \"airTemperatureCelcius\": 32,\n" +
//                "      \"totalDistanceInMeters\": 0,\n" +
//                "      \"latitudeInDegree\": -37.815,\n" +
//                "      \"longitudeInDegree\":144.95\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"startTimeInSeconds\": 1650195182,\n" +
//                "      \"timerDurationInSeconds\": 5,\n" +
//                "      \"movingDurationInSeconds\": 0,\n" +
//                "      \"heartRate\": 129,\n" +
//                "      \"elevationInMeters\": 31.799999237060547,\n" +
//                "      \"speedMetersPerSecond\": 0,\n" +
//                "      \"clockDurationInSeconds\": 5,\n" +
//                "      \"stepsPerMinute\": 0,\n" +
//                "      \"airTemperatureCelcius\": 32,\n" +
//                "      \"totalDistanceInMeters\": 0,\n" +
//                "      \"latitudeInDegree\": -37.816,\n" +
//                "      \"longitudeInDegree\":144.94\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"startTimeInSeconds\": 1650195185,\n" +
//                "      \"timerDurationInSeconds\": 8,\n" +
//                "      \"movingDurationInSeconds\": 3,\n" +
//                "      \"heartRate\": 129,\n" +
//                "      \"elevationInMeters\": 31,\n" +
//                "      \"speedMetersPerSecond\": 3.4240000247955322,\n" +
//                "      \"clockDurationInSeconds\": 8,\n" +
//                "      \"stepsPerMinute\": 0,\n" +
//                "      \"airTemperatureCelcius\": 32,\n" +
//                "      \"totalDistanceInMeters\": 6.539999961853027,\n" +
//                "      \"latitudeInDegree\": -37.817,\n" +
//                "      \"longitudeInDegree\":144.93\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"startTimeInSeconds\": 1650195187,\n" +
//                "      \"timerDurationInSeconds\": 10,\n" +
//                "      \"movingDurationInSeconds\": 5,\n" +
//                "      \"heartRate\": 112,\n" +
//                "      \"elevationInMeters\": 31,\n" +
//                "      \"speedMetersPerSecond\": 3.4240000247955322,\n" +
//                "      \"clockDurationInSeconds\": 10,\n" +
//                "      \"stepsPerMinute\": 0,\n" +
//                "      \"airTemperatureCelcius\": 32,\n" +
//                "      \"totalDistanceInMeters\": 13.390000343322754,\n" +
//                "      \"latitudeInDegree\": -37.818,\n" +
//                "      \"longitudeInDegree\":144.92\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}";
//        ActivityDetails activityDetails=JSONObject.parseObject(stringActivityDetails,ActivityDetails.class);
//        ActivityDetails actualActivityDetails=activityDaoImpl.selectActivityDetailsByActivityId("10");
//        assertEquals(activityDetails.toString(),actualActivityDetails.toString());
//
//    }
//    @Test
//    public void testSelectActivityDetailsByAccessTokenAndType(){
//        String type="RUNNING";
//        List<Activity> activities= activityDaoImpl.selectActivityDetailsByAccessTokenAndType(accessToken,type);
//        String jsonStringActivity="[{\n" +
//                "  \"durationInSeconds\": 10,\n" +
//                "  \"averageSpeedInMetersPerSecond\": 0.233,\n" +
//                "  \"averageHeartRateInBeatsPerMinute\": 92,\n" +
//                "  \"distanceInMeters\": 2.42,\n" +
//                "  \"activityName\": \"Running\",\n" +
//                "  \"userId\": \"898329394\",\n" +
//                "  \"deviceName\": \"forerunner935\",\n" +
//                "  \"averagePaceInMinutesPerKilometer\": 71.53076,\n" +
//                "  \"activityId\": 10,\n" +
//                "  \"startTimeInSeconds\": 1650191523,\n" +
//                "  \"userAccessToken\": \"2d7c25b2-27f5-4974-a82f-2a9e41502b9f\",\n" +
//                "  \"startTimeOffsetInSeconds\": 36000,\n" +
//                "  \"maxPaceInMinutesPerKilometer\": 6.8956003,\n" +
//                "  \"maxHeartRateInBeatsPerMinute\": 95,\n" +
//                "  \"summaryId\": \"8654408419\",\n" +
//                "  \"maxSpeedInMetersPerSecond\": 2.417,\n" +
//                "  \"activityType\": \"RUNNING\"\n" +
//                "}]";
//        List<Activity> activityList=JSONObject.parseArray(jsonStringActivity,Activity.class);
//        assertEquals(activities.toString(),activityList.toString());
//    }
//    @Test
//    public void testSelectActivityByAccessTokenAndDate(){
//
//    }
//}