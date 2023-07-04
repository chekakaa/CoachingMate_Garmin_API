package coachingmateanalytics.coachingmate.entity;

import lombok.Data;

@Data
public class Laps {

    private long startTimeInSeconds;
    public void setStartTimeInSeconds(long startTimeInSeconds) {
        this.startTimeInSeconds = startTimeInSeconds;
    }
    public long getStartTimeInSeconds() {
        return startTimeInSeconds;
    }

}