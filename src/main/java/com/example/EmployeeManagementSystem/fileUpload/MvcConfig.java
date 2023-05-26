package com.example.EmployeeManagementSystem.fileUpload;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {


    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("index");
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path uploadDir=Paths.get("./picture");
        String uploadPath=uploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/picture/**").addResourceLocations("file:/" +uploadPath+"/");
    }


}
