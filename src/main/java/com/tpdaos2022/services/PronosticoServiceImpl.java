package com.tpdaos2022.services;



import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpdaos2022.entities.Ciudad;
import com.tpdaos2022.entities.Pronostico;
import com.tpdaos2022.exceptions.Excepcion;
import com.tpdaos2022.repo.PronosticoRepo;


@Service
public class PronosticoServiceImpl implements PronosticoService {
	
	@Autowired
	private PronosticoRepo repoPronostico;

	@Autowired
	private  Validator validator;
	
	@Autowired
	private EventoExtremoService serviceEvento;

	
	

	@Override
	public Optional<Pronostico> getByCiudadAndFecha(Optional<Ciudad> c, LocalDate fechaPronostico) {
		
		
		return repoPronostico.findByCiudadAndFechaPronostico(c, fechaPronostico);
		
	}
	
	public void insert(Pronostico pr) throws Excepcion {
		Set<ConstraintViolation<Pronostico>> cv = validator.validate(pr);
		if(cv.size()>0)
		{
			String err="";
			for (ConstraintViolation<Pronostico> constraintViolation : cv) {
				err+=constraintViolation.getPropertyPath()+": "+constraintViolation.getMessage()+"\n";
			}
			throw new Excepcion(err,400);
		}
		else if(repoPronostico.findByCiudadAndFechaPronostico(pr.getCiudad(), pr.getFechaPronostico()).isPresent())
		{
			throw new Excepcion("Ya existe un clima para esa ciudad, en esa fecha, se reemplazara",400);
		}
		else 
			repoPronostico.save(pr);
		    //si es evento extremo invocar al servicio evento extremo y pasar el pronóstico completo
			if (pr.getEventoExtremo() == true) {
				Long idCiudad = pr.getCiudad().getId();
				String cuerpoMensaje = "Alerta Evento Extremo! " + pr.getDescripcionPronostico() + ". " + "Con Fecha: " + pr.getFechaPronostico() + ". LLuvia prevista:  " + pr.getLluviaPrevista() + ". Porcentaje LLuvia: " + pr.getPorcentajeLluvia();
				serviceEvento.mandarMails(idCiudad, cuerpoMensaje);
	
}
	}
	
	
	@Override
	public List<Pronostico> getPronosticoRangoFechas(LocalDate fecha1, LocalDate fecha2, Optional<Ciudad> ciudad) throws Excepcion {
		
		
		
		
		
		//List<Pronostico> pronosticoExtendido = repoPronostico.findByRangoFechas(fecha1, fecha2, ciudad);
		List<Pronostico> pronosticoExtendido = repoPronostico.findByfechaPronosticoBetween(fecha1, fecha2);
		return pronosticoExtendido;
		
	}
	
	
	//actualiza el objeto Pronostico
	@Override
	public void update(Pronostico pr) throws Excepcion {
		
		Set<ConstraintViolation<Pronostico>> cav = validator.validate(pr);
		if(cav.size()>0)
		{
			String err="";
			for (ConstraintViolation<Pronostico> constraintViolation : cav) {
				err+=constraintViolation.getPropertyPath()+": "+constraintViolation.getMessage()+"\n";
			}
			throw new Excepcion(err,400);
		}
		else if(!repoPronostico.findById(pr.getId()).isPresent())
		{
			throw new Excepcion("No se encuentra el clima que desea modificar.",400);
		}
		else
			repoPronostico.save(pr);
		  //si es evento extremo invocar al servicio evento extremo y pasar el pronóstico completo
		if (pr.getEventoExtremo() == true) {
			Long idCiudad = pr.getCiudad().getId();
			String cuerpoMensaje = "Alerta Evento Extremo! " + pr.getDescripcionPronostico() + ". " + "Con Fecha: " + pr.getFechaPronostico() + ". LLuvia prevista:  " + pr.getLluviaPrevista() + ". Porcentaje LLuvia: " + pr.getPorcentajeLluvia();
			serviceEvento.mandarMails(idCiudad, cuerpoMensaje);
	}
	
	
	}
	
}