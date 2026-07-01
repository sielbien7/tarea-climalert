package tarea.climalert.services;

import org.springframework.stereotype.Service;
import tarea.climalert.models.WeatherRecord;


public class MockEmailServiceImpl implements EmailService {
  @Override
  public void enviarAlerta(WeatherRecord registroClima) {
    System.out.println("==================================================");
    System.out.println("[MOCK EMAIL] Iniciando simulación de envío...");
    System.out.println("Destinatarios: admin@clima.com, emergencias@clima.com, meteorologia@clima.com");
    System.out.println("Asunto: ALERTA CLIMÁTICA CRÍTICA");
    System.out.println("Cuerpo: Se han detectado condiciones extremas.");
    System.out.println("        Temperatura actual: " + registroClima.getTemperature() + "°C");
    System.out.println("        Humedad actual: " + registroClima.getHumidity() + "%");
    System.out.println(" [MOCK EMAIL] Simulación finalizada con éxito.");
    System.out.println("==================================================");
  }
}
