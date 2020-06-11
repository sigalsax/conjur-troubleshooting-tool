/**
 * Model object for Conjur log input
 */
package com.troubleshooting.model;

public class ConjurLogModel implements ILogModel {

    private String origin;
    private String request_id;
    private String thread_id;
    private String message;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getThread_id() {
        return thread_id;
    }

    public void setThread_id(String thread_id) {
        this.thread_id = thread_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("[origin=%s, request_id=%s, thread_id=%s, message=%s]", origin, request_id, thread_id, message);
    }
}
