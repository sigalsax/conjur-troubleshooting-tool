package com.troubleshooting.model;

// Data object for a Conjur log
public class ConjurLogModel implements ILogModel {
    private String logLevel;
    private String message;

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("[logLevel=%s, message=%s]", logLevel, message);
    }
}
