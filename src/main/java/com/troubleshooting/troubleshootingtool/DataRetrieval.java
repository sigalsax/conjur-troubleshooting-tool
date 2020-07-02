package com.troubleshooting.troubleshootingtool;

import com.troubleshooting.model.EnvironmentCollectionModel;
import com.troubleshooting.model.LogCollectionModel;

/**
 * Interface for retrieving data
 */
interface DataRetrieval {

    LogCollectionModel getLogs(String containerID, String query);

    EnvironmentCollectionModel getEnvironmentInfo(String containerID);
}
