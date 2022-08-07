package com.tpdaos2022.restServices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpdaos2022.entities.Ciudad;
import com.tpdaos2022.services.CiudadService;


@RestController
@RequestMapping("/ciudades")
public class CiudadController {
	
	@Autowired
	private CiudadService service;
	
	/**
	 * Obtiene una ciudad a través de su id.
	 	/**
	 * Obtiene una nuevo estado de Clima pasando la ciudad como paramatro
	 * 			curl --location --request GET 'http://localhost:8081/{idCiudad} 
	 *			--header 'Accept: application/json' 
	 * 			--header 'Content-Type: application/json' 
	 * @param idciudad
	 * @return Una Ciudad
	 * @throws Exception **/

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Ciudad> getById(@PathVariable Long id) throws Exception
	{
		Optional<Ciudad> rta=service.getById(id);
		if(rta.isPresent())
		{
			Ciudad pojo=rta.get();
			return new ResponseEntity<Ciudad>(pojo, HttpStatus.OK);
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	/**
	 * Obtiene la lista completa de ciudades.
	 *  curl --location --request GET 'http://localhost:8081/ciudades'
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Ciudad>> getAll() throws Exception
	{
		List<Ciudad> rta=service.findAll();
		return new ResponseEntity<List<Ciudad>>(rta,HttpStatus.OK);
	}
	

	

	
}