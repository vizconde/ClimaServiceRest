package com.tpdaos2022.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpdaos2022.entities.Ciudad;
import com.tpdaos2022.entities.Suscripcion;
import com.tpdaos2022.exceptions.Excepcion;
import com.tpdaos2022.repo.SuscripcionRepo;

@Service
public class SuscripcionServiceImpl implements SuscripcionService {
	
	@Autowired
	private  SuscripcionRepo repoSuscripcion;
	
	@Autowired
	private  Validator validator;
	
	//implementacion metodo para crear una nueva suscripcion
	@Override
	public void insert(Suscripcion susc) throws Excepcion {
		
		Set<ConstraintViolation<Suscripcion>> cv = validator.validate(susc);
		if(cv.size()>0)
		{
			String err="";
			for (ConstraintViolation<Suscripcion> constraintViolation : cv) {
				err+=constraintViolation.getPropertyPath()+": "+constraintViolation.getMessage()+"\n";
			}
			throw new Excepcion(err,400);
		}
		else if(repoSuscripcion.findByCiudadAndEmail(susc.getCiudad(), susc.getEmail()).isPresent())
		{
			throw new Excepcion("Ya existe una suscripcion para ese email en la ciudad ingresada",400);
		}
		else
			repoSuscripcion.save(susc);
	}
	
	
	//implementa metodo para borrar una suscripcion
	@Override
	public void delete(String email) throws Excepcion {
		if(!repoSuscripcion.findByEmail(email).isPresent())
		{
			throw new Excepcion("No existe una suscripción con ese email",400);
		}
		else
			repoSuscripcion.deleteSuscripcionByEmail(email);
		
	}
	
	
	//obtiene una lista de suscriptores, implementacion
	@Override
	public List<Suscripcion> filtrar(Optional<Ciudad> ciudad) {
		
		//condicional para manejar errores
		return repoSuscripcion.findByCiudad(ciudad);
		
	}

}