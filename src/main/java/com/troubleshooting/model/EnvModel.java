package com.troubleshooting.model;

import java.util.HashMap;
import java.util.Map;

public class EnvModel implements IEnvModel {

    Map<String,String> envMap = new HashMap<String,String>();

    @Override
    public void addEnv(String containerEnv) {
        String[] envParts = containerEnv.split("=|\n");

        for (int i = 0; i < envParts.length; i+=2) {
                envMap.put(envParts[i], envParts[i + 1]);
        }
    }

    @Override
    public Map getEnvMap(){
        return envMap;
    }
}
