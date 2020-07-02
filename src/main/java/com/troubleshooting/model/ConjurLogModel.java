package com.troubleshooting.model;

/**
 * Model object for Conjur log input
 */
public class ConjurLogModel implements Log {

    private String origin;
    private String requestId;
    private String threadId;
    private String message;

    public ConjurLogModel(String origin, String request_id, String thread_id, String message) {
        this.origin = origin;
        this.requestId = request_id;
        this.threadId = thread_id;
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, [message=%s]", origin, requestId, threadId, message);
    }
}
