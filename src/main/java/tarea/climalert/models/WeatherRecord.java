package tarea.climalert.models;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class WeatherRecord {

  private LocalDateTime timestamp;
  private double temperature;
  private double humidity;
  private double feelslike;
  private double windSpeed;
  private String condition;

  public WeatherRecord(double temperature, double humidity, double feelslike, double windSpeed, String condition) {
    this.temperature = temperature;
    this.humidity = humidity;
    this.timestamp = LocalDateTime.now();
    this.feelslike = feelslike;
    this.windSpeed = windSpeed;
    this.condition = condition;
  }
}
