package com.troubleshooting.troubleshootingtool;

/**
 * Holds the user input for validation
 */
public class Input {
    private String containerId;
    private String query;

    public Input(String containerId, String query) {
        this.containerId = containerId;
        this.query = query;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
