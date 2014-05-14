package io.nadron.event.impl;


import java.io.Serializable;

public class ChatMessage implements Serializable {

    public String id;
    public String type;
    public String message;
    public int color;
    public int time;
    public String source;
    public String sourceName;
    public String destination;
    public String status;


    public ChatMessage() {
    }

    public ChatMessage(String id, String type, String message, int color, int time, String source, String sourceName, String destination, String status) {
        this.id = id;
        this.status = status;
        this.destination = destination;
        this.sourceName = sourceName;
        this.source = source;
        this.time = time;
        this.color = color;
        this.message = message;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatMessage)) return false;

        ChatMessage that = (ChatMessage) o;

        if (color != that.color) return false;
        if (time != that.time) return false;
        if (destination != null ? !destination.equals(that.destination) : that.destination != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (source != null ? !source.equals(that.source) : that.source != null) return false;
        if (sourceName != null ? !sourceName.equals(that.sourceName) : that.sourceName != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + color;
        result = 31 * result + time;
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (sourceName != null ? sourceName.hashCode() : 0);
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
