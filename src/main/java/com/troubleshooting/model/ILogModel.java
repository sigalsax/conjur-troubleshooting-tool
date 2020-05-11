package com.troubleshooting.model;

import java.util.List;

interface ILogModel {
    String logLevel="";
    String timeStamp="";
    String ctnId="";
    String origin="";
    String service="";
    String message="";

    void addLog(String log);

    List getLogMap();
}
