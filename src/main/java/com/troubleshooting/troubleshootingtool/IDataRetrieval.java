package com.troubleshooting.troubleshootingtool;

import com.troubleshooting.model.EnvironmentsModel;
import com.troubleshooting.model.LogsModel;

interface IDataRetrieval {

    LogsModel getLogs(String containerID, String query);

    EnvironmentsModel getEnvironmentInfo(String containerID);
}
