package br.com.dantas.springbatchv5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class PropsConfig {

    //TODO REMOVE COMMENT BELLOW TO USE EXTERNAL FILE CONFIGURANTION APPROACH
//    @Bean
    public PropertySourcesPlaceholderConfigurer sourceConfigurer(){
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new FileSystemResource("C:\\Users\\Bruno\\Documents\\Spring Batch - Giuliana Bezerra\\firstJobExternalProperties.properties"));
        return configurer;
    }
}
