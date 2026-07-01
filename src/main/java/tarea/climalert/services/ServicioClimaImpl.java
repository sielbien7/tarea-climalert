package tarea.climalert.services;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tarea.climalert.models.WeatherRecord;
import tarea.climalert.models.WeatherApiResponse;
import tarea.climalert.repositories.RepositorioClima;

@Service
public class ServicioClimaImpl implements ServicioClima{
  private final RepositorioClima repositorioClima;
  private final RestTemplate restTemplate;

  @Value("${weather.api.url}")
  private String apiUrl;

  @Value("${weather.api.key}")
  private String apiKey;

  @Value("${weather.api.location}")
  private String location;

  public ServicioClimaImpl(RepositorioClima repositorioClima) {
    this.repositorioClima = repositorioClima;
    this.restTemplate = new RestTemplate();
  }

  @Override
  @Scheduled(fixedRate = 300000)
  public void obtenerClimaActual() {
    System.out.println("Consultando el clima de la ubicacion " + location + "...");

    String urlCompleta = apiUrl + "?key=" + apiKey + "&q=" + location;

    try{
      WeatherApiResponse respuesta = restTemplate.getForObject(urlCompleta, WeatherApiResponse.class);

      if(respuesta != null && respuesta.getCurrent() != null) {
        double temp = respuesta.getCurrent().getTempC();
        double hum = respuesta.getCurrent().getHumidity();
        double sensacion = respuesta.getCurrent().getFeelslikeC();
        double viento = respuesta.getCurrent().getWindKph();
        String condicionCielo = respuesta.getCurrent().getCondition().getText();

        WeatherRecord nuevoRegistro = new WeatherRecord(temp, hum, sensacion, viento, condicionCielo);
        repositorioClima.save(nuevoRegistro);
      }
    } catch (Exception e) {
      System.err.println("Error al consultar la API: " + e.getMessage());
    }
  }
}
