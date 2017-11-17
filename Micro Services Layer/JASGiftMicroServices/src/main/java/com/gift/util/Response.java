package com.gift.util;

import java.util.Date;

/**
 * The Class Response for custom response to client.
 */
public class Response {
    
    /** The time stamp. */
    private String timeStamp;
    
    /** The message. */
    private String message;
    
    /** The details. */
    private String details;

    /**
     * Instantiates a new response.
     *
     * @param message the message
     * @param details the details
     */
    public Response(String message, String details) {
        this.timeStamp = new Date().toString();
        this.message = message;
        this.details = details;
    }

    /**
     * Instantiates a new response.
     *
     * @param message the message
     */
    public Response(String message) {
        this.timeStamp = new Date().toString();
        this.message = message;
        this.details = message;
    }

    /**
     * Instantiates a new response.
     */
    public Response() {
    }

    /**
     * Gets the time stamp.
     *
     * @return the time stamp
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets the time stamp.
     *
     * @param timeStamp the new time stamp
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param message the new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the details.
     *
     * @return the details
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets the details.
     *
     * @param details the new details
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Response{" +
                "timeStamp='" + timeStamp + '\'' +
                ", message='" + message + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
