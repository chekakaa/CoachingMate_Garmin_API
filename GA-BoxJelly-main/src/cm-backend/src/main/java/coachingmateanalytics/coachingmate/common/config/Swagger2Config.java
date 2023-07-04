// Define the package for the configuration
package coachingmateanalytics.coachingmate.common.config;

// Import required libraries
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2Config is a class that contains Swagger2 configurations for
 * the application. It enables Swagger2 documentation and sets up
 * the required configurations for generating API documentation.
 */
@EnableWebMvc
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "coachingmateanalytics.coachingmate.controller")
public class Swagger2Config {
    private static final Logger LOGGER = LoggerFactory.getLogger(Swagger2Config.class);

    @Value("${spring.swagger2.enabled}")
    private Boolean swaggerEnabled;

    /**
     * Creates a Docket Bean that configures the Swagger2 documentation
     * settings for the application.
     *
     * @return A configured instance of Docket
     */
    @Bean
    public Docket swaggerDocket() {
        LOGGER.debug("Coachingmate2020--API configuration");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * Creates an ApiInfo instance that provides API information
     * to be displayed in the Swagger2 documentation.
     *
     * @return A configured instance of ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().description("Coachingmate2020 API")
                .contact(new Contact("Saul", "http://localhost:8080/index.html", "shuaimou77@gmail.com"))
                .version("1.0.0")
                .termsOfServiceUrl("http://47.74.88.139:8080/")
                .build();
    }
}
