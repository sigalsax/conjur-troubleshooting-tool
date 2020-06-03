package com.troubleshooting.controller;

import com.troubleshooting.model.IAuthenticationUserDataModel;

interface ITroubleshoot {

    void validateData(IAuthenticationUserDataModel userModel);

    void getTroubleshootingData(IAuthenticationUserDataModel userModel);
}
