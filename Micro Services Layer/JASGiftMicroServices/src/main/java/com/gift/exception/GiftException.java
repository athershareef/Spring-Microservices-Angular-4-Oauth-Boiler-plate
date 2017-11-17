package com.gift.exception;

import java.util.Date;

/**
 * The Class GiftException.
 * Standard Sample Exception Structure
 */
public class GiftException {
    
    /** The date time stamp. */
    private String dateTimeStamp;
    
    /** The message. */
    private String message;
    
    /** The details. */
    private String details;

    /**
     * Instantiates a new vehicle exception.
     *
     * @param dateTimeStamp the date time stamp
     * @param message the message
     * @param details the details
     */
    public GiftException(String dateTimeStamp, String message, String details) {
        super();
        this.dateTimeStamp = dateTimeStamp;
        this.message = message;
        this.details = details;
    }

    /**
     * Instantiates a new vehicle exception.
     *
     * @param message the message
     * @param details the details
     */
    public GiftException(String message, String details) {
        super();
        this.dateTimeStamp = new Date().toString();
        this.message = message;
        this.details = details;
    }

    /**
     * Instantiates a new vehicle exception.
     */
    public GiftException() {
    }

    /**
     * Gets the date time stamp.
     *
     * @return the date time stamp
     */
    public String getDateTimeStamp() {
        return dateTimeStamp;
    }

    /**
     * Sets the date time stamp.
     *
     * @param dateTimeStamp the new date time stamp
     */
    public void setDateTimeStamp(String dateTimeStamp) {
        this.dateTimeStamp = dateTimeStamp;
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
}

