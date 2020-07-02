package com.troubleshooting.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.troubleshooting.troubleshootingtool.ContainerDataRetrieval;
import com.troubleshooting.service.ValidateService;
import com.troubleshooting.troubleshootingtool.Input;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthenticationTroubleshootController implements Troubleshoot {

    private ContainerDataRetrieval retrieveData;
    private ValidateService validateService;

    private final String CONTAINERID = "containerId";
    private final String QUERY = "query";
    private final String ERROR = "errorMessage";
    private final String LOGENTRIES = "logEntries";
    private final String ENVIRONMENTVARIABLES = "environmentVariables";

    private final String INDEXPAGE = "index";
    private final String TROUBLESHOOTPAGE = "troubleshoot";

    public AuthenticationTroubleshootController(ContainerDataRetrieval retrieveData, ValidateService validateService) {
        this.retrieveData = retrieveData;
        this.validateService = validateService;
    }

    @Override
    public boolean validateInput(Input input) {
        return validateService.validateInput(input);
    }

    @Override
    public String getTroubleshootingData(String containerId, String query) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonLogs="";
        String jsonEnv="";

        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        try {
            jsonLogs = mapper.writeValueAsString(retrieveData.getLogs(containerId, query));
            jsonEnv = mapper.writeValueAsString(retrieveData.getEnvironmentInfo(containerId));
            return jsonLogs;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonLogs;
    }

    @GetMapping(value = "/subView")
    public @ResponseBody String getSubView(@RequestParam String containerId, @RequestParam String query) {
        if (!validateInput(new Input(containerId, query))) {
            return "Invalid input!";
        }
        return getTroubleshootingData(containerId, query);
    }
}
