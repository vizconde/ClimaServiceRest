package com.tpdaos2022.services;




import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.tpdaos2022.entities.Ciudad;
import com.tpdaos2022.entities.Pronostico;
import com.tpdaos2022.exceptions.Excepcion;

public interface PronosticoService {

	/**
	 * Obtiene un pronostico
	 * @param Ciudad AND fecha
	 * @return
	 */
	public Optional<Pronostico> getByCiudadAndFecha(Optional<Ciudad> c, LocalDate fechaPronostico);
	
	
	/**
	 * Obtiene una lista de 10 pronosticos (si los hubiera) en base a la fecha pasada y ciudad pasada como argumento
	 * @param Ciudad
	 * @param fecha
	 * @return
	 */
	public List<Pronostico> getPronosticoRangoFechas(LocalDate fecha1, LocalDate fecha2, Optional<Ciudad> ciudad) throws Excepcion;
	
	
	/**
	 * Inserta un nuevo pronostico
	 * @param p
	 * @throws Exception
	 */
	public void insert(Pronostico pr) throws Exception;
	
	
	/**
	 * Actualiza Pronostico
	 * @param p
	 * @throws Excepcion 
	 */
	public void update(Pronostico pr) throws Excepcion;

	
}
