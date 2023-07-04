package coachingmateanalytics.coachingmate.entity;

/**
 * ActivityFile class for CoachingMate application.
 * Represents an activity file containing data such as user info, activity details, and file type.
 */
public class ActivityFile {

    private String userId;
    private String userAccessToken;
    private String summaryId;
    private String fileType;
    private String callbackURL;
    private long startTimeInSeconds;
    private long activityId;
    private String activityName;
    private boolean manual;
    private String activityDescription;

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

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getCallbackURL() {
        return callbackURL;
    }

    public void setCallbackURL(String callbackURL) {
        this.callbackURL = callbackURL;
    }

    public long getStartTimeInSeconds() {
        return startTimeInSeconds;
    }

    public void setStartTimeInSeconds(long startTimeInSeconds) {
        this.startTimeInSeconds = startTimeInSeconds;
    }

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public boolean getManual() {
        return manual;
    }

    public void setManual(boolean manual) {
        this.manual = manual;
    }

    public String getDes() {return activityDescription;}

    public void setDes() {this.activityDescription = activityDescription;}

    @Override
    public String toString() {
        return "ActivityFile{" +
                "userId='" + userId + '\'' +
                ", userAccessToken='" + userAccessToken + '\'' +
                ", summaryId='" + summaryId + '\'' +
                ", fileType='" + fileType + '\'' +
                ", callbackURL='" + callbackURL + '\'' +
                ", startTimeInSeconds=" + startTimeInSeconds +
                ", activityId=" + activityId +
                ", activityName='" + activityName + '\'' +
                ", manual=" + manual + '\'' +
                ", activityDescription=" + activityDescription + '+' +
                '}';
    }
}
