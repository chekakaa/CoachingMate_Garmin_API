package coachingmateanalytics.coachingmate.common.utils;

import lombok.Data;

/**
 * A utility class representing the properties of a paginated response.
 */
@Data
public class Paging {
    private Integer total;         // The total number of items in the collection
    private Integer currentPage;   // The current page number
    private Integer pageSize;      // The number of items per page
}
