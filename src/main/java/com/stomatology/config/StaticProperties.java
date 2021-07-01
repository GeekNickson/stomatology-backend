package com.stomatology.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.static", ignoreUnknownFields = false)
@Getter
@Setter
public class StaticProperties {
    private String path;
}
