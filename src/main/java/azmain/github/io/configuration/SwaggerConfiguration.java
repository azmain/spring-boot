package azmain.github.io.configuration;

import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Learn Spring Boot")
                .select()
                .apis(RequestHandlerSelectors.basePackage("azmain.github"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Azmain Islam",
                "I will apply all my learnings in this app",
                "1.0.0",
                "Free To Use",
                new Contact("Nishan", "azmain.github.io","azmainnishan@gmail.com"),
                "© API License",
                "azmain.github.io",
                Collections.emptyList()
        );
    }
}
