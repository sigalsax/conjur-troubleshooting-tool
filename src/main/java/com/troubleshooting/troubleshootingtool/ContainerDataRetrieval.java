package com.troubleshooting.troubleshootingtool;

import com.troubleshooting.model.ConjurLogModel;
import com.troubleshooting.model.EnvModel;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class ContainerDataRetrieval implements IDataRetrieval {
    ContainerDataAccess access = new ContainerDataAccess();
    ConjurLogModel conjurLog = new ConjurLogModel();
    EnvModel env = new EnvModel();

    //	Extract Logs
    @Override
    public List getData(String containerID) {
        List<String> loggingFrames = access.getLogs(containerID);

        for(int i = 0; i < loggingFrames.size(); i++) {
            conjurLog.addLog(loggingFrames.get(i));
        }

        // For testing purposes
        System.out.println(conjurLog.getLogMap().toString());
        return loggingFrames;
    }

    // Extract Env
    @Override
    public String getInfo(String containerID) {
        Map<String, String> envMap = new HashMap();

        try {
            envMap = env.addEnv(access.getEnv(containerID));
            // For testing purposes
            envMap.forEach((key, value) -> System.out.println(key + ":" + value));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return containerID;
    }
}
