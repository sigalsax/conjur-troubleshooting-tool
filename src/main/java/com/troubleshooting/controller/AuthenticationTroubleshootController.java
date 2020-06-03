package com.troubleshooting.controller;

import com.troubleshooting.model.IAuthenticationUserDataModel;
import com.troubleshooting.view.ITroubleshootView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.troubleshooting.troubleshootingtool.ContainerDataRetrieval;

@Controller
@RequestMapping("/")
public class AuthenticationTroubleshootController implements ITroubleshoot {

    ContainerDataRetrieval retrieveData;

    @Autowired
    public AuthenticationTroubleshootController(IAuthenticationUserDataModel userModel, ITroubleshootView troubleshootView, ContainerDataRetrieval containerDataRetrieval){
        this.retrieveData = containerDataRetrieval;
//      this.userModel = userModel;
//      this.troubleshootView = troubleshootView;
        userModel.setContainerId("0921773ddba3");
        getTroubleshootingData(userModel);
    }

    @Override
    public void validateData(IAuthenticationUserDataModel userModel) { }

    @GetMapping
    @Override
    public void getTroubleshootingData(IAuthenticationUserDataModel userModel) {
        // validateData(userModel);
        retrieveData.getLogs(userModel.getContainerId());
        retrieveData.getEnvironment(userModel.getContainerId());
    }
}
