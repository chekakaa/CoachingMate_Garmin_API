package coachingmateanalytics.coachingmate.entity;

import lombok.Data;

/**
 * Represents a sample containing various attributes related to sports or fitness activities.
 * 
 * 
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class Samples {

    private long startTimeInSeconds;
    private double elevationInMeters;
    private int airTemperatureCelcius;
    private int heartRate;
    private double speedMetersPerSecond;
    private int stepsPerMinute;
    private double totalDistanceInMeters;
    private int timerDurationInSeconds;
    private int clockDurationInSeconds;
    private int movingDurationInSeconds;
    private double latitudeInDegree;
    private double longitudeInDegree;
    private double swimCadenceInStrokesPerMinute;
    private double powerInWatts;
    private double bikeCadenceInRPM;

}
