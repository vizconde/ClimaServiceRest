package com.tpdaos2022.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpdaos2022.entities.Ciudad;
import com.tpdaos2022.repo.CiudadRepo;

@Service
public class CiudadServiceImpl implements CiudadService{

	@Autowired
	CiudadRepo repoCiudades;
	
	@Override
	public java.util.List<Ciudad> findAll()
	{
//		Ciudad c1= new Ciudad(1L,"Santa Fe","3000");
//		Ciudad c2= new Ciudad(2L,"Parana","3565");
//		List<Ciudad> ciudades= new ArrayList<Ciudad>();
//		ciudades.add(c1);
//		ciudades.add(c2);
//		
		return repoCiudades.findAll();
	}

	@Override
	public Optional<Ciudad> getById(Long id) {

		return repoCiudades.findById(id);
		
	}
	
	
	
	@Override
	public Ciudad getByIdentificador(Long id) {
		
		return repoCiudades.findByIdentificador(id);
	}
	

	
}