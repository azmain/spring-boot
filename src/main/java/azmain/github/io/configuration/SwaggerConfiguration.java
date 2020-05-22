package azmain.github.io.configuration;

import io.swagger.models.auth.In;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
//                .globalOperationParameters(singletonList(
//                        new ParameterBuilder()
//                                .name("Authorization")
//                                .modelRef(new ModelRef("string"))
//                                .parameterType("header")
//                                .required(false)
//                                .hidden(true)
//                                .defaultValue("Bearer " + "jwt")
//                                .build()
//                        )
//                )
                .groupName("Learn Spring Boot")
                .select()
                .apis(RequestHandlerSelectors.basePackage("azmain.github"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiDetails())
                .securitySchemes(Collections.singletonList(
                        new ApiKey(
                                "APIKey",
                                "Authorization",
                                "header"))
                )
                .securityContexts(Collections.singletonList(securityContext()));
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Azmain Islam",
                "I will apply all my learnings in this app",
                "1.0.0",
                "Free To Use",
                new Contact("Nishan", "azmain.github.io","azmainnishan@gmail.com"),
                "© API License",
                "https://www.azmain.github.io",
                Collections.emptyList()
        );
    }

    private SecurityContext securityContext() {
        return SecurityContext
                .builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/.*"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        final AuthorizationScope authorizationScope =
                new AuthorizationScope("global", "accessEverything");
        final AuthorizationScope[] authorizationScopes =
                new AuthorizationScope[]{authorizationScope};
        return Collections.singletonList(
                new SecurityReference("APIKey", authorizationScopes));
    }

}
