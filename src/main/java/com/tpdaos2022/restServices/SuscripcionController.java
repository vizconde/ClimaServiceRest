package com.tpdaos2022.restServices;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tpdaos2022.entities.Ciudad;
import com.tpdaos2022.entities.Suscripcion;
import com.tpdaos2022.services.CiudadService;
import com.tpdaos2022.services.SuscripcionService;
import com.tpdaos2022.exceptions.Excepcion;



@RestController
@RequestMapping("/suscripcion")
@Validated
public class SuscripcionController {
	
	@Autowired
	private CiudadService ciudadService;
	
	@Autowired
	private SuscripcionService serviceSuscripcion;
	
	//ESTABLECE - CREA UNA NUEVA SUSCRIPCION
			/**
			 * Inserta una nueva suscripcion en la base de datos
			 * 			curl --location --request POST 'http://localhost:8081/suscripcion' 
			 *			--header 'Accept: application/json' 
			 * 			--header 'Content-Type: application/json' 
			 *			--data-raw '{
			 *			    "email": "jordan_albarracin@hotmail.com" ,
			 *			    "idCiudad": 2
			 *	
			 *			}'
			 * @param email
			 * @param idCiudad
			 * @return
			 * @throws Exception 
			 */
			@PostMapping
			public ResponseEntity<Object> guardarSuscripcion( @Valid @RequestBody SuscripcionDTO form, BindingResult result) throws Exception
			{				
				
				if(result.hasErrors())
				{
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST/*, this.formatearError(result)*/);
				}
				
				

					Suscripcion susc = form.toPojo();
					
					//setea la ciudad a partir del id de ciudad, amtes lo busca
					Optional<Ciudad> c = ciudadService.getById(form.getIdCiudad());
					
					if(c.isPresent()) {
						susc.setCiudad(c.get());}
					else
					
						//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getError("02", "Ciudad Requerida", "La ciudad indicada no se encuentra en la base de datos."));
						return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La ciudad indicada no se encuentra en la base de datos.");
					
					
					
					
						serviceSuscripcion.insert(susc);
						
						/*URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
								.buildAndExpand(susc.getId()).toUri(); //Por convención en REST, se devuelve la  url del recurso recién creado

						return ResponseEntity.created(location).build();//201 (Recurso creado correctamente)*/
						
						return new ResponseEntity<>("result successful result", 
								   HttpStatus.OK);
				
					

			}
			
			
			/**
			 * Borra la suscripcion con el email indicado
			 * 	  curl --location --request DELETE 'http://localhost:8081/suscripcion/jordan_albarracin@hotmail.com'
			 * 			--header 'Accept: application/json' 
			 * 			--header 'Content-Type: application/json' 
			 * @param email
			 * @return ok en caso de borrar exitosamente la suscripcion, error en otro caso
			 * @throws Excepcion 
			 */
			@DeleteMapping("/{email}")
			public ResponseEntity<String> eliminarSuscripcion (@PathVariable String email) throws Excepcion
			{
				
				
			
				serviceSuscripcion.delete(email);
				
				return ResponseEntity.ok().build();
				
				
			}
			
			
			
			/**
			 * Devuelve una lista de suscriptores (emails) pasando como parametro la ciudad. 
			 * Ej1 curl --location --request GET 'http://localhost:8081/suscripcion?idCiudad='
			 *	--header 'Accept: application/json' 
			 * 	--header 'Content-Type: application/json' 
			 * @param idCiudad
			 * @return
			 * @throws Excepcion 
			 */
			//@Operation(summary = "Permite filtrar suscriptores. ")
			@GetMapping( produces = { MediaType.APPLICATION_JSON_VALUE})
			public List<SuscripcionResponseDTO> filtrarSuscriptores(@RequestParam(name = "idCiudad",required = true) Long idCiudad ) throws Excepcion {
				
				//primero busco la ciudad a partir del id proporcionado por el parametro
				Optional<Ciudad> c = ciudadService.getById(idCiudad);
				
				
				List<Suscripcion> suscriptores = serviceSuscripcion.filtrar(c);
				List<SuscripcionResponseDTO> dtos = new ArrayList<SuscripcionResponseDTO>();
				
				for (Suscripcion pojo : suscriptores) {
					
			        dtos.add(buildResponse(pojo));
				}
				return dtos;

			}
			
			//CREA LOS LINKS PARA DEVOLVER EN EL JSON---------------------------------------------------------
			private SuscripcionResponseDTO buildResponse(Suscripcion pojo)
			{
				try {
					
					
					SuscripcionResponseDTO dto = new SuscripcionResponseDTO(pojo);
					//Self link
					Link selfLink = WebMvcLinkBuilder.linkTo(PronosticoController.class).withSelfRel();
					dto.add(selfLink);
					
					
					return dto;
					
					
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			}
			

}
