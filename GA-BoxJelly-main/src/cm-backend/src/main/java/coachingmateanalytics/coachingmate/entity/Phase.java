package coachingmateanalytics.coachingmate.entity;

/**
 * Phase class representing a single phase entity in the CoachingMate application.
 */
public class Phase {
    private String phase_id;
    private String phase_name;
    private String description;

    /**
     * Constructs a Phase object with the given parameters.
     *
     * @param phase_id the ID of the phase
     * @param phase_name the name of the phase
     * @param description the description of the phase
     */
    public Phase(String phase_id, String phase_name, String description) {
        this.phase_id = phase_id;
        this.phase_name = phase_name;
        this.description = description;
    }

    /**
     * Gets the ID of the phase.
     *
     * @return the phase ID
     */
    public String getPhase_id() {
        return phase_id;
    }

    /**
     * Sets the ID of the phase.
     *
     * @param phase_id the new phase ID
     */
    public void setPhase_id(String phase_id) {
        this.phase_id = phase_id;
    }

    /**
     * Gets the name of the phase.
     *
     * @return the phase name
     */
    public String getPhase_name() {
        return phase_name;
    }

    /**
     * Sets the name of the phase.
     *
     * @param phase_name the new phase name
     */
    public void setPhase_name(String phase_name) {
        this.phase_name = phase_name;
    }

    /**
     * Gets the description of the phase.
     *
     * @return the phase description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the phase.
     *
     * @param description the new phase description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
