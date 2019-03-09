package org.infinitec.test.weatherforecast.constants;

/**
 * @author Sameera.Manorathna
 */

public class Constants {
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final int NO_OF_EXPECTED_FORECAST_DAYS = 3;

    /* external API related constant values */
    public static final String API_KEY_TEXT = "APPID";
    public static final String CITY_NAME_QUERY_PARAM_TEXT = "q";

    public static final String SERVER_ERROR = "Requesting Data Not Found";

    /* day time starts with 6:00 h and night time ends with 5:59 h */
    public static final int DAY_TIME_STARTING_HOUR = 6;
    public static final int DAY_TIME_STARTING_MINUTE = 0;

    /* night time starts with 18:00 h and day time ends with 17:59 h */
    public static final int NIGHT_TIME_STARTING_HOUR = 17;
    public static final int NIGHT_TIME_STARTING_MINUTE = 59;

}
