package com.troubleshooting.troubleshootingtool;

import com.troubleshooting.model.ConjurLogModel;
import com.troubleshooting.model.EnvironmentModel;
import com.troubleshooting.model.EnvironmentCollectionModel;
import com.troubleshooting.model.LogCollectionModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Retrieves the data returned from the Docker Client and packs the data in the appropriate data models
 */
@Component
public final class ContainerDataRetrieval implements DataRetrieval {
    private ContainerDataAccess access;
    private LogCollectionModel logCollection;
    private EnvironmentCollectionModel environmentCollection;

    static String STDERR = "STDERR";

    @Autowired
    public ContainerDataRetrieval(ContainerDataAccess access, LogCollectionModel logCollection, EnvironmentCollectionModel environmentCollection) {
        this.access = access;
        this.logCollection = logCollection;
        this.environmentCollection = environmentCollection;
    }

    @Override
    public LogCollectionModel getLogs(String containerID, String query) {
        List<String> loggingFrames = new ArrayList<String>();

        loggingFrames = access.getLogs(containerID, query);
        loggingFrames.stream().forEach(entry -> extractImportantPartsOfLogEntry(entry));

        return logCollection;
    }

    private void extractImportantPartsOfLogEntry(String logInstance) {
        List<String> includedEnv = Arrays.asList(new String[] {
                "origin",
                "request_id",
                "tid",
        });

        String[] logEntry = logInstance.split(" ", 5);
        if (!logEntry[0].contains(STDERR) && !logEntry[0].isEmpty()) {
            System.out.println(logInstance);
            if (logEntry[1].contains("origin") && logEntry[2].contains("request_id") && logEntry[3].contains("tid")) {
                logCollection.add(new ConjurLogModel(logEntry[1], logEntry[2], logEntry[3], logEntry[logEntry.length - 1]));
            }
        }
    }

    @Override
    public EnvironmentCollectionModel getEnvironmentInfo(String containerID) {
        try {
            String environmentCollectionString = access.getEnvironmentInfo(containerID);
            populateEnvCollection(environmentCollectionString);
        } catch (Exception e) {
            System.out.println("Unable to get environment variables from container...");
            throw e;
        }
        return environmentCollection;
    }

    private void populateEnvCollection(String envInstance) {
        Properties prop = new Properties();

        try {
            prop.load(new StringReader(envInstance));
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> includedEnv = Arrays.asList(new String[] {
                "HOSTNAME",
                "DATABASE_URL",
                "PORT",
                "CONJUR_ADMIN_PASSWORD",
                "CONJUR_LOG_LEVEL",
                "CONJUR_PASSWORD",
                "CONJUR_ACCOUNT",
                "CONJUR_MAJOR_VERSION",
                "CONJUR_DATA_KEY"
        });

        List<EnvironmentModel> environmentModels = prop.entrySet().stream().filter(e -> includedEnv.contains(e.getKey()))
                .map(e -> new EnvironmentModel(e.getKey().toString(), e.getValue().toString())).collect(Collectors.toList());

        environmentCollection.addAll(environmentModels);
    }
}
