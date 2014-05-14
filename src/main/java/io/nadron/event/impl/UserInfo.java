package io.nadron.event.impl;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInfo)) return false;

        UserInfo userInfo = (UserInfo) o;

        if (details != null ? !details.equals(userInfo.details) : userInfo.details != null) return false;
        if (id != null ? !id.equals(userInfo.id) : userInfo.id != null) return false;
        if (isYb != null ? !isYb.equals(userInfo.isYb) : userInfo.isYb != null) return false;
        if (name != null ? !name.equals(userInfo.name) : userInfo.name != null) return false;
        if (role != null ? !role.equals(userInfo.role) : userInfo.role != null) return false;
        if (status != null ? !status.equals(userInfo.status) : userInfo.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (isYb != null ? isYb.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        return result;
    }
}
