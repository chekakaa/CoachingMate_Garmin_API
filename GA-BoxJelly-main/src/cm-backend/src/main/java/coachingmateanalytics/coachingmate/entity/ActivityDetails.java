package coachingmateanalytics.coachingmate.entity;

import java.util.List;

/**
 * ActivityDetails entity class for CoachingMate application.
 * Holds detailed information about a user's activities including summary, samples, and laps.
 */
public class ActivityDetails {
    private String userId;
    private String userAccessToken;
    private String summaryId;
    private long activityId;
    private Summary summary;
    private List<Samples> samples;
    private List<Laps> laps;

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAccessToken() {
        return userAccessToken;
    }

    public void setUserAccessToken(String userAccessToken) {
        this.userAccessToken = userAccessToken;
    }

    public String getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(String summaryId) {
        this.summaryId = summaryId;
    }

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public List<Samples> getSamples() {
        return samples;
    }

    public void setSamples(List<Samples> samples) {
        this.samples = samples;
    }

    public List<Laps> getLaps() {
        return laps;
    }

    public void setLaps(List<Laps> laps) {
        this.laps = laps;
    }

    @Override
    public String toString() {
        return "ActivityDetails{" +
                "userId='" + userId + '\'' +
                ", userAccessToken='" + userAccessToken + '\'' +
                ", summaryId='" + summaryId + '\'' +
                ", activityId=" + activityId +
                ", summary=" + summary +
                ", samples=" + samples +
                ", laps=" + laps +
                '}';
    }
}
