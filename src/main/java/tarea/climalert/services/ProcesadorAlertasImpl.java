package tarea.climalert.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tarea.climalert.models.AlertaMonitor;
import tarea.climalert.models.WeatherRecord;
import tarea.climalert.repositories.RepositorioClima;
import java.time.LocalDateTime;

@Service
public class ProcesadorAlertasImpl implements ProcesadorAlertas {

  private final RepositorioClima repositorioClima;
  private final EmailService emailService;
  private final AlertaMonitor monitor;

  private LocalDateTime ultimaAlerta = null;



  public ProcesadorAlertasImpl (RepositorioClima repositorioClima, EmailService emailService,
                                @Value("${alerta.condicion.temperatura.minima}") double tempMin,
                                @Value("${alerta.condicion.humedad.minima}") double humMin){
    this.repositorioClima = repositorioClima;
    this.emailService = emailService;
    this.monitor = new AlertaMonitor(tempMin, humMin);
  }

  @Override
  @Scheduled(fixedRate = 60000)
  public void analizarCondicionesClimaticas() {
    System.out.println("Analizando alertas meteorológicas...");

    WeatherRecord ultimoRegistro = repositorioClima.findUltimoRegistro();

    if (ultimoRegistro == null) {
      System.out.println("Esperando datos climáticos...");
      return;
    }

    if(monitor.debeAlertar(ultimoRegistro)){
      emailService.enviarAlerta(ultimoRegistro);
      monitor.registrarAlerta(ultimoRegistro);
    } else if (!monitor.esCritico(ultimoRegistro)) {
      System.out.println("Clima normal :D. Temp: " + ultimoRegistro.getTemperature() + "°C | Hum: " + ultimoRegistro.getHumidity() + "%");
    }
  }
}
