package coachingmateanalytics.coachingmate.entity;

/**
 * Represents the status of a program for a specific user.
 */
public class Program_status {
    private String program_id;
    private String user_id;
    private Number status;

    /**
     * Constructs a Program_status object with the given program ID, user ID, and status.
     *
     * @param program_id The program ID
     * @param user_id    The user ID
     * @param status     The status
     */
    public Program_status(String program_id, String user_id, Number status) {
        this.program_id = program_id;
        this.user_id = user_id;
        this.status = status;
    }

    /**
     * Returns the program ID.
     *
     * @return The program ID
     */
    public String getProgram_id() {
        return program_id;
    }

    /**
     * Sets the program ID.
     *
     * @param program_id The program ID to set
     */
    public void setProgram_id(String program_id) {
        this.program_id = program_id;
    }

    /**
     * Returns the user ID.
     *
     * @return The user ID
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * Sets the user ID.
     *
     * @param user_id The user ID to set
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * Returns the status.
     *
     * @return The status
     */
    public Number getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status The status to set
     */
    public void setStatus(Number status) {
        this.status = status;
    }
}
