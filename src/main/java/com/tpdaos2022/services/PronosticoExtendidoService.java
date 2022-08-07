package com.tpdaos2022.services;

import java.time.LocalDate;
import java.util.List;
import com.tpdaos2022.entities.Pronostico;
import com.tpdaos2022.exceptions.Excepcion;


public interface PronosticoExtendidoService {
	
	//recibo como parametro una fecha y una ciudad
	//sumo 10 dias a la fecha para obtener el rango
	//obtengo la segunda fecha que el extremo del intervalo de fechas
	//llamo al servicio Pronostico para pasar los argumentos al metodo encargado de filtrar los pronosticos por ciudad y rango de fechas
	
	public List<Pronostico> pronosticosRango(LocalDate fecha1, Long idCiudad) throws Excepcion;
	
	
	
}
