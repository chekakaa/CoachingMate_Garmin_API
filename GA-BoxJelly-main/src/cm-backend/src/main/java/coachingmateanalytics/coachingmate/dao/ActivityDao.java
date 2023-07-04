package coachingmateanalytics.coachingmate.dao;

import coachingmateanalytics.coachingmate.entity.Activity;
import coachingmateanalytics.coachingmate.entity.ActivityDetails;
import coachingmateanalytics.coachingmate.entity.Statistic;

import java.util.Date;
import java.util.List;
import java.util.Map;
// work done:
// 1. add a brief description of the ActivityDao interface and its purpose.
// 2. adjust the code structure and format


/**
 * ActivityDao defines the data access operations for the Activity and ActivityDetails entities.
 * ActivityDetails is a sub-collection of Activity in the MongoDB database for the CoachingMate application.
 */
public interface ActivityDao {

    void saveActivity(Statistic activity);

    List<Activity> findAllByAccessToken(String accessToken);

    List<ActivityDetails> selectActivityDetailsByAccessToken(String accessToken);

    void insertActivity(Activity activity);

    void insertActivityDetails(ActivityDetails activityDetails);

    ActivityDetails selectActivityDetailsByActivityId(String activityId);

    List<Activity> selectActivityDetailsByAccessTokenAndType(String accessToken, String type);

    List<Activity> selectActivityByAccessTokenAndDate(String accessToken, Date date);

    Map<String, Object> getStatisticsData(String accessToken);
}
