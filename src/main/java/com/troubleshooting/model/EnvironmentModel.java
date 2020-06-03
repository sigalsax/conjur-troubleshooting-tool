/**
 * Model object for container environment variables, associating an environment variable and its value via
 * key-value pairs
 */
package com.troubleshooting.model;

public class EnvironmentModel implements IEnvironmentModel{
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString() {
        return String.format("[key=%s, key=%s]", key, value);
    }
}
