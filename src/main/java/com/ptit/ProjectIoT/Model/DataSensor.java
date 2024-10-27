package com.ptit.ProjectIoT.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "datasensor")
public class DataSensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double temperature;
    private double humidity;
    private double brightness;
//    private double other;
    private LocalDateTime dateTime;

    public DataSensor() {
        this.dateTime = LocalDateTime.now();
    }

    public DataSensor(double temperature, double humidity, double brightness) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.brightness = brightness;
        this.dateTime = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getBrightness() {
        return brightness;
    }

    public void setBrightness(double brightness) {
        this.brightness = brightness;
    }

//    public double getOther() {
//        return other;
//    }

//    public void setOther(double other) {
//        this.other = other;
//    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "DataSensor{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", brightness=" + brightness +
                ", dateTime=" + dateTime +
                '}';
    }
}
