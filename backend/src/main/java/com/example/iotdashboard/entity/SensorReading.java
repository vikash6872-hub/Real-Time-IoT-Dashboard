package com.example.iotdashboard.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "sensor_readings", indexes = {
    @Index(name = "idx_device_ts", columnList = "deviceId, ts")
})
public class SensorReading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceId;
    private String sensorType;
    private Double value;
    private Instant ts;
    private Instant createdAt = Instant.now();

    public SensorReading() {}
    public SensorReading(String deviceId, String sensorType, Double value, Instant ts) {
        this.deviceId = deviceId; this.sensorType = sensorType; this.value = value; this.ts = ts;
    }
    // getters and setters
    public Long getId() { return id; }
    public String getDeviceId() { return deviceId; }
    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }
    public String getSensorType() { return sensorType; }
    public void setSensorType(String sensorType) { this.sensorType = sensorType; }
    public Double getValue() { return value; }
    public void setValue(Double value) { this.value = value; }
    public Instant getTs() { return ts; }
    public void setTs(Instant ts) { this.ts = ts; }
    public Instant getCreatedAt() { return createdAt; }
}
