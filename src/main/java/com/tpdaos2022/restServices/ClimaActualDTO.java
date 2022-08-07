package com.tpdaos2022.restServices;
import com.tpdaos2022.entities.ClimaActual;


/**
 * Objeto necesario para insertar. 
 * Nótese que en lugar de referenciar al objeto Ciudad, reemplaza ese atributo por el idCiudad, lo cual resulta mas sencillo de representar en JSON
 *
 */

public class ClimaActualDTO {

	private Double temperatura;
	
	private String estadoClima;
	
	private Double humedad;
	
	private Long idCiudad;
	
	
	
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
	
	public Long getIdCiudad() {
		return idCiudad;
	}
	public void setIdCiudad(Long idCiudad) {
		this.idCiudad = idCiudad;
	}
	
	
	
	public ClimaActual toPojo() {
		ClimaActual ca = new ClimaActual();
		ca.setTemperatura(this.getTemperatura());
		ca.setEstadoClima(this.getEstadoClima());
		ca.setHumedad(this.getHumedad());
		return ca;
	}


}
