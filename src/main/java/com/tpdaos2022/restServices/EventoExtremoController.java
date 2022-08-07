package com.tpdaos2022.restServices;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpdaos2022.entities.Ciudad;
import com.tpdaos2022.entities.Pronostico;
import com.tpdaos2022.entities.Suscripcion;
import com.tpdaos2022.services.EventoExtremoService;


@RestController
@RequestMapping("/eventoextremo")
@Validated
public class EventoExtremoController {
	
	@Autowired
	private EventoExtremoService servicioEx;
	

	//PUT para recibir una notificacion de evento extremo.
	//envia un mail alertando de un pronostico extremo
			/**
			 * 			curl --location --request PUT 'http://localhost:8081/eventoextremo' 
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
			 * @throws Exception **/
	@PutMapping
	public ResponseEntity<Object>  actualizarEventoExtremo(@Valid @RequestBody PronosticoDTO pr) throws Exception
	{
		Pronostico pro = pr.toPojo();			
		Boolean esExtremo = pro.getEventoExtremo();
		
		if (esExtremo == true) {
		//obtengo la ciudad para buscar sus suscriptos
			Long idCiudad = pr.getIdCiudad();
			Optional<Ciudad> ciudad = servicioEx.obtenerCiudad(idCiudad);
			
			List<Suscripcion> susc = servicioEx.buscarSuscriptos(ciudad);
			
			//prepara mails para enviar
			//prepara el mensaje a enviar
			String cuerpoMensaje = "Alerta Evento Extremo! " + pr.getDescripcionPronostico() + ". " + "Con Fecha: " + pr.getFechaPronostico() + ". LLuvia prevista:  " + pr.getLluviaPrevista() + ". Porcentaje LLuvia: " + pr.getPorcentajeLluvia();
			
			servicioEx.prepararMails(susc, cuerpoMensaje);

			 return null;
		} else {
			
			System.out.println("El pronóstico ingresado no es un evento extremo");
			return null;
		}
		}

	}
	
	


