package tarea.climalert.repositories;

import tarea.climalert.models.WeatherRecord;

public interface RepositorioClima {

  void save (WeatherRecord clima);
  WeatherRecord findUltimoRegistro();
}
