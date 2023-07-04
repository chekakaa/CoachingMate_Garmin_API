// Define the package for the configuration
package coachingmateanalytics.coachingmate.common.config;

// Import required libraries
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * SyncConfig is a class that contains configurations for
 * asynchronous execution in the application, specifically
 * for setting up the thread pool executor.
 */
@Configuration
@EnableAsync
public class SyncConfig {

    /**
     * Creates a ThreadPoolTaskExecutor Bean that configures a thread pool
     * specifically designed for sending emails.
     *
     * @return A configured instance of ThreadPoolTaskExecutor
     */
    @Bean(name = "emailPoolTaskExecutor")
    public ThreadPoolTaskExecutor sendEmailPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // Set core thread number
        taskExecutor.setCorePoolSize(10);
        // Set maximum thread number
        taskExecutor.setMaxPoolSize(100);
        // Set the queue capacity for tasks
        taskExecutor.setQueueCapacity(50);
        // Set the keep-alive time for idle threads
        taskExecutor.setKeepAliveSeconds(200);
        // Set the thread name prefix
        taskExecutor.setThreadNamePrefix("Email-");
        // Set the policy for handling tasks that cannot be executed
        // Retry the task execution if rejected due to capacity constraints
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // Initialize the task executor
        taskExecutor.initialize();

        return taskExecutor;
    }
}
