package coachingmateanalytics.coachingmate.common.utils;

/**
 * A utility class containing constants used throughout the application.
 */
public final class Consts {
    public static final String OAUTH_VERIFIER = "oauth_verifier";
    public static final String VARIABLE_DELIMTER = "&";
    public static final String VALUE_DELIMTER = "=";
    public static final String URL_DELIMTER = "?";
    public static final String OAUTH_TOKEN = "oauth_token";
    public static final String CALLBACK_URL = "oauth_callback";
    public static final String MONGODB_TOKEN_COLLECTIN_NAME = "requestToken";
    public static final String MONGODB_USER_COLLECTIN_NAME = "user";
    public static final String MONGODB_ACTIVITY_COLLECTIN_NAME = "activity";
    public static final String MONGODB_ACTIVITY_DETAILS_COLLECTIN_NAME = "activity_details";
    public static final String MONGODB_DASHBOARD_STATISTICS_COLLECTION_NAME = "dashboard_statistics";

    public static final String[] URL_WHITELIST = {
            "/login*",
            "/register*",
            "/auth/accessToken*",
            "/verifyEmail*",
            "/requestReset*",
            "/requestReset/reset*",
            "/pushActivity*",
            "/pushActivityDetail*",
            "/pushFile*"
    };

    // Private constructor to prevent instantiation of utility class
    private Consts() {
    }
}
