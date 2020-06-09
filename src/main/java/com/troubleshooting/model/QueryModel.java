/**
 * Model object for user input
 */
package com.troubleshooting.model;

public class QueryModel implements IQueryModel{
    private String containerId;
    private String query;

    @Override
    public String getContainerId() {
        return containerId;
    }

    @Override
    public void setContainerId(String containerId) {
        this.containerId=containerId;
    }

    @Override
    public String getQuery() {
        return query;
    }

    @Override
    public void setQuery(String query) {
        this.query=query;
    }
}
