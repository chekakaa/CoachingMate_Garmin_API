package coachingmateanalytics.coachingmate.common.utils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * A utility class containing methods for working with dates.
 */
public final class DateUtils {

    /**
     * Returns the start of the day for a given date.
     *
     * @param date the input date
     * @return the start of the day
     */
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return localDateTimeToDate(startOfDay);
    }

    /**
     * Returns the end of the day for a given date.
     *
     * @param date the input date
     * @return the end of the day
     */
    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return localDateTimeToDate(endOfDay);
    }

    /**
     * Converts a Date object to a LocalDateTime object.
     *
     * @param date the input date
     * @return the converted LocalDateTime object
     */
    private static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * Converts a LocalDateTime object to a Date object.
     *
     * @param localDateTime the input LocalDateTime object
     * @return the converted Date object
     */
    private static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    // Private constructor to prevent instantiation of utility class
    private DateUtils() {
    }
}
