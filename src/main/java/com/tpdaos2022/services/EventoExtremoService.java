package com.tpdaos2022.services;
import java.util.List;
import java.util.Optional;
import com.tpdaos2022.entities.Ciudad;
import com.tpdaos2022.entities.Suscripcion;



public interface EventoExtremoService {

	/**
	 * Obtiene lista de suscriptos de una ciudad determinada
	 * @return
	 */
	public java.util.List<Suscripcion> buscarSuscriptos(Optional<Ciudad> ciudad);
	

	
	
	/**
	 * prepara y manda email a cada suscripto
	 */
	
	public void prepararMails(List<Suscripcion> susc, String cuerpoMensaje);
	
	
	/**
	 * manda emails
	 */
	public void mandarMails(Long idCiudad, String cuerpoMensaje);
	
	
	/**
	 * obtiene una ciudad a partir de su id
	 */
	public Optional<Ciudad> obtenerCiudad(Long idCiudad) throws Exception;
	
	
	
}
