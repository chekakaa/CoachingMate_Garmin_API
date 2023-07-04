package coachingmateanalytics.coachingmate.common.intercepter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CustomWebMvcConfig class implements WebMvcConfigurer and configures the custom interceptors and resource handlers.
 */
@Configuration
public class CustomWebMvcConfig implements WebMvcConfigurer {

    /**
     * Adds custom interceptors to the InterceptorRegistry.
     *
     * @param registry The InterceptorRegistry object.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor());
        registry.addInterceptor(new SessionIntercepter()).addPathPatterns("/**")
                .excludePathPatterns("/user/login.do").excludePathPatterns("/user/register.do")
                .excludePathPatterns("/user/send").excludePathPatterns("/index.html").excludePathPatterns("/swagger-ui.html**")
                .excludePathPatterns(
                "/v2/api-docs",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/webjars/**"
        );
    }

    /**
     * Adds view controllers for redirecting API documentation-related requests.
     *
     * @param registry The ViewControllerRegistry object.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/api/v2/api-docs", "/v2/api-docs");
        registry.addRedirectViewController("/api/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
        registry.addRedirectViewController("/api/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
        registry.addRedirectViewController("/api/swagger-resources", "/swagger-resources");
    }

    /**
     * Configures custom resource handlers for API documentation.
     *
     * @param registry The ResourceHandlerRegistry object.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
        registry.addResourceHandler("/api/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
