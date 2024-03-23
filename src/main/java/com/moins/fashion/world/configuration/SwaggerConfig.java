//package com.moins.fashion.world.configuration;
//
//import java.util.Collections;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@EnableSwagger2
//public class SwaggerConfig implements WebMvcConfigurer {
//
//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).select()
//				.apis(RequestHandlerSelectors.basePackage("com.example.controller")).paths(PathSelectors.any()).build()
//				.apiInfo(this.custInfo());
//
//	}
//
//	public ApiInfo custInfo() {
//
//		return new ApiInfo("Test", // Title`
//				"Dress Rental System", // Description
//				"1.0", // Version
//				"TOS", // Terms of Service
//				new Contact("Dress", "Dressrentalservice.com", "info@dress.com"), // Contact
//				"Test license", // License
//				"License", Collections.emptyList());
//	}
//
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addRedirectViewController("/api/v2/api-docs", "/v2/api-docs");
//		registry.addRedirectViewController("/api/swagger-resources/configuration/ui",
//				"/swagger-resources/configuration/ui");
//		registry.addRedirectViewController("/api/swagger-resources/configuration/security",
//				"/swagger-resources/configuration/security");
//		registry.addRedirectViewController("/api/swagger-resources", "/swagger-resources");
//	}
//
//	@Override
//	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
//
//		registry.addResourceHandler("/api/swagger-ui.html**")
//				.addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
//
//		registry.addResourceHandler("/api/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//	}
//}
