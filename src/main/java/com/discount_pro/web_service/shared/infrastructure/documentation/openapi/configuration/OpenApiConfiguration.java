package com.discount_pro.web_service.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI discountProWebServiceOpenApi() {
        // General configuration
        var openApi = new OpenAPI();
        openApi
                .info(new Info()
                        .title("Discount Pro Web Service API")
                        .description("Discount Pro Web Service application REST API documentation.")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0")
                                .url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Discount Pro Web Service Documentation")
                        .url("https://github.com/DiscountPro/backend"));
        return openApi;
    }
}
