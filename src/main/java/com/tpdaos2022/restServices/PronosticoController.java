package com.tpdaos2022.restServices;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.time.LocalDate;

import com.tpdaos2022.services.PronosticoService;
import com.tpdaos2022.entities.Ciudad;
import com.tpdaos2022.entities.Pronostico;
import com.tpdaos2022.exceptions.Excepcion;
import com.tpdaos2022.services.CiudadService;

@RestController
@RequestMapping("/pronostico")
@Validated
public class PronosticoController {
	
	@Autowired
	private PronosticoService servicePron;
	
	@Autowired
	private CiudadService ciudadService;

	
	
	/**
	 * Permite buscar un pronostico
	 * curl --location --request GET 'http://localhost:8081/pronostico?idCiudad=&&fechaPronostico='
	 * --header 'Accept: application/json' 
	 *--header 'Content-Type: application/json'
	 * @param idCiudad
	 * @param fechaPronostico
	 * @return
	 * @throws Excepcion 
	 */
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<PronosticoResponseDTO> buscarPronByCiudAndFecha(@RequestParam( name = "idCiudad", required = true) Long idCiudad,
			@RequestParam( name = "fechaPronostico", required = true)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaPronostico) throws Excepcion {
		
		//primero busco la ciudad a partir del id proporcionado por el parametro
		//para esto tengo que llamar al servicio de ciudades y pasarle el id en una url al controlador CiudadController
		Optional<Ciudad> c = ciudadService.getById(idCiudad);
		
		//si la respuesta está presente busco el pronostico con la ciudad c y la fechaPronostico
		Optional<Pronostico> pronostico = servicePron.getByCiudadAndFecha( c, fechaPronostico);
		
		if(pronostico.isPresent())
		{
			Pronostico pojo=pronostico.get();
			return new ResponseEntity<PronosticoResponseDTO>(buildResponse(pojo), HttpStatus.OK);
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
	}
	
	
	//CREA LOS LINKS PARA DEVOLVER EN EL JSON---------------------------------------------------------
		private PronosticoResponseDTO buildResponse(Pronostico pojo)
		{
			try {
				
				
				PronosticoResponseDTO dto = new PronosticoResponseDTO(pojo);
				//Self link
				Link selfLink = WebMvcLinkBuilder.linkTo(PronosticoController.class).withSelfRel();
				dto.add(selfLink);
				
				//Method link
				//llamo al servicio de ciudad para armar el link
				Link ciudadLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CiudadController.class)
				        .getById(pojo.getCiudad().getId()))
				        .withRel("ciudad");
				dto.add(ciudadLink);
				return dto;
				
				//link para consultar pronostico extendido Consultar Pronostico
				
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		
		
		//ESTABLECE - CREA UN NUEVO PRONOSTICO
		/**
		 * Inserta una nuevo estado de Clima en la base de datos
		 * 			curl --location --request POST 'http://localhost:8081/pronostico' 
		 *			--header 'Accept: application/json' 
		 * 			--header 'Content-Type: application/json' 
		 *			--data-raw '{
		 *			    "idCiudad": 2,
		 *			    "fechaPronostico": "2022-07-09",
		 *			    "porcentajeLluvia": 32,
		 *			    "lluviaPrevista": 45,
		 *				"descripcionPronostico": "Soleado con vientos del sector oeste",
		 *				"eventoExtremo": "FALSE"
		 *	
		 *			}'
		 * @param idCiudad
		 * @param fechaPronostico
		 * @param porcentajeLluvia
		 * @param lluviaPrevista
		 * @param descripcionPronostico
		 * @param eventoExtremo
		 * @return Pronostico
		 * @throws Exception 
		 */
		@PostMapping
		public ResponseEntity<Object> guardarPronostico( @Valid @RequestBody PronosticoDTO form, BindingResult result) throws Exception
		{
			//obtiene fecha y ciudad para realizar la consulta y ver si ya existe un pronostico con esa ciudad y fecha
			LocalDate fecha = form.getFechaPronostico();
			Optional<Ciudad> c = ciudadService.getById(form.getIdCiudad());
			
			
			//busca el pronostico con la ciudad c y la fechaPronostico
			Optional<Pronostico> pronostico = servicePron.getByCiudadAndFecha( c, fecha);
			
			
			//si el pronostico está presente lo modifico, sino lo guarda
			Pronostico pr = form.toPojo();
			
			
			if(pronostico.isPresent()) {
				//reemplaza el pronostico para esa fecha en esa ciudad
				Long id = pronostico.get().getId();
				Ciudad ciudad = pronostico.get().getCiudad();
				LocalDate fechaPr = pronostico.get().getFechaPronostico();
				pr.setId(id);
				pr.setCiudad(ciudad);
				pr.setFechaPronostico(fechaPr);
				
				servicePron.update(pr);
				
				URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
						.buildAndExpand(pr.getId()).toUri(); //Por convención en REST, se devuelve la  url del recurso recién creado

				return ResponseEntity.created(location).build();//201 (Recurso creado correctamente)
				
			}
			else {
			
				//si no existe lo crea
				//primero obtengo la ciudad a partir del idCiudad
				Ciudad ciudad = ciudadService.getByIdentificador(form.getIdCiudad());
				System.out.println(ciudad);
				pr.setCiudad(ciudad);
				
				
				servicePron.insert(pr);
				URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
						.buildAndExpand(pr.getId()).toUri(); //Por convención en REST, se devuelve la  url del recurso recién creado

				return ResponseEntity.created(location).build();//201 (Recurso creado correctamente)
				
			
			}

		
		}
		
		
		

}
