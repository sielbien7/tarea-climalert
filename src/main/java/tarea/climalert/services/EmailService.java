package tarea.climalert.services;

import tarea.climalert.models.WeatherRecord;

public interface EmailService {
  void enviarAlerta(WeatherRecord registroClima);
}
