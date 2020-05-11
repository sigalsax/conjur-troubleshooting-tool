package com.troubleshooting.troubleshootingtool;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@PropertySource("classpath:application.properties")
public class ProgramProperties {
    String containerid;

    public String getContainerId() {
        return containerid;
    }
    public void setContainerId(String containerid) {
        this.containerid = containerid;
    }
}
