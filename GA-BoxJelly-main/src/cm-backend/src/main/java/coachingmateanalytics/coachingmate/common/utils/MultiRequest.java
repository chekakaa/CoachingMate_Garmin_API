package coachingmateanalytics.coachingmate.common.utils;

/**
 * A generic wrapper class for multi-requests.
 *
 * @param <E> The type of the request parameters
 */
public class MultiRequest<E> {
    private E params;

    /**
     * Retrieve the request parameters.
     *
     * @return The request parameters
     */
    public E getParams() {
        return params;
    }

    /**
     * Set the request parameters.
     *
     * @param params The request parameters to set
     */
    public void setParams(E params) {
        this.params = params;
    }
}
