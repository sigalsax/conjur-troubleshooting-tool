/**
 * Model object for a collection of container environment variables
 */
package com.troubleshooting.model;

import java.util.ArrayList;
import java.util.List;

public class EnvironmentsModel {

    List<EnvironmentModel> envList = new ArrayList<>();

    public void addEnv(EnvironmentModel environmentModel){
        envList.add(environmentModel);
    }

    public String toString(List<String> envList) {
        return envList.toString();
    }
}
