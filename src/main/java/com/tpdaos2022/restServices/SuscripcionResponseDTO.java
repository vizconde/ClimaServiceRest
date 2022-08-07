package com.tpdaos2022.restServices;

import org.springframework.hateoas.RepresentationModel;

import com.tpdaos2022.entities.Suscripcion;


/**
 * Objeto utilizado para construir la respuesta de los servicios
 * 
 *
 */
public class SuscripcionResponseDTO extends  RepresentationModel<SuscripcionResponseDTO>  {
	
	private String email;
	
	//constructor
	public SuscripcionResponseDTO(Suscripcion pojo) {
		super();
		this.email = pojo.getEmail();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
