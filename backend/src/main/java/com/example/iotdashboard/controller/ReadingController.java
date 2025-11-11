package com.example.iotdashboard.controller;

import com.example.iotdashboard.dto.SensorReadingDto;
import com.example.iotdashboard.entity.SensorReading;
import com.example.iotdashboard.repository.DeviceRepository;
import com.example.iotdashboard.repository.SensorReadingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class ReadingController {
    private final SensorReadingRepository readingRepo;
    private final DeviceRepository deviceRepo;
    private final SimpMessagingTemplate messagingTemplate;

    public ReadingController(SensorReadingRepository readingRepo, DeviceRepository deviceRepo,
                             SimpMessagingTemplate messagingTemplate) {
        this.readingRepo = readingRepo;
        this.deviceRepo = deviceRepo;
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping("/readings")
    public ResponseEntity<?> ingest(@RequestBody List<SensorReadingDto> payload) {
        List<SensorReading> saved = payload.stream()
                .map(dto -> new SensorReading(dto.getDeviceId(), dto.getSensorType(), dto.getValue(),
                        dto.getTs() == null ? Instant.now() : dto.getTs()))
                .map(readingRepo::save)
                .collect(Collectors.toList());

        saved.forEach(r -> {
            messagingTemplate.convertAndSend("/topic/readings", r);
            messagingTemplate.convertAndSend("/topic/readings/" + r.getDeviceId(), r);
        });

        return ResponseEntity.ok().build();
    }

    @GetMapping("/readings")
    public ResponseEntity<?> history(@RequestParam String deviceId,
                                     @RequestParam String sensorType,
                                     @RequestParam long fromEpochMs,
                                     @RequestParam long toEpochMs,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "50") int size) {
        var p = readingRepo.findByDeviceIdAndSensorTypeAndTsBetween(deviceId, sensorType,
                Instant.ofEpochMilli(fromEpochMs), Instant.ofEpochMilli(toEpochMs),
                org.springframework.data.domain.PageRequest.of(page, size));
        return ResponseEntity.ok(p.getContent());
    }
}
