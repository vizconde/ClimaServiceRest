package com.tpdaos2022.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tpdaos2022.entities.Pronostico;
import com.tpdaos2022.exceptions.Excepcion;
import com.tpdaos2022.repo.PronosticoRepo;

@Service
public class PronosticoExtendidoServiceImpl implements PronosticoExtendidoService {
	
	
	@Autowired
	private PronosticoRepo repoPronostico;
	

	@Override
	public List<Pronostico> pronosticosRango(LocalDate fecha1, Long idCiudad) throws Excepcion {
		
		//recibo como parametro una fecha y una ciudad
		//sumo 10 dias a la fecha para obtener el rango
		//obtengo la segunda fecha que el extremo del intervalo de fechas
		//llamo al servicio Pronostico para pasar los argumentos al metodo encargado de filtrar los pronosticos por ciudad y rango de fechas
		
		LocalDate fecha2 = fecha1.plusDays(10);
		
		List<Pronostico> prex = repoPronostico.filtrarExtCiudad(fecha1, fecha2, idCiudad);
		
		return prex;
		
	}
	
	
	
	
}
