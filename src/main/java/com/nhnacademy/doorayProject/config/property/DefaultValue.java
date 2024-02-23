package com.nhnacademy.doorayProject.config.property;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "com.nhnacademy.doorayproject.default-value")
public class DefaultValue {
    private int dormancyDay;
}
