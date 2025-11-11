# Real-Time-IoT-Dashboard

Real-Time IoT Sensor Data Monitoring & Analytics Dashboard
Stack: Java (Spring Boot), MySQL, WebSocket (STOMP/SockJS), HTML/CSS/JS (Chart.js)

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

