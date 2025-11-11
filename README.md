#  Real-Time IoT Sensor Data Monitoring & Analytics Dashboard

A **Java + MySQL + HTML/CSS/JavaScript** based project that provides a **real-time web dashboard** for monitoring **IoT sensor data** (Temperature, Humidity, Air Quality).  
The system simulates IoT devices sending live sensor readings to a backend server, which stores data in MySQL and visualizes it on a clean, web-based dashboard.

---

##  Features

✅ Real-time sensor data updates (Temperature, Humidity, Air Quality)  
✅ Java-based backend with MySQL database integration  
✅ Live Dashboard with charts and analytics  
✅ Data simulator using Java (no hardware required)  
✅ Easy to set up and run locally  
✅ full-stack structure (Java + DB + Frontend)

---


## Run (Local, without Docker)
1. Start MySQL and create database `iotdb` or update `application.yml`.
2. Build backend:
   ```
   cd backend
   mvn clean package
   ```
3. Run backend:
   ```
   java -jar target/iot-dashboard-0.0.1-SNAPSHOT.jar
   ```
4. Serve frontend:
   ```
   npx http-server frontend
   ```
5. Run simulator:
   ```
   JavaSensorSimulator.java


   ```

## Docker
1. Build and run:
   ```
   docker-compose up --build
   ```

