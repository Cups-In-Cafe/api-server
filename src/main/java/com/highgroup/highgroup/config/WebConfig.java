package com.highgroup.highgroup.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.resource.PathResourceResolver;

import org.springframework.beans.factory.annotation.Value;


@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Value("${file.url}") 
  private String uploadImagePath; 

  public static String[] allowedOrigins = {
    "http://highgroup.hi24.kr:7070",
    "http://localhost:8080"
  };

  
  @Override 
  public void addResourceHandlers(ResourceHandlerRegistry registry) { 
    
    registry.addResourceHandler("/v1/upload/**") 
    .addResourceLocations("file:///"+uploadImagePath) 
    .setCachePeriod(3600)
    .resourceChain(true)
    .addResolver(new PathResourceResolver());

  };

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    
    registry.addMapping("/**")
        .allowedMethods(HttpMethod.GET.name(), HttpMethod.HEAD.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),HttpMethod.DELETE.name())
        .allowedOrigins( allowedOrigins );
    
  }
}
