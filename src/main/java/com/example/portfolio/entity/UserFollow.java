package com.example.portfolio.entity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
public class UserFollow implements Serializable{
    private static final long serialVersionUID = 1L;
    private User user;
    private Integer status;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
