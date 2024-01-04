package com.apiproducts.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class imgConfig implements WebMvcConfigurer {

    @Value("${ubicacion.imagen}")
    private String ruta;
    @Value("{carpeta}")
    private String carpeta;

    // esta clase sirve para decir a spring que este archivo puede tomarlo como parte del proyecto
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/"+carpeta+"/**").addResourceLocations(ruta);
    }
}
