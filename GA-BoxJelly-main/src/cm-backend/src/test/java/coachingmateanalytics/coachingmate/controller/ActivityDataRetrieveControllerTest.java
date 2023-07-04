package coachingmateanalytics.coachingmate.controller;

import coachingmateanalytics.coachingmate.entity.Activity;
import coachingmateanalytics.coachingmate.entity.ActivityDetails;
import coachingmateanalytics.coachingmate.entity.DashboardStastic;
import coachingmateanalytics.coachingmate.service.ActivityService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.json.JSONString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
//import org.junit.platform.runner.JUnitPlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import coachingmateanalytics.coachingmate.controller.ActivityDataRetrieveController;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ActivityDataRetrieveControllerTest{
    @Autowired
    private ActivityDataRetrieveController activityDataRetrieveController;
    String accessToken="2d7c25b2-27f5-4974-a82f-2a9e41502b9f";
    @Resource
    ActivityService activityService;

    @Test
    void testGetActivityByAccessToken() {
        ResponseEntity<List<Activity>> activitiesResponse = activityDataRetrieveController.getActivityByAccessToken(accessToken);
        String jsonStringActivity="[{\n" +
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
                "}]";
        List<Activity> activityList=JSONObject.parseArray(jsonStringActivity,Activity.class);

        assertEquals(ResponseEntity.ok(activityList).toString(), activitiesResponse.toString());
    }

    @Test
    void testGetActivityDetailsByAccessToken() {
        ResponseEntity<List<ActivityDetails>> activityDetailsListResponse=activityDataRetrieveController.getActivityDetailsByAccessToken(accessToken);

        String jsonStringActivityDetails="[\n" +
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
                "}\n" +
                "]";


        List<ActivityDetails> activityDetailsList=JSONObject.parseArray(jsonStringActivityDetails,ActivityDetails.class);

        assertEquals (ResponseEntity.ok(activityDetailsList).toString(),activityDetailsListResponse.toString());



    }

    @Test
    void testGetActivityDetailsByActivityId() {
        String activityId="10";
        ActivityDetails activityDetails=activityDataRetrieveController.getActivityDetailsByActivityId(activityId);
        String jsonStringActivityDetails="{\n" +
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
                "}";
        ActivityDetails activityDetailsActual=JSONObject.parseObject(jsonStringActivityDetails,ActivityDetails.class);
        assertEquals(activityDetails.toString(),activityDetailsActual.toString());

    }


    @Test
    void testGetActivityByAccessTokenAndType(){
        String type="RUNNING";
        List<Activity> activities=activityDataRetrieveController.getActivityByAccessTokenAndType(accessToken,type);
        String jsonStringActivity="[{\n" +
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
                "}]";
        List<Activity> activityList=JSONObject.parseArray(jsonStringActivity,Activity.class);
        assertEquals(activities.toString(),activityList.toString());
    }

    @Test
    void testGetDashboardStatisticsByAccessToken(){
        DashboardStastic dashboardStastic= activityDataRetrieveController.getDashboardStatisticsByAccessToken(accessToken);
        String dashboardString="{\n" +
                "    \"userAccessToken\" : \"2d7c25b2-27f5-4974-a82f-2a9e41502b9f\",\n" +
                "    \"ttlActivityTimes\" : 3,\n" +
                "    \"ttlRunningTimes\" : 3,\n" +
                "    \"ttlRiddingTimes\" : 0,\n" +
                "    \"ttlSwimmingTimes\" : 0,\n" +
                "    \"ttlActivityTime\" : 0.0,\n" +
                "    \"ttlRunningTime\" : 0.0,\n" +
                "    \"ttlRiddingTime\" : 0.0,\n" +
                "    \"ttlSwimmingTime\" : 0.0,\n" +
                "    \"activityTimeChartMap\" : {\n" +
                "        \"RiddingTime\" : {\n" +
                "            \"actual\" : [\n" +
                "                0,\n" +
                "                0,\n" +
                "                0,\n" +
                "                0,\n" +
                "                0,\n" +
                "                0,\n" +
                "                0\n" +
                "            ],\n" +
                "            \"except\" : [\n" +
                "                90,\n" +
                "                90,\n" +
                "                90,\n" +
                "                90,\n" +
                "                90,\n" +
                "                90,\n" +
                "                90\n" +
                "            ]\n" +
                "        },\n" +
                "        \"AllTime\" : {\n" +
                "            \"actual\" : [\n" +
                "                0,\n" +
                "                0,\n" +
                "                0,\n" +
                "                0,\n" +
                "                0,\n" +
                "                0,\n" +
                "                0\n" +
                "            ],\n" +
                "            \"except\" : [\n" +
                "                90,\n" +
                "                90,\n" +
                "                90,\n" +
                "                90,\n" +
                "                90,\n" +
                "                90,\n" +
                "                90\n" +
                "            ]\n" +
                "        },\n" +
                "        \"SwimmingTime\" : {\n" +
                "            \"actual\" : [\n" +
                "                0,\n" +
                "                0,\n" +
                "                0,\n" +
                "                0,\n" +
                "                0,\n" +
                "                0,\n" +
                "                0\n" +
                "            ],\n" +
                "            \"except\" : [\n" +
                "                90,\n" +
                "                90,\n" +
                "                90,\n" +
                "                90,\n" +
                "                90,\n" +
                "                90,\n" +
                "                90\n" +
                "            ]\n" +
                "        },\n" +
                "        \"RunningTime\" : {\n" +
                "            \"actual\" : [\n" +
                "                0,\n" +
                "                0,\n" +
                "                0,\n" +
                "                0,\n" +
                "                0,\n" +
                "                0,\n" +
                "                0\n" +
                "            ],\n" +
                "            \"except\" : [\n" +
                "                90,\n" +
                "                90,\n" +
                "                90,\n" +
                "                90,\n" +
                "                90,\n" +
                "                90,\n" +
                "                90\n" +
                "            ]\n" +
                "        }\n" +
                "    },\n" +
                "    \"radarActivities\" : {\n" +
                "        \"RUNNING\" : {\n" +
                "            \"time\" : 0.0,\n" +
                "            \"calories\" : 0,\n" +
                "            \"distance\" : 7.26,\n" +
                "            \"avgSpeed\" : 0.0,\n" +
                "            \"peakSpeed\" : 2.417,\n" +
                "            \"avgHeartRate\" : 0\n" +
                "        },\n" +
                "        \"OPEN_WATER_SWIMMING\" : {\n" +
                "            \"time\" : 0.0,\n" +
                "            \"calories\" : 0,\n" +
                "            \"distance\" : 0.0,\n" +
                "            \"avgSpeed\" : 0.0,\n" +
                "            \"peakSpeed\" : 0.0,\n" +
                "            \"avgHeartRate\" : 0\n" +
                "        },\n" +
                "        \"ROAD_BIKING\" : {\n" +
                "            \"time\" : 0.0,\n" +
                "            \"calories\" : 0,\n" +
                "            \"distance\" : 0.0,\n" +
                "            \"avgSpeed\" : 0.0,\n" +
                "            \"peakSpeed\" : 0.0,\n" +
                "            \"avgHeartRate\" : 0\n" +
                "        }\n" +
                "    },\n" +
                "    \"hearRateZones\" : [\n" +
                "        [\n" +
                "            0.0,\n" +
                "            0.0,\n" +
                "            0.0,\n" +
                "            0.0,\n" +
                "            0.0\n" +
                "        ],\n" +
                "        [\n" +
                "            0.0,\n" +
                "            0.0,\n" +
                "            0.0,\n" +
                "            0.0,\n" +
                "            0.0\n" +
                "        ],\n" +
                "        [\n" +
                "            0.0,\n" +
                "            0.0,\n" +
                "            0.0,\n" +
                "            0.0,\n" +
                "            0.0\n" +
                "        ],\n" +
                "        [\n" +
                "            0.0,\n" +
                "            0.0,\n" +
                "            0.0,\n" +
                "            0.0,\n" +
                "            0.0\n" +
                "        ],\n" +
                "        [\n" +
                "            0.0,\n" +
                "            0.0,\n" +
                "            0.0,\n" +
                "            0.0,\n" +
                "            0.0\n" +
                "        ],\n" +
                "        [\n" +
                "            0.0,\n" +
                "            0.0,\n" +
                "            0.0,\n" +
                "            0.0,\n" +
                "            0.0\n" +
                "        ],\n" +
                "        [\n" +
                "            0.0,\n" +
                "            0.0,\n" +
                "            0.0,\n" +
                "            0.0,\n" +
                "            0.0\n" +
                "        ]\n" +
                "    ],\n" +
                "}\n";
        DashboardStastic newDashboardStastic=JSONObject.parseObject(dashboardString,DashboardStastic.class);
        assertEquals(dashboardStastic.toString(),newDashboardStastic.toString());
    }
}
