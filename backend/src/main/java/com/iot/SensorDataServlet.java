
package com.iot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet("/api/v1/readings")
public class SensorDataServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            // Read JSON data from request
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            // Parse JSON
            JSONObject json = new JSONObject(sb.toString());
            double temperature = json.getDouble("temperature");
            double humidity = json.getDouble("humidity");
            double air_quality = json.getDouble("air_quality");

            // Connect to MySQL
            Connection con = DBConnection.getConnection();

            // Insert data
            String query = "INSERT INTO sensor_data (temperature, humidity, air_quality) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setDouble(1, temperature);
            ps.setDouble(2, humidity);
            ps.setDouble(3, air_quality);
            ps.executeUpdate();

            con.close();

            // Response success
            out.print("{\"status\":\"success\"}");
            out.flush();

            System.out.println("✅ Data Saved: Temp=" + temperature + "°C, Hum=" + humidity + "%, AQI=" + air_quality);

        } catch (Exception e) {
            e.printStackTrace();
            out.print("{\"status\":\"error\", \"message\":\"" + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        response.getWriter().println("IoT Sensor Data Servlet is running ✅");
    }
}

