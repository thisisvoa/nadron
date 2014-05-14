package io.ybt.zombie.domain;


public class UserInfo {
    public String id;
    public String name;
    public String role;
    public String status;
    public String isYb;
    public String details;

    public UserInfo(String id, String name, String role, String status, String isYb, String details) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.status = status;
        this.isYb = isYb;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsYb() {
        return isYb;
    }

    public void setIsYb(String isYb) {
        this.isYb = isYb;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
