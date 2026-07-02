# Climalert Tarea


## 1. Configuración de Entorno
Para ejecutar el proyecto, es necesario configurar las siguientes variables de entorno en el entorno de desarrollo:

* **WEATHER_API_KEY**: API Key obtenida en WeatherAPI.
* **MAIL_PASSWORD**: Contraseña de aplicación (16 caracteres) generada en la cuenta de Google.

## 2. Personalización
Los parámetros operativos se definen en el archivo `src/main/resources/application.properties`:

* **weather.api.location**: Ubicación para el monitoreo.
* **alerta.condicion.temperatura.minima**: Límite de temperatura para activar la alerta.
* **alerta.condicion.humedad.minima**: Límite de humedad para activar la alerta.
* **alerta.correos.destinos**: Lista de correos destinatarios separados por coma.


---