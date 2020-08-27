package com.troubleshooting.model;

/**
 * Model object for user input
 */
public class QueryModel implements Query {
    private String containerId;
    private String query;

    @Override
    public String getContainerId() {
        return containerId;
    }

    @Override
    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    @Override
    public String getQuery() {
        return query;
    }

    @Override
    public void setQuery(String query) {
        this.query = query;
    }
}
