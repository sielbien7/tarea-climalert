package tarea.climalert.models;

import org.springframework.beans.factory.annotation.Value;

public class AlertaMonitor {
  private WeatherRecord ultimoProcesado = null;
  private final double umbralTemperatura;
  private final double umbralHumedad;

  public AlertaMonitor(double umbralTemperatura, double umbralHumedad) {
    this.umbralTemperatura = umbralTemperatura;
    this.umbralHumedad = umbralHumedad;
  }

  public boolean debeAlertar(WeatherRecord nuevo) {
    return esCritico(nuevo) && esNuevo(nuevo);
  }

  private boolean esNuevo(WeatherRecord nuevo){
    return ultimoProcesado == null || !ultimoProcesado.getTimestamp().equals(nuevo.getTimestamp());
  }

  public boolean esCritico(WeatherRecord registro){
    return registro.getTemperature() > umbralTemperatura && registro.getHumidity() > umbralHumedad;
  }

  public void registrarAlerta(WeatherRecord registro) {
    this.ultimoProcesado = registro;
  }



}
