package tarea.climalert.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tarea.climalert.models.WeatherRecord;
import java.time.format.DateTimeFormatter;

@Service
public class GmailEmailServiceImpl implements EmailService{

  private final JavaMailSender mailSender;

  @Value("${alerta.correos.destinos}")
  private String[] destinatarios;

  public GmailEmailServiceImpl(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Override
  public void enviarAlerta(WeatherRecord registroClima) {

    SimpleMailMessage mensaje = new SimpleMailMessage();

    mensaje.setTo(destinatarios);
    mensaje.setSubject("Alerta climática crítica!" + registroClima.getCondition());

    DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    String fechaFormateada = registroClima.getTimestamp().format(formatoFecha);

    String cuerpo = "ATENCIÓN: Se han detectado condiciones meteorológicas extremas en la zona de monitoreo (CABA).\n\n" +
        " DETALLE COMPLETO DEL CLIMA:\n" +
        "----------------------------------------\n" +
        "• Fecha y Hora: " + fechaFormateada + "\n" +
        "• Estado del cielo: " + registroClima.getCondition() + "\n" +
        "• Temperatura real: " + registroClima.getTemperature() + " °C\n" +
        "• Sensación térmica: " + registroClima.getFeelslike() + " °C\n" +
        "• Humedad relativa: " + registroClima.getHumidity() + " %\n" +
        "• Viento: " + registroClima.getWindSpeed() + " km/h\n" +
        "----------------------------------------\n\n" +
        "Este es un mensaje automático del sistema Climalert. Por favor, tome las precauciones necesarias.";

    mensaje.setText(cuerpo);

    try {
      mailSender.send(mensaje);
      System.out.println("Correo enviado exitosamente con detalle completo.");
    } catch (Exception e) {
      System.err.println("Error al intentar enviar el correo: " + e.getMessage());
    }

  }
}
