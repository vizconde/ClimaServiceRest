package com.tpdaos2022.restServices;


/**
 * Objeto necesario para modificar-insertar un nuevo Mail. 
 *
 */
public class MailDTO {
	
	private String email;
	
	private String cuerpoMensaje;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCuerpoMensaje() {
		return cuerpoMensaje;
	}
	
	public void setCuerpoMensaje(String cuerpoMensaje) {
		this.cuerpoMensaje = cuerpoMensaje;
	}
	
	
	
}
