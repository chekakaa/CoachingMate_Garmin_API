package coachingmateanalytics.coachingmate.entity;

import java.util.List;

/**
 * ActivityDetailsBean class for CoachingMate application.
 * Represents a list of ActivityDetails instances.
 */
public class ActivityDetailsBean {

    private List<ActivityDetails> activityDetails;

    // Getter and Setter
    public List<ActivityDetails> getActivityDetails() {
        return activityDetails;
    }

    public void setActivityDetails(List<ActivityDetails> activityDetails) {
        this.activityDetails = activityDetails;
    }
}
