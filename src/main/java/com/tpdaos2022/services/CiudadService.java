package com.tpdaos2022.services;

import java.util.Optional;

import com.tpdaos2022.entities.Ciudad;



public interface CiudadService {
	
	
	/**
	 * Obtiene la lista completa de ciudades
	 * @return
	 */
	public java.util.List<Ciudad> findAll();

	/**
	 * Obtiene una ciudad
	 * @param id identificador de la ciudad requerida
	 * @return 
	 */
	public Optional<Ciudad> getById(Long id);
	
	//metodo alternativo para obtener una ciudad
	public Ciudad getByIdentificador(Long id);
	

	
	
}
