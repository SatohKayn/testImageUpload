package test.testImageUpload.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uploadsDir = "/images/**";
        String uploadsPath = "file:C:/Users/nkanh/Downloads/testImageUpload/images/";

        registry.addResourceHandler(uploadsDir)
                .addResourceLocations(uploadsPath);
    }
}
