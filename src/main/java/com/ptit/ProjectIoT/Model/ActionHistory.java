package com.ptit.ProjectIoT.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "actionhistory")
public class ActionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String device;
    private String action;
    private LocalDateTime dateTime;

    public ActionHistory() {
        this.dateTime = LocalDateTime.now();
    }

    public ActionHistory(String device, String action) {
        this.device = device;
        this.action = action;
        this.dateTime = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "ActionHistory{" +
                "id=" + id +
                ", device='" + device + '\'' +
                ", action='" + action + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
