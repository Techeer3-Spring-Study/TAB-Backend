package com.techeeresc.tab.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {
  @Bean
  public GroupedOpenApi publicApi() {
    return GroupedOpenApi.builder().group("tab-api-v1").pathsToMatch("/api/v1/**").build();
  }

  @Bean
  public OpenAPI tabOpenApi() {
    return new OpenAPI()
        .info(
            new Info()
                .title("TAB API Docs")
                .description("Techeer 3rd Spring Boot Project API Docs")
                .version("v0.0.1"));
  }
}
