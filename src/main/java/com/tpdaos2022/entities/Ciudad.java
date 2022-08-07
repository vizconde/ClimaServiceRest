package com.tpdaos2022.entities;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ciudad {

	@Id
	private Long id;
	
	private String nombre;
	private String cp;
	
	public Ciudad()
	{
		super();
	}

	public Ciudad(Long id, String nombre, String cp) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cp=cp;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}


	
	
	
	
}
