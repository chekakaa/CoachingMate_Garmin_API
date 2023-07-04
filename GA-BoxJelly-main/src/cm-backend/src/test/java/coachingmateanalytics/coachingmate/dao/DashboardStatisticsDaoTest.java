package coachingmateanalytics.coachingmate.dao;

import coachingmateanalytics.coachingmate.common.utils.Consts;
import coachingmateanalytics.coachingmate.entity.DashboardStastic;
import coachingmateanalytics.coachingmate.entity.UserPartner;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class DashboardStatisticsDaoTest {
    @Autowired
    private DashboardStatisticsDao dashboardStatisticsDao;

    @Autowired
    private MongoTemplate mongoTemplate;

    private DashboardStastic testDashboardStatistics;

    @BeforeEach
    public void setUp() {
        // create a test dashboard stastic
        String dashboardStatisticsString="{\n" +
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
        testDashboardStatistics= JSONObject.parseObject(dashboardStatisticsString,DashboardStastic.class);
        testDashboardStatistics.setUserAccessToken("testAccessToken");
    }

    @Test
    public void testInsertDashboardStatistics(){
        setUp();
        dashboardStatisticsDao.insertDashboardStatic(testDashboardStatistics);
        Query query = new Query(Criteria.where("userAccessToken").is(testDashboardStatistics.getUserAccessToken()));
        DashboardStastic dashboardStatisticsFromMongoDB = mongoTemplate.findOne(query, DashboardStastic.class, Consts.MONGODB_DASHBOARD_STATISTICS_COLLECTION_NAME);
        assertEquals(testDashboardStatistics.toString(),dashboardStatisticsFromMongoDB.toString());
        mongoTemplate.remove(query, DashboardStastic.class,Consts.MONGODB_DASHBOARD_STATISTICS_COLLECTION_NAME); // delete dashboard by userAccessToken in mongodb
    }
    @Test
    public void testSelectDashboardStaticByAccessToken(){
        setUp();
        dashboardStatisticsDao.insertDashboardStatic(testDashboardStatistics);
        DashboardStastic dashboardStatistics=dashboardStatisticsDao.selectDashboardStaticByAccessToken("testAccessToken");
        assertEquals(testDashboardStatistics.toString(),dashboardStatistics.toString());

        Query query = new Query(Criteria.where("userAccessToken").is(testDashboardStatistics.getUserAccessToken()));
        mongoTemplate.remove(query, DashboardStastic.class,Consts.MONGODB_DASHBOARD_STATISTICS_COLLECTION_NAME); // delete dashboard by userAccessToken in mongodb

    }
}
