CREATE DATABASE IF NOT EXISTS iot_dashboard;
USE iot_dashboard;

CREATE TABLE IF NOT EXISTS devices (
  device_id INT AUTO_INCREMENT PRIMARY KEY,
  device_name VARCHAR(100),
  location VARCHAR(100),
  status VARCHAR(20) DEFAULT 'active'
);

CREATE TABLE IF NOT EXISTS sensor_data (
  id INT AUTO_INCREMENT PRIMARY KEY,
  device_id INT,
  temperature FLOAT,
  humidity FLOAT,
  air_quality FLOAT,
  ts DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (device_id) REFERENCES devices(device_id)
);

