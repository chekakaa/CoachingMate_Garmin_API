package coachingmateanalytics.coachingmate.common.utils;

import lombok.Data;

/**
 * A utility class representing the sorting options for a search request.
 */
@Data
public class Sorting {
    private String sortingColumn;
    private String sortingMethod;

    // Getters and setters are automatically generated by the Lombok @Data annotation.
}