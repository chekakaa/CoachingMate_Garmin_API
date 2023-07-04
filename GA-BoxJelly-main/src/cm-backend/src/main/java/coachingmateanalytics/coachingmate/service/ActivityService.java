package coachingmateanalytics.coachingmate.service;

import coachingmateanalytics.coachingmate.entity.Activity;
import coachingmateanalytics.coachingmate.entity.ActivityDetails;
import coachingmateanalytics.coachingmate.entity.DashboardStastic;
import coachingmateanalytics.coachingmate.entity.Statistic;

import java.util.List;

/**
 * ActivityService interface for CoachingMate application.
 */
public interface ActivityService {
    /**
     * Inserts an activity into the database.
     */
    void insertActivity(Activity activity);

    /**
     * Inserts activity details into the database.
     */
    void insertActivityDetail(ActivityDetails activityDetails);

    /**
     * Retrieves a list of activities by access token.
     */
    List<Activity> selectActivityByAccessToken(String accessToken);

    /**
     * Retrieves a list of activity details by access token.
     */
    List<ActivityDetails> selectActivityDetailsByAccessToken(String accessToken);

    /**
     * Retrieves activity details by activity ID.
     */
    ActivityDetails selectActivityDetailsByActivityId(String activityId);

    /**
     * Retrieves a list of activities by access token and type.
     */
    List<Activity> selectActivityByAccessTokenAndType(String accessToken, String type);

    /**
     * Generates statistics for activities associated with the given access token.
     */
    void statisticsActivities(String accessToken);

    /**
     * Retrieves dashboard statistics by access token.
     */
    DashboardStastic selectDashboardStatisticsByAccessToken(String accessToken);
}
