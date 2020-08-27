package com.troubleshooting.service;

import com.troubleshooting.troubleshootingtool.Input;
import org.springframework.stereotype.Service;

/**
 * Service for input validation
 */
@Service
public class ValidateService {

    public boolean validateInput(Input input) {
        return input.getContainerId() != "" && input.getQuery() != "";
    }
}
