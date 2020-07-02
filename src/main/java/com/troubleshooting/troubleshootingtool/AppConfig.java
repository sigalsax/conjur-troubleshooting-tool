package com.troubleshooting.troubleshootingtool;

import com.troubleshooting.controller.AuthenticationTroubleshootController;
import com.troubleshooting.model.*;
import com.troubleshooting.service.ValidateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public QueryModel queryModel() {
        return new QueryModel();
    }

    @Bean(name="authenticationController")
    public AuthenticationTroubleshootController authenticationTroubleshootController(){
        return new AuthenticationTroubleshootController(retrieveData(), validateService());
    }

    @Bean
    public ValidateService validateService(){ return new ValidateService(); }

    @Bean
    public ContainerDataAccess containerDataAccess() {
        return new ContainerDataAccess();
    }

    @Bean
    public LogCollectionModel logCollectionModel() {
        return new LogCollectionModel();
    }

    @Bean
    public EnvironmentCollectionModel environmentsCollectionModel() {
        return new EnvironmentCollectionModel();
    }

    @Bean
    public ContainerDataRetrieval retrieveData(){
        return new ContainerDataRetrieval(containerDataAccess(), logCollectionModel(), environmentsCollectionModel());
    }
}
