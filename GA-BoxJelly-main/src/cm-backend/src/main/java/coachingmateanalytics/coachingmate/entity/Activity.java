package coachingmateanalytics.coachingmate.entity;

import lombok.Data;

/**
 * Activity entity class for CoachingMate application.
 * Represents an activity performed by a user.
 */
@Data
public class Activity {
    private String parentSummaryId;
    private String userId;
    private String userAccessToken;
    private String summaryId;
    private long activityId;
    private String activityName;
    private int durationInSeconds;
    private long startTimeInSeconds;
    private int startTimeOffsetInSeconds;
    private String activityType;
    private int averageHeartRateInBeatsPerMinute;
    private String deviceName;
    private int maxHeartRateInBeatsPerMinute;
    private double averageBikeCadenceInRoundsPerMinute;
    private int activeKilocalories;
    private double averageSpeedInMetersPerSecond;
    private double averagePaceInMinutesPerKilometer;
    private double maxBikeCadenceInRoundsPerMinute;
    private double maxPaceInMinutesPerKilometer;
    private double maxSpeedInMetersPerSecond;
    private double startingLatitudeInDegree;
    private double startingLongitudeInDegree;
    private double totalElevationGainInMeters;
    private double totalElevationLossInMeters;
    private double averageSwimCadenceInStrokesPerMinute;
    private double averageRunCadenceInStepsPerMinute;
    private double distanceInMeters;
    private double maxRunCadenceInStepsPerMinute;
    private long steps;
    private boolean manual;
}
