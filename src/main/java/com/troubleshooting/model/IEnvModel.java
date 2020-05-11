package com.troubleshooting.model;

import java.util.Map;

interface IEnvModel {
    
    Map <String,String> envObject = null;

    void addEnv(String input);

    Map<String, String> getEnvMap();
}
