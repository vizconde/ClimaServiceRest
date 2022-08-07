package com.tpdaos2022.restServices;

import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tpdaos2022.entities.ClimaActual;

import com.tpdaos2022.services.CiudadService;
import com.tpdaos2022.services.ClimaActualService;
import com.tpdaos2022.exceptions.Excepcion;


import com.tpdaos2022.entities.Ciudad;





//capa de presentacion
/**
 * Recurso Clima
 * @author Jordan Albarracin
 *
 */

@RestController
@RequestMapping("/clima")
@Validated
public class ClimaActualController {

	@Autowired
	private ClimaActualService climaService;
	
	@Autowired
	private CiudadService ciudadService;
	
	
	/**
	 * Obtiene una nuevo estado de Clima pasando la ciudad como paramatro
	 *curl --location --request GET 'http://localhost:8081/clima?idCiudad=' 
	 *--header 'Accept: application/json' 
	 *--header 'Content-Type: application/json'
	 * @param idciudad
	 * @return ClimaActual
	 * @throws Exception **/
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ClimaActualResponseDTO> buscarClimaByCiudad(@RequestParam(name = "idCiudad" ,required = true) Long idCiudad) throws Excepcion {
		
		//primero busco la ciudad a partir del id proporcionado por el parametro
		//para esto tengo que llamar al servicio de ciudades y pasarle el id CiudadService
		Optional<Ciudad> c = ciudadService.getById(idCiudad);
		
		//busco el clima actual para la ciudad c
		Optional<ClimaActual> climaActual = climaService.getByCiudad(c);

		
		//si la respuesta está presente construyo el pojo y retorno json
		//sino hago constar del error de ciudad no encontrada
		if(climaActual.isPresent())
		{
			ClimaActual pojo=climaActual.get();
			return new ResponseEntity<ClimaActualResponseDTO>(buildResponse(pojo), HttpStatus.OK);
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		
	}
	
	
	//CREA LOS LINKS PARA DEVOLVER EN EL JSON---------------------------------------------------------
	private ClimaActualResponseDTO buildResponse(ClimaActual pojo)
	{
		try {
			
			
			ClimaActualResponseDTO dto = new ClimaActualResponseDTO(pojo);
			//Self link
			Link selfLink = WebMvcLinkBuilder.linkTo(ClimaActualController.class).withSelfRel();
			dto.add(selfLink);
			
			//Method link
			Link ciudadLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CiudadController.class)
			        .getById(pojo.getCiudad().getId()))
			        .withRel("ciudad");
			dto.add(ciudadLink);
			
			
			//Link para consultar pronostico extendido
			//primero calculo la fecha actual
			LocalDate fechaActual = LocalDate.now();
			Link pronosticoExtendido = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PronosticoExtendidoController.class)
					.pronExtendido(pojo.getCiudad().getId(), fechaActual))
			        .withRel("Pronostico Extendido");
			dto.add(pronosticoExtendido);			
					
					return dto;	
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	//ESTABLECE - CREA UN NUEVO CLIMA-------------------------------------------------------------------
	
	/**
	 * Inserta una nuevo estado de Clima en la base de datos
	 * curl --location --request POST 'http://localhost:8081/clima' 
	 * --header 'Accept: application/json' 
	 * --header 'Content-Type: application/json' 
	 * --data-raw '{
	 * "temperatura": 36,
	 * "estadoClima": "Soleado",
	 * "humedad": 56,
	 * "idCiudad": 1
	 * }'
	 * @param temperatura
	 * @param estadoClima
	 * @param humedad
	 * @param idciudad
	 * @return ClimaActual
	 * @throws Exception 
	 */
	@PostMapping
	public ResponseEntity<Object> guardarClimaActual( @Valid @RequestBody ClimaActualDTO form, BindingResult result) throws Exception
	{
		
		if(result.hasErrors())
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST/*, this.formatearError(result)*/);
		}
		
		

			ClimaActual ca = form.toPojo();
			
			//setea la ciudad a partir del id de ciudad
			Optional<Ciudad> c = ciudadService.getById(form.getIdCiudad());
			
			
			if(c.isPresent()) {
				ca.setCiudad(c.get());
			}
			else {
			
				//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getError("02", "Ciudad Requerida", "La ciudad indicada no se encuentra en la base de datos."));
				return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La ciudad indicada no se encuentra en la base de datos.");
			
			}
			
			
			//busca el clima por ciudad, si existe lo reemplaza
			Optional<ClimaActual> climaactual = climaService.getByCiudad(c);
			
			if(climaactual.isPresent()) {
				//reemplaza el clima actual para esa ciudad
				Long id = climaactual.get().getId();
				ca.setId(id);
				climaService.update(ca);
				URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
						.buildAndExpand(ca.getId()).toUri(); //Por convención en REST, se devuelve la  url del recurso recién creado

				return ResponseEntity.created(location).build();//201 (Recurso creado correctamente)
				
			}
			else {
			
				//si no existe lo crea
				climaService.insert(ca);
				URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
						.buildAndExpand(ca.getId()).toUri(); //Por convención en REST, se devuelve la  url del recurso recién creado

				return ResponseEntity.created(location).build();//201 (Recurso creado correctamente)
				
			
			}
		
	

	}
	
	
	//ACTUALIZA CLIMA ACTUAL---------------------------------------------------------------------------------
	/**
	 * Modifica una clima existente en la base de datos:
	 * 			curl --location --request PUT 'http://localhost:8081/clima?idCiudad' 
	 *			--header 'Accept: application/json' 
	 * 			--header 'Content-Type: application/json' 
	 *			--data-raw '{
	 *			      "temperatura": 36,
	 *			      "estadoClima": "Soleado",
	 *			      "humedad": 56
	 *			}'
	 * @param p Persona a modificar
	 * @return Persona Editada o error en otro caso
	 * @throws Excepcion
	 */
	@PutMapping
	public ResponseEntity<Object>  actualizarClimaActual(@Valid @RequestBody ClimaActualDTO form, @RequestParam(name = "idCiudad",required = true) Long idCiudad ) throws Excepcion
	{
		
		
		//busco clima por id de ciudad
		Optional<Ciudad> c = ciudadService.getById(idCiudad);
		Optional<ClimaActual> climaactual = climaService.getByCiudad(c);
		
		System.out.println(climaactual);
		
		ClimaActual ca = form.toPojo();
		
		if(climaactual.isPresent()) {
			//reemplaza el clima actual para esa ciudad
			Ciudad ciudad = climaactual.get().getCiudad();
			Long id = climaactual.get().getId();
			ca.setId(id);
			ca.setCiudad(ciudad);
			climaService.update(ca);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(ca.getId()).toUri(); //Por convención en REST, se devuelve la  url del recurso recién creado

			return ResponseEntity.created(location).build();//201 (Recurso creado correctamente)
			
			
		} else
			
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encuentra clima actual");
		
		
				
		
		

	}
	}	

