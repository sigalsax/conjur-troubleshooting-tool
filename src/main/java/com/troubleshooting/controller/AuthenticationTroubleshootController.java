package com.troubleshooting.controller;
import com.troubleshooting.troubleshootingtool.ContainerDataRetrieval;
import com.troubleshooting.service.ValidateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
public class AuthenticationTroubleshootController implements ITroubleshoot {

    ContainerDataRetrieval retrieveData;

    ValidateService validate;

    public AuthenticationTroubleshootController(ContainerDataRetrieval retrieveData) {
        this.retrieveData=retrieveData;
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model){
        return "index";
    }

    @Override
    public void validateData(Model userModel) {}

    // TODO: Use JQuery for easy re-rendering to index page
    // TODO: Look into converting input to JSON objects
    @RequestMapping(value="/troubleshoot", method=RequestMethod.POST)
    public String getTroubleshootingData(Model model, @RequestParam String containerId, @RequestParam String query) {
        model.addAttribute("containerId", containerId);
        model.addAttribute("query", query);

        boolean isValidUser = validate.validateInput(containerId, query);

        if (!isValidUser) {
            model.addAttribute("errorMessage", "Invalid Input");
            return "index";
        }

        getTroubleshootingData(model);
        return "troubleshoot";
    }

    @Override
    public void getTroubleshootingData(Model userModel) {
        retrieveData.getLogs((String) userModel.getAttribute("containerId"));
        retrieveData.getEnvironmentInfo((String) userModel.getAttribute("containerId"));
    }
}
