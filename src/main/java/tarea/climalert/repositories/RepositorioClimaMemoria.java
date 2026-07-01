package tarea.climalert.repositories;

import org.springframework.stereotype.Repository;
import tarea.climalert.models.WeatherRecord;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioClimaMemoria implements RepositorioClima {

  private final List<WeatherRecord> historialClima = new ArrayList<>();

  @Override
  public void save(WeatherRecord record) {
    historialClima.add(record);
    System.out.println("Registro de clima guardado en memoria: " + record);
  }

  @Override
  public WeatherRecord findUltimoRegistro() {
    if (historialClima.isEmpty()){
      return null;
    }
    return historialClima.get(historialClima.size() - 1);
  }
}
