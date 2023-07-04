package coachingmateanalytics.coachingmate.entity;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Data
public class DashboardStastic {
    String userAccessToken;
    int ttlActivityTimes;
    int ttlRunningTimes;
    int ttlRiddingTimes;
    int ttlSwimmingTimes;
    double ttlActivityTime;
    double ttlRunningTime;
    double ttlRiddingTime;
    double ttlSwimmingTime;
    int[] allActivityTime;
    int[] runningActivityTime;
    int[] swimmingActivityTime;
    int[] riddingActivityTime;
//    RadarActivity[] radarActivities;
    Map<String, ActivityTimeChart> activityTimeChartMap;
    Map<String, RadarActivity> radarActivities;
    List<double[]> hearRateZones;


    @Data
    public class ActivityTimeChart{
        int[] actual;
        int[] except;
    }

    @Data
    public class RadarActivity{
        double time;
        int calories;
        double distance;
        double avgSpeed;
        double peakSpeed;
        int avgHeartRate;

    }
    @Override
    public String toString(){
        StringBuffer hearRateZonesString=new StringBuffer();
        hearRateZonesString.append("[");
        for (int i = 0; i < hearRateZones.size(); i++) {
            hearRateZonesString.append(Arrays.toString(hearRateZones.get(i)));
            if (i<hearRateZones.size()-1){
                hearRateZonesString.append(", ");
            }
        }
        hearRateZonesString.append("]");
        String result="DashboardStastic(userAccessToken=bd5b13a9-9eed-4204-a807-94bf0eb06356, ttlActivityTimes=3, ttlRunningTimes=3, ttlRiddingTimes=0, ttlSwimmingTimes=0, ttlActivityTime=0.0, ttlRunningTime=0.0, ttlRiddingTime=0.0, ttlSwimmingTime=0.0, allActivityTime=null, runningActivityTime=null, swimmingActivityTime=null, riddingActivityTime=null, activityTimeChartMap={RiddingTime=DashboardStastic.ActivityTimeChart(actual=[0, 0, 0, 0, 0, 0, 0], except=[90, 90, 90, 90, 90, 90, 90]), AllTime=DashboardStastic.ActivityTimeChart(actual=[0, 0, 0, 0, 0, 0, 0], except=[90, 90, 90, 90, 90, 90, 90]), SwimmingTime=DashboardStastic.ActivityTimeChart(actual=[0, 0, 0, 0, 0, 0, 0], except=[90, 90, 90, 90, 90, 90, 90]), RunningTime=DashboardStastic.ActivityTimeChart(actual=[0, 0, 0, 0, 0, 0, 0], except=[90, 90, 90, 90, 90, 90, 90])}, radarActivities={RUNNING=DashboardStastic.RadarActivity(time=0.0, calories=0, distance=7.26, avgSpeed=0.0, peakSpeed=2.417, avgHeartRate=0), OPEN_WATER_SWIMMING=DashboardStastic.RadarActivity(time=0.0, calories=0, distance=0.0, avgSpeed=0.0, peakSpeed=0.0, avgHeartRate=0), ROAD_BIKING=DashboardStastic.RadarActivity(time=0.0, calories=0, distance=0.0, avgSpeed=0.0, peakSpeed=0.0, avgHeartRate=0)}," +
                " hearRateZones=" +
                hearRateZonesString;
        return result;
    }
}
