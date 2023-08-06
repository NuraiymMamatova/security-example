package com.example.todo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@OpenAPIDefinition(
//        info =@Info(
//                title = "User API",
//                version = "${api.version}",
//                contact = @Contact(
//                        name = "Baeldung", email = "user-apis@baeldung.com", url = "https://www.baeldung.com"
//                ),
//                license = @License(
//                        name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"
//                ),
////                termsOfService = "${tos.uri}",
//                description = "${api.description}"
//        )
//        servers = @Server(
//                url = "${api.server.url}",
//                description = "Production"
//        )
//)
//@SecurityScheme(
//        name = "Bearer Authentication",
//        type = SecuritySchemeType.HTTP,
//        bearerFormat = "JWT",
//        scheme = "bearer"
//)
//@OpenAPIDefinition(info = @Info(title = "REST API", version = "1.1",
//        contact = @Contact(name = "Chinna", email = "java4chinna@gmail.com")),
//        security = {@SecurityRequirement(name = "basicAuth"), @SecurityRequirement(name = "bearerToken")}
//)
//@SecuritySchemes({
//        @SecurityScheme(name = "basicAuth", type = SecuritySchemeType.HTTP, scheme = "basic"),
//        @SecurityScheme(name = "bearerToken", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
//})
public class SwaggerConfig {

//    private static final String API_KEY = "Bearer Token ";

    @Bean
    public OpenAPI customizeOpenAPI() {
        final String securitySchemeName = "Bearer Token";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("Bearer")
                                .bearerFormat("JWT")));
    }

//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .components(new Components()
//                        .addSecuritySchemes(API_KEY, apiKeySecuritySchema()))
//                .info(new Info().title("Airbnb"))
//                .security(Collections.singletonList(new SecurityRequirement().addList(API_KEY)));
//    }
//
//    public SecurityScheme apiKeySecuritySchema() {
//        return new SecurityScheme()
//                .name("Authorization")
//                .description("Just put the token")
//                .in(SecurityScheme.In.HEADER)
//                .type(SecurityScheme.Type.HTTP)
//                .scheme("Bearer");
//    }


//@Bean
//public Docket customImplementation() {
//
//    return new Docket(DocumentationType.SWAGGER_2)
//            .select()
//            .paths(PathSelectors.any())
//            .apis(RequestHandlerSelectors.basePackage("com.ntapan"))
//            .build()
//            .apiInfo(apiInfo())
//            .pathMapping("/")
//            .useDefaultResponseMessages(false)
//            .directModelSubstitute(LocalDate.class, String.class)
//            .genericModelSubstitutes(ResponseEntity.class);
//}
//
//    ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("Swagger with Spring Boot + Security")
//                .version("1.0.0")
//                .description("Your Description")
////                .contact(new Contact("Contact Name", "Contact_URL","contact@email.com"))
//                .build();
//    }
}
