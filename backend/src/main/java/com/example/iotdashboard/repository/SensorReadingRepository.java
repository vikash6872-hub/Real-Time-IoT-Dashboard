package com.example.iotdashboard.repository;

import com.example.iotdashboard.entity.SensorReading;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.Instant;

public interface SensorReadingRepository extends JpaRepository<SensorReading, Long> {
    Page<SensorReading> findByDeviceIdAndSensorTypeAndTsBetween(
        String deviceId, String sensorType, Instant from, Instant to, Pageable pageable);
}
