package com.tpdaos2022.services;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpdaos2022.repo.ClimaActualRepo;
import com.tpdaos2022.exceptions.Excepcion;
import com.tpdaos2022.entities.ClimaActual;
import com.tpdaos2022.entities.Ciudad;


@Service
public class ClimaActualServiceImpl implements ClimaActualService {

	@Autowired
	private ClimaActualRepo repoClimaActual;
	
	@Autowired
	private  Validator validator;
	
	@Override
	public Optional<ClimaActual> getByCiudad(Optional<Ciudad> c) {
		
		return repoClimaActual.findByCiudad(c);
	}
	
	//almacena un objeto ClimaActual
	@Override
	public void insert(ClimaActual ca) throws Exception {
	
			repoClimaActual.save(ca);
		
	}
	
	//actualiza un objeto clima actual
	@Override
	public void update(ClimaActual ca) throws Excepcion {
		
		Set<ConstraintViolation<ClimaActual>> cav = validator.validate(ca);
		if(cav.size()>0)
		{
			String err="";
			for (ConstraintViolation<ClimaActual> constraintViolation : cav) {
				err+=constraintViolation.getPropertyPath()+": "+constraintViolation.getMessage()+"\n";
			}
			throw new Excepcion(err,400);
		}
		else if(!repoClimaActual.findById(ca.getId()).isPresent())
		{
			throw new Excepcion("No se encuentra el clima que desea modificar.",400);
		}
		else
			repoClimaActual.save(ca);
	}
	
}
