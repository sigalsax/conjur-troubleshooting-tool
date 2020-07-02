package com.troubleshooting.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Model object for a collection of container environment variables
 */
public class EnvironmentCollectionModel {

    List<EnvironmentModel> envList = new ArrayList<>();

    public void addEnv(EnvironmentModel environmentModel){
        envList.add(environmentModel);
    }

    public void addAll(List<EnvironmentModel> environmentModels) {
        envList.addAll(environmentModels);
    }

    public String toString() {
        return envList.toString();
    }
}
