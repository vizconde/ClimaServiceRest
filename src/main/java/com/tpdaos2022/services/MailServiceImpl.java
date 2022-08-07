package com.tpdaos2022.services;

import org.springframework.stereotype.Service;

import com.tpdaos2022.exceptions.Excepcion;


@Service
public class MailServiceImpl implements MailService {
	
	public void mandarMail(String email, String cuerpoMensaje) throws Excepcion {
		
		//escribir el mail por pantalla
		System.out.println(".......................................................................................");
		System.out.println("Destinatario: " + email );
		System.out.println("Mensaje: " + cuerpoMensaje );
	}

}
