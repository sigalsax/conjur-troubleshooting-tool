package com.troubleshooting.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Model object for a collection of Conjur logs
 */
public class LogCollectionModel {
    final private List<ConjurLogModel> logList = new ArrayList<>();

    public void add(ConjurLogModel log) {
        logList.add(log);
    }

    public String toString() {
        return Arrays.toString(logList.toArray());
    }
}
