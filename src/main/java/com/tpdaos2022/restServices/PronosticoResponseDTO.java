package com.tpdaos2022.restServices;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import com.tpdaos2022.entities.Pronostico;

/**
 * Objeto utilizado para construir la respuesta de los servicios
 * 
 *
 */
public class PronosticoResponseDTO extends  RepresentationModel<PronosticoResponseDTO> {
	
	private LocalDate fechaPronostico;
	
	private Double porcentajeLluvia;
	
	private Double lluviaPrevista;
	
	private String descripcionPronostico;
	
	private Boolean eventoExtremo;
	
	//constructor
	public PronosticoResponseDTO(Pronostico pojo) {
		super();
		this.fechaPronostico = pojo.getFechaPronostico();
		this.porcentajeLluvia = pojo.getPorcentajeLluvia();
		this.lluviaPrevista = pojo.getLluviaPrevista();
		this.descripcionPronostico = pojo.getDescripcionPronostico();
		this.eventoExtremo = pojo.getEventoExtremo();
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
