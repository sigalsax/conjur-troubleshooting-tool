package com.troubleshooting.model;

import java.util.ArrayList;
import java.util.List;

public class ConjurLogModel implements ILogModel {
    List<String> logList = new ArrayList<>();
    String origin;
    String message;

    @Override
    public void addLog(String log) {
        logList.add(log);
    }

    @Override
    public List getLogMap() {
        return logList;
    }


}
