package com.tpdaos2022.restServices;

import org.springframework.hateoas.RepresentationModel;

import com.tpdaos2022.entities.ClimaActual;

/**
 * Objeto utilizado para construir la respuesta de los servicios
 * 
 *
 */



public class ClimaActualResponseDTO extends  RepresentationModel<ClimaActualResponseDTO> {
	
	
	private Double temperatura;
	
	private String estadoClima;
	
	private Double humedad;
	
	public ClimaActualResponseDTO(ClimaActual pojo) {
		super();
		this.temperatura=pojo.getTemperatura();
		this.estadoClima=pojo.getEstadoClima();
		this.humedad=pojo.getHumedad();		
		
	}

	public Double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}

	public String getEstadoClima() {
		return estadoClima;
	}

	public void setEstadoClima(String estadoClima) {
		this.estadoClima = estadoClima;
	}

	public Double getHumedad() {
		return humedad;
	}

	public void setHumedad(Double humedad) {
		this.humedad = humedad;
	}
	
	
	
	
}
