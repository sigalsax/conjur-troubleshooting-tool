package com.troubleshooting.model;

// Log interface for Conjur and DAP logs
interface ILogModel {
    String logLevel="";
    String timeStamp="";
    String ctnId="";
    String origin="";
    String service="";
    String message="";
}
