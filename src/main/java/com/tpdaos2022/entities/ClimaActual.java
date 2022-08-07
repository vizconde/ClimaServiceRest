package com.tpdaos2022.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;



@Entity
public class ClimaActual {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Double temperatura;
	
	private String estadoClima;
	
	private Double humedad;
	
	@OneToOne
	@NotNull(message = "La ciudad no puede ser nula")
	private Ciudad ciudad;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	
	
}
