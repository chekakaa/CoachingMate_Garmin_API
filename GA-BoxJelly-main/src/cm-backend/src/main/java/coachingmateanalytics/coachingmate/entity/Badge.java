package coachingmateanalytics.coachingmate.entity;

/**
 * Badge entity class for CoachingMate application.
 */
public class Badge {
    private String badge_id;
    private String url;
    private String title;

    /**
     * Constructor for the Badge class.
     * 
     * @param badge_id The unique identifier for the badge.
     * @param url The URL of the badge image.
     * @param title The title or name of the badge.
     */
    public Badge(String badge_id, String url, String title) {
        this.badge_id = badge_id;
        this.url = url;
        this.title = title;
    }

    /**
     * Gets the badge ID.
     * 
     * @return The unique identifier for the badge.
     */
    public String getBadge_id() {
        return badge_id;
    }

    /**
     * Sets the badge ID.
     * 
     * @param badge_id The unique identifier for the badge.
     */
    public void setBadge_id(String badge_id) {
        this.badge_id = badge_id;
    }

    /**
     * Gets the URL of the badge image.
     * 
     * @return The URL of the badge image.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the URL of the badge image.
     * 
     * @param url The URL of the badge image.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets the title or name of the badge.
     * 
     * @return The title or name of the badge.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title or name of the badge.
     * 
     * @param title The title or name of the badge.
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
