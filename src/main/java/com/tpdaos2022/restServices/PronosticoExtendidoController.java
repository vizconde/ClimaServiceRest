package com.tpdaos2022.restServices;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.tpdaos2022.entities.Pronostico;
import com.tpdaos2022.exceptions.Excepcion;
import com.tpdaos2022.services.CiudadService;
import com.tpdaos2022.services.PronosticoExtendidoService;


@RestController
@RequestMapping("/pextendido")
@Validated
public class PronosticoExtendidoController {

	
	@Autowired CiudadService serviceCiudad;
	
	@Autowired PronosticoExtendidoService servicePronE;
	
	/**
	 * Permite buscar pronostico extendido
	 * curl --location --request GET 'http://localhost:8081/pextendido?idCiudad=&&fecha='
	 * --header 'Accept: application/json' 
	 *--header 'Content-Type: application/json'
	 * @param idCiudad
	 * @param fecha
	 * @return 
	 * @return pronostico
	 * @throws Exception 
	 */
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE})
	public List<PronosticoResponseDTO> pronExtendido(@RequestParam( name = "idCiudad", required = true) Long idCiudad,
			@RequestParam( name = "fecha", required = true)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaPronostico) throws Excepcion {
		
		
		
		//Busco pronostico extendido
		List<Pronostico> pronosticosEx = servicePronE.pronosticosRango(fechaPronostico, idCiudad);
		
		List<PronosticoResponseDTO> dtos = new ArrayList<PronosticoResponseDTO>();
		
		for (Pronostico pojo : pronosticosEx) {
			
	        dtos.add(buildResponse(pojo));
		}
	
		
		return dtos;
		
		
	
		
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
				
				//Consultar Pronostico
				
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
	
	

}
