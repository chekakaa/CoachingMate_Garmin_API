package coachingmateanalytics.coachingmate.common.utils;

/**
 * A utility class representing a generic request with additional information for filtering and pagination.
 *
 * @param <E> The type of the model this request is associated with.
 */
public class Request<E> {
    private E model;             // The model associated with this request
    private Paging paging;       // The pagination information for this request
    private String keyword;      // The keyword for searching or filtering
    private String action;       // The action to be performed for this request
    private String info;         // Additional information for this request

    public E getModel() {
        return model;
    }

    public void setModel(E model) {
        this.model = model;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }
}
