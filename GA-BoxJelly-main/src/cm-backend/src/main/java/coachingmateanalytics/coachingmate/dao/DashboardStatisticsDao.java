package coachingmateanalytics.coachingmate.dao;

import coachingmateanalytics.coachingmate.common.utils.Consts;
import coachingmateanalytics.coachingmate.entity.Activity;
import coachingmateanalytics.coachingmate.entity.DashboardStastic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * DashboardStatisticsDao is responsible for managing DashboardStastic data in the database.
 */
@Component
public class DashboardStatisticsDao {
    @Autowired
    private MongoTemplate mongoTemplate; // Inject mongoTemplate

    /**
     * Retrieves a DashboardStastic object based on the user's access token.
     *
     * @param accessToken the user's access token
     * @return the DashboardStastic object associated with the access token
     */
    public DashboardStastic selectDashboardStaticByAccessToken(String accessToken) {
        Query query = Query.query(Criteria.where("userAccessToken").is(accessToken)); // Query by accessToken
        return mongoTemplate.findOne(query, DashboardStastic.class, Consts.MONGODB_DASHBOARD_STATISTICS_COLLECTION_NAME); // Find DashboardStastic by accessToken
    }

    /**
     * Inserts a DashboardStastic object into the database.
     *
     * @param dashboardStastic the DashboardStastic object to be inserted
     * @return the saved DashboardStastic object
     */
    public DashboardStastic insertDashboardStatic(DashboardStastic dashboardStastic) {
        Query query = Query.query(Criteria.where("userAccessToken").is(dashboardStastic.getUserAccessToken()));
        mongoTemplate.remove(query, Consts.MONGODB_DASHBOARD_STATISTICS_COLLECTION_NAME); // Remove existing DashboardStastic for the given userAccessToken
        return mongoTemplate.save(dashboardStastic, Consts.MONGODB_DASHBOARD_STATISTICS_COLLECTION_NAME); // Save new DashboardStastic object
    }
}