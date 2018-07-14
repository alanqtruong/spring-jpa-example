package com.example.spring.jpa.models;

import java.io.Serializable;
import java.util.Date;

/**
 * @author recklessN1nja
 */
public class UserMessageError implements Serializable {
    private String errorMessage;
    private Date date;

    public UserMessageError(String errorMessage, Date date) {
        this.errorMessage = errorMessage;
        this.date = date;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "UserMessageError{" +
                "errorMessage='" + errorMessage + '\'' +
                ", date=" + date +
                '}';
    }
}
