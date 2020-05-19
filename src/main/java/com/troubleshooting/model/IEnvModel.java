package com.troubleshooting.model;

import java.util.Map;

public interface IEnvModel {
    
    Map <String,String> envObject = null;

    Map<String, String> addEnv(String input);
}
