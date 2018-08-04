package com.example.spring.jpa.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author alanqtruong
 */
public class UserResponseMessage implements Serializable {

    private String message;
    private Date date;

    public UserResponseMessage(String message, Date date) {
        this.message = message;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "UserResponseMessage{" +
                "message='" + message + '\'' +
                ", date=" + date +
                '}';
    }
}
