// Define the package for the annotation
package coachingmateanalytics.coachingmate.common.annotation;

// Import required libraries
import java.lang.annotation.*;

/**
 * AuthCheck is a custom annotation that can be used to mark methods
 * requiring authentication or authorization checks.
 */
@Target(ElementType.METHOD) // Indicates that this annotation can be applied to methods
@Retention(RetentionPolicy.RUNTIME) // Specifies that this annotation will be available at runtime
@Documented // Indicates that this annotation should be documented by tools that process annotations
public @interface AuthCheck {
    // No additional attributes or methods are needed for this annotation
}
