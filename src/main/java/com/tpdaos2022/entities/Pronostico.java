package com.tpdaos2022.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


@Entity
public class Pronostico {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne
	@NotNull(message = "La ciudad no puede ser nula")
	private Ciudad ciudad;
	
	@NotNull(message = "La fecha no puede ser nula")
	private LocalDate fechaPronostico;
	
	private Double porcentajeLluvia;
	
	private Double lluviaPrevista;
	
	private String descripcionPronostico;
	
	private Boolean eventoExtremo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public LocalDate getFechaPronostico() {
		return fechaPronostico;
	}

	public void setFechaPronostico(LocalDate fechaPronostico) {
		this.fechaPronostico = fechaPronostico;
	}

	public Double getPorcentajeLluvia() {
		return porcentajeLluvia;
	}

	public void setPorcentajeLluvia(Double porcentajeLluvia) {
		this.porcentajeLluvia = porcentajeLluvia;
	}

	public Double getLluviaPrevista() {
		return lluviaPrevista;
	}

	public void setLluviaPrevista(Double lluviaPrevista) {
		this.lluviaPrevista = lluviaPrevista;
	}

	public String getDescripcionPronostico() {
		return descripcionPronostico;
	}

	public void setDescripcionPronostico(String descripcionPronostico) {
		this.descripcionPronostico = descripcionPronostico;
	}

	public Boolean getEventoExtremo() {
		return eventoExtremo;
	}

	public void setEventoExtremo(Boolean eventoExtremo) {
		this.eventoExtremo = eventoExtremo;
	}
	
	
	
	

}
