import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.util.Random;

public class JavaSensorSimulator {

    public static void main(String[] args) {
        Random random = new Random();
        String apiUrl = "http://localhost:8080/api/sensor-data";

        while (true) {
            try {
                double temperature = 20 + random.nextDouble() * 10;
                double humidity = 40 + random.nextDouble() * 30;
                double airQuality = 200 + random.nextDouble() * 100;

                String jsonData = String.format(
                    "{\"temperature\": %.2f, \"humidity\": %.2f, \"airQuality\": %.2f}",
                    temperature, humidity, airQuality
                );

                URL url = new URL(apiUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                try (OutputStream os = conn.getOutputStream()) {
                    os.write(jsonData.getBytes());
                }

                System.out.println("Sent → " + jsonData + " | Response Code: " + conn.getResponseCode());

                conn.disconnect();

                Thread.sleep(1000);

            } catch (Exception e) {
                System.out.println("Error sending data: " + e.getMessage());
                try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
            }
        }
    }
}
