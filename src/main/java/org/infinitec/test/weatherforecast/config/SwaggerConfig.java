package org.infinitec.test.weatherforecast.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Sameera.Manoraathna
 *
 * Swagger configuration
 */

@EnableSwagger2
@Configuration
public class SwaggerConfig
{
    public Docket configureSwagger()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.infinitec.test.weatherforecast.controller"))
                .paths(PathSelectors.regex("/data.*"))
                .build()
                .apiInfo(getAPIInfo());
    }

    private ApiInfo getAPIInfo()
    {
        Contact contact = new Contact("XYZ", "XYZ.com", "XYZ@gmail.com");
        return new ApiInfoBuilder()
                .title("Infinitec REST API project")
                .description("Sameera Manorathna")
                .version("1.0")
                .build();
    }
}
