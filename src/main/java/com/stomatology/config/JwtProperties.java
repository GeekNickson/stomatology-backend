package com.stomatology.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.jwt", ignoreUnknownFields = false)
@Getter
@Setter
public class JwtProperties {
    private String secret;
    private String issuer;
    private Long tokenExp;
    private Long refreshExp;
}
