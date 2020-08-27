package com.troubleshooting.model;

/**
 * Model object for container environment variables, associating an environment variable and its value via
 * key-value pairs
 */
public class EnvironmentModel implements Environment {
    private String key;
    private String value;

    public EnvironmentModel(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String toString() {
        return String.format("%s, %s", key, value);
    }
}
