/**
 * Model object for a collection of Conjur logs
 */
package com.troubleshooting.model;

import java.util.ArrayList;
import java.util.List;

public class LogsModel {
    List<ConjurLogModel> logList = new ArrayList<>();

    public void add(ConjurLogModel log) {
        logList.add(log);
    }

    public String toString(List<String> logList) {
        return logList.toString();
    }
}
