package com.troubleshooting.troubleshootingtool;

import com.troubleshooting.controller.AuthenticationTroubleshootController;
import com.troubleshooting.model.*;
import com.troubleshooting.view.AuthenticationTroubleshootView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public AuthenticationUserDataModel authenticationUserDataModel() {
        return new AuthenticationUserDataModel();
    }

    @Bean
    public AuthenticationTroubleshootView authenticationTroubleshootView() {
        return new AuthenticationTroubleshootView();
    }

    @Bean(name="authenticationController")
    public AuthenticationTroubleshootController authenticationTroubleshootController(){
        return new AuthenticationTroubleshootController(authenticationUserDataModel(), authenticationTroubleshootView(), retrieveData());
    }

    @Bean
    public ContainerDataAccess containerDataAccess() {
        return new ContainerDataAccess();
    }

    @Bean
    public ConjurLogModel conjurLogModel() {
        return new ConjurLogModel();
    }

    @Bean
    public LogsModel logsModel() {
        return new LogsModel();
    }

    @Bean
    public EnvironmentModel environmentModel() {
        return new EnvironmentModel();
    }

    @Bean
    public EnvironmentsModel environmentsModel() {
        return new EnvironmentsModel();
    }

    @Bean
    public ContainerDataRetrieval retrieveData(){
        return new ContainerDataRetrieval(containerDataAccess(), conjurLogModel(), logsModel(), environmentModel(), environmentsModel());
    }
}