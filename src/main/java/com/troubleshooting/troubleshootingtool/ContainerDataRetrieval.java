package com.troubleshooting.troubleshootingtool;

import com.troubleshooting.model.ConjurLogModel;
import com.troubleshooting.model.EnvModel;

import com.troubleshooting.model.EnvsModel;
import com.troubleshooting.model.LogsModel;
import org.springframework.stereotype.Component;

import java.util.List;

// Retrieves the data returned from the Docker Client and packs the data in the appropriate data models
@Component
public class ContainerDataRetrieval implements IDataRetrieval {
    ContainerDataAccess access = new ContainerDataAccess();

    ConjurLogModel log = new ConjurLogModel();
    LogsModel logs = new LogsModel();

    EnvModel env = new EnvModel();
    EnvsModel envs = new EnvsModel();

    //	Extract Logs
    @Override
    public LogsModel getData(String containerID) {
        List<String> loggingFrames = access.getLogs(containerID);

        for(int i = 0; i < loggingFrames.size(); i++) {
            System.out.println(loggingFrames.get(i));
            logParser(loggingFrames.get(i), log);
        }
        return logs;
    }

    // Extracts important parts of each Log entry
    public void logParser(String logInstance, ConjurLogModel log) {
        String[] logEntry = logInstance.split(" ", 8);

        if (!logEntry[0].contains("STDERR:")) {
            log.setLogLevel(logEntry[1]);
            log.setMessage(logEntry[logEntry.length - 1]);
            logs.add(log);
        }
    }

    // Extract Env
    @Override
    public EnvsModel getInfo(String containerID) {
        String envsString;

        try {
            envsString = access.getEnv(containerID);
            envParser(envsString, env);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return envs;
    }

    // Extracts important parts of each Env entry
    public void envParser(String envInstance, EnvModel env) {
        String[] envParts = envInstance.split("=|\n");
        for (int i = 0; i < envParts.length; i+=2) {
            env.setKey(envParts[i]);
            env.setValue(envParts[i + 1]);
            envs.addEnv(env);
        }
    }
}
