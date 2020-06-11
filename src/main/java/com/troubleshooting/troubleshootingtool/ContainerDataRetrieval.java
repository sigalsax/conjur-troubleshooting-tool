/**
 * Retrieves the data returned from the Docker Client and packs the data in the appropriate data models
 */
package com.troubleshooting.troubleshootingtool;

import com.troubleshooting.model.ConjurLogModel;
import com.troubleshooting.model.EnvironmentModel;
import com.troubleshooting.model.EnvironmentsModel;
import com.troubleshooting.model.LogsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public final class ContainerDataRetrieval implements IDataRetrieval {
    private ContainerDataAccess access;
    private ConjurLogModel log;
    private LogsModel logs;
    private EnvironmentModel env;
    private EnvironmentsModel envs;

    static String STDERR = "STDERR";

    @Autowired
    public ContainerDataRetrieval(ContainerDataAccess access, ConjurLogModel log, LogsModel logs, EnvironmentModel env, EnvironmentsModel envs) {
        this.access = access;
        this.log = log;
        this.logs = logs;
        this.env = env;
        this.envs = envs;
    }

    @Override
    public LogsModel getLogs(String containerID, String query) {
        List<String> loggingFrames = new ArrayList<String>();

        try {
            loggingFrames = access.getLogs(containerID, query);

            loggingFrames.stream().forEach(entry -> extractImportantPartsOfLogEntry(entry));
        } catch (NullPointerException e) {
            System.out.println(e);
        }
        return logs;
    }

    private void extractImportantPartsOfLogEntry(String logInstance) {
        String[] logEntry = logInstance.split(" ", 5);
        if (!logEntry[0].contains(STDERR) && !logEntry[0].isEmpty() && logEntry.length >= 5) {
            if (logEntry[1].contains("origin") && logEntry[2].contains("request_id") && logEntry[3].contains("tid")) {
                log.setOrigin(logEntry[1]);
                log.setRequest_id(logEntry[2]);
                log.setThread_id(logEntry[3]);
                log.setMessage(logEntry[logEntry.length - 1]);
                logs.add(log);
            }
        }
    }

    @Override
    public EnvironmentsModel getEnvironmentInfo(String containerID) {
        try {
            String envsString = access.getEnv(containerID);
            formatAndPopulateEnvCollection(envsString);
        } catch (Exception e) {
            System.out.println("Unable to get environment variables from container... ");
        }
        return envs;
    }

    private void formatAndPopulateEnvCollection(String envInstance) {
        String [] includedEnv = {"HOSTNAME", "DATABASE_URL", "PORT", "CONJUR_ADMIN_PASSWORD", "CONJUR_LOG_LEVEL", "CONJUR_PASSWORD", "CONJUR_ACCOUNT"};
        String [] envParts = envInstance.split("=|\n");

        for (int i = 0; i < envParts.length; i+=2) {
            if(Arrays.stream(includedEnv).anyMatch(envParts[i]::equals)) {
                env.setKey(envParts[i]);
                env.setValue(envParts[i + 1]);
                envs.addEnv(env);
            }
        }
    }
}
