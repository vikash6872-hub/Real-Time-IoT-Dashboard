package com.example.iotdashboard.dto;

import java.time.Instant;

public class SensorReadingDto {
    private String deviceId;
    private String sensorType;
    private Double value;
    private Instant ts;

    public String getDeviceId() { return deviceId; }
    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }
    public String getSensorType() { return sensorType; }
    public void setSensorType(String sensorType) { this.sensorType = sensorType; }
    public Double getValue() { return value; }
    public void setValue(Double value) { this.value = value; }
    public Instant getTs() { return ts; }
    public void setTs(Instant ts) { this.ts = ts; }
}
