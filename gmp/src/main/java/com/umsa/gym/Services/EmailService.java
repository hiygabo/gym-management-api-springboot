package com.umsa.gym.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void enviarCorreoRechazo(String destinatario, String nombreCliente){
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setFrom("gaboandiaalave@gmail.com");
        mensaje.setTo(destinatario);
        mensaje.setSubject("Aviso importante: Problema con tu inscripcion"); 
        mensaje.setText("Hola "+ nombreCliente + ",\n\n"
            + "Hemos revisado tu solicitud de inscripcion, pero lamentablemente no pudimos verificar tu deposito con el comprobante adjunto .\n\n"
            + "Por favor, verifica que la transferencia se haya realizado correctamente y vuelve a llenar el formulario"
            +"Recomendaciones: \n\n"
            +"Adjunta tu comprobante, formato PDF o imagen (visibilidad clara) \n\n "
            +"Adjunta una imagen clara de tu depósito bancaraio"
        );
        // Enviar el correo
        mailSender.send(mensaje);
    }

    public void enviarCorreoAprobacion(String destinatario, String nombreCliente, byte[] pdfAdjunto) throws MessagingException{
        MimeMessage mensaje = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);
        helper.setFrom("gaboandiaalave@gmail.com");
        helper.setTo(destinatario);
        helper.setSubject("¡Bienvenido al Gimnasio! Tu suscripción está activa");
        helper.setText("Hola " + nombreCliente + ",\n\n"
                + "¡Tu pago ha sido verificado y tu suscripción ya está activa!\n\n"
                + "Adjuntamos a este correo el detalle oficial de tu plan de suscripción en formato PDF. Por favor, preséntalo en tu primer día.\n\n"
                + "¡A darle con todo!\nEl equipo del Gimnasio.");
        helper.addAttachment("Detalle_Suscripcion.pdf", new ByteArrayResource(pdfAdjunto));

        mailSender.send(mensaje);
    }


}
