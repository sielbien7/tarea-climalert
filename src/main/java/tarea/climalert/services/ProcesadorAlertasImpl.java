package tarea.climalert.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tarea.climalert.models.WeatherRecord;
import tarea.climalert.repositories.RepositorioClima;

@Service
public class ProcesadorAlertasImpl implements ProcesadorAlertas {

  private final RepositorioClima repositorioClima;
  private final EmailService emailService;

  @Value("${alerta.condicion.temperatura.minima}")
  private double umbralTemperatura;

  @Value("${alerta.condicion.humedad.minima}")
  private double umbralHumedad;


  public ProcesadorAlertasImpl (RepositorioClima repositorioClima, EmailService emailService){
    this.repositorioClima = repositorioClima;
    this.emailService = emailService;
  }

  @Override
  @Scheduled(fixedRate = 60000)
  public void analizarCondicionesClimaticas() {
    System.out.println("Analizando alertas meteorológicas...");

    WeatherRecord ultimoRegistro = repositorioClima.findUltimoRegistro();

    if(ultimoRegistro != null) {
      double temp = ultimoRegistro.getTemperature();
      double hum =  ultimoRegistro.getHumidity();

      if (temp > umbralTemperatura && hum > umbralHumedad) {

        //Mandar meil
        emailService.enviarAlerta(ultimoRegistro);

      } else {
        System.out.println("Clima normal :D. Temp: " + temp + "°C | Hum: " + hum + "%");
      }
    } else {
      System.out.println("Esperando datos climáticos...");
    }
  }
}
