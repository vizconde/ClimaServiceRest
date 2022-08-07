package com.tpdaos2022.restServices;
import java.time.LocalDate;

import com.tpdaos2022.entities.Pronostico;

/**
 * Objeto necesario para insertar nuevos pronosticos. 
 *
 */
public class PronosticoDTO {
	
	private Long idCiudad;
	
	private LocalDate fechaPronostico;
	
	private Double porcentajeLluvia;
	
	private Double lluviaPrevista;
	
	private String descripcionPronostico;
	
	private Boolean eventoExtremo;

	public Long getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(Long idCiudad) {
		this.idCiudad = idCiudad;
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
	
	public Pronostico toPojo() {
		Pronostico pr = new Pronostico();
		pr.setFechaPronostico(this.getFechaPronostico());
		pr.setPorcentajeLluvia(this.getPorcentajeLluvia());
		pr.setLluviaPrevista(this.getLluviaPrevista());
		pr.setDescripcionPronostico(this.getDescripcionPronostico());
		pr.setEventoExtremo(this.getEventoExtremo());
		
		return pr;
	}

}
