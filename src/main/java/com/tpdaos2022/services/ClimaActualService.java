package com.tpdaos2022.services;

import java.util.Optional;
import com.tpdaos2022.entities.ClimaActual;
import com.tpdaos2022.exceptions.Excepcion;
import com.tpdaos2022.entities.Ciudad;


public interface ClimaActualService {

	/**
	 * Obtiene un clima a partir de la ciudad
	 * @param Ciudad
	 * @return
	 */
	public Optional<ClimaActual> getByCiudad(Optional<Ciudad> c) throws Excepcion;
	
	/**
	 * Inserta un nuevo estado de clima
	 * @param ca
	 * @throws Exception
	 */
	public void insert(ClimaActual ca) throws Exception;
	
	/**
	 * Actualiza clima pasando el id
	 * @param p
	 * @throws Excepcion 
	 */
	public void update(ClimaActual ca) throws Excepcion;
}
