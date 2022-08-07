package com.tpdaos2022.services;
import com.tpdaos2022.exceptions.Excepcion;



public interface MailService {
	
	public void mandarMail(String email, String cuerpoMensaje) throws Excepcion;

}
