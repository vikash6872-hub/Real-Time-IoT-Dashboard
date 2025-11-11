package com.example.iotdashboard.service;

import com.example.iotdashboard.repository.SensorReadingRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class AggregationService {

    private final SensorReadingRepository readingRepo;

    public AggregationService(SensorReadingRepository readingRepo) {
        this.readingRepo = readingRepo;
    }

    @Scheduled(fixedRate = 60000)
    public void computeRollingStats() {
        Instant now = Instant.now();
        Instant from = now.minus(5, ChronoUnit.MINUTES);
        // Placeholder: add aggregation logic as needed
    }
}
