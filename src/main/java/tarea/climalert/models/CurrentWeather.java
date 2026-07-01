package tarea.climalert.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CurrentWeather {
  @JsonProperty("temp_c")
  private double tempC;
  private double humidity;

  @JsonProperty("feelslike_c")
  private double feelslikeC;

  @JsonProperty("wind_kph")
  private double windKph;

  private Condition condition;
}
