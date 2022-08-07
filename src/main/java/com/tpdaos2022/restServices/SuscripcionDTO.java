package com.tpdaos2022.restServices;
import com.tpdaos2022.entities.Suscripcion;
/**
 * Objeto necesario para insertar nuevas suscripciones. 
 *
 */
public class SuscripcionDTO {
	
	private String email;
	
	private Long idCiudad;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(Long idCiudad) {
		this.idCiudad = idCiudad;
	}
	
	
	public Suscripcion toPojo() {
		
		Suscripcion susc = new Suscripcion();
		
		susc.setEmail(this.getEmail());		
		return susc;
	}
	
}
