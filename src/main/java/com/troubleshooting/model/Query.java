package com.troubleshooting.model;

/**
 * Interface for queries
 */
public interface Query {

    String getContainerId();

    void setContainerId(String ctnId);

    String getQuery();

    void setQuery(String query);
}
