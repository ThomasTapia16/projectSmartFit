package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.models.Solicitud;

@Service
public class SendEmail {


    //Importante hacer la inyección de dependencia de JavaMailSender:
    @Autowired
    private JavaMailSender mailSender;

    //Pasamos por parametro: destinatario, asunto y el mensaje
   
    public void sendEmail(String to, String nombre, String pwd) {

        SimpleMailMessage email = new SimpleMailMessage();

        email.setTo(to);
        email.setSubject("Hola "+nombre+", hemos creado tu cuenta con éxito!");
        email.setText("Tu clave se generó automáticamente: "+pwd+" , inicia sesión con ella en tu cuenta y cambia la contraseña.\nEquipo de desarrollo numero 2 paralelo 702");

        mailSender.send(email);

    }
    
    public void sendEmailRechazo(String to,Long id,String motivo)
    {
    	SimpleMailMessage email = new SimpleMailMessage();

        email.setTo(to);
        email.setSubject("Solicitud de ingreso de colaborador al sistema rechazada");
        email.setText("La solicitud de id: "+id+" ha sido rechazada por el siguiente motivo:"+ motivo);

        mailSender.send(email);
    }
    
    public void sendEmailSolicitud(String to, Solicitud solicitud)
    {
    	SimpleMailMessage email = new SimpleMailMessage();

        email.setTo(to);
        email.setSubject("La solicitud fue enviada con éxito");
        email.setText("ID de la solicitud:"+solicitud.getId()+
        		"\nRut:"+solicitud.getRut()+
        		"\nNombre:"+solicitud.getNombre()+" "+solicitud.getApellido()+
        		"\nSede:"+solicitud.getSede().getNombre());
        mailSender.send(email);
        		
    }
    
}