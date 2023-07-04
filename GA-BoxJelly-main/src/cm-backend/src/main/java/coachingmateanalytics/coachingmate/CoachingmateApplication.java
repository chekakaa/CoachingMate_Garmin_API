
// Define the package for the application
package coachingmateanalytics.coachingmate;

// Import required libraries
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * This is the main class for the CoachingmateApplication.
 * It is annotated with @SpringBootApplication to indicate that
 * it is a Spring Boot application and to enable auto-configuration
 * and component scanning.
 */
@SpringBootApplication
public class CoachingmateApplication {

    /**
     * The main method serves as the entry point for the application.
     * It delegates the responsibility to start the application to the
     * SpringApplication class by calling the run method and passing the
     * main class and command-line arguments.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(CoachingmateApplication.class, args);
    }
}

