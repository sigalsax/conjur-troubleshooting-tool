package com.troubleshooting.service;

import org.springframework.stereotype.Service;

@Service
public class ValidateService {

    public boolean validateInput(String containerId, String query) {
        return containerId != "" && query != "";
    }
}
