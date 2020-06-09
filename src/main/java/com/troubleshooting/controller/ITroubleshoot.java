package com.troubleshooting.controller;

import org.springframework.ui.Model;

interface ITroubleshoot {
    void validateData(Model userModel);

    void getTroubleshootingData(Model userModel);
}
