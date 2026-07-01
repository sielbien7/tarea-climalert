package tarea.climalert.services;

import org.springframework.web.client.RestTemplate;
import tarea.climalert.repositories.RepositorioClima;

public interface ServicioClima {
 void obtenerClimaActual();
}
