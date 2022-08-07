package com.tpdaos2022.services;

import java.util.List;
import java.util.Optional;
import com.tpdaos2022.entities.Ciudad;
import com.tpdaos2022.entities.Suscripcion;
import com.tpdaos2022.exceptions.Excepcion;

//Inserta una nueva suscripcion
public interface SuscripcionService {
	
	//Inserta una nueva suscripcion
	public void insert(Suscripcion susc) throws Excepcion;
	
	
	/**
	 * Elimina una suscripcion del sistema
	 * @param email
	 * @throws Excepcion 
	 */
	public void delete(String email) throws Excepcion;
	
	/**
	 * Obtiene una lista de suscriptores
	 * @param ciudad
	 * @throws Excepcion 
	 */
	public List<Suscripcion> filtrar(Optional<Ciudad> ciudad);

	
	
}


