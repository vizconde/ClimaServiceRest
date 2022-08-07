package com.tpdaos2022.restServices;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tpdaos2022.exceptions.Excepcion;
import com.tpdaos2022.services.MailService;

@RestController
@RequestMapping("/mail")
public class MailController {

	@Autowired
	private MailService emailService;
	
	//ACTUALIZA UN EMAIL CON CUERPO DE MENSAJE + DESTINATARIO---------------------------------------------------------------------------------
		/**
		 * Modifica el registro de mail a quien se enviara un correp:
		 * 			curl --location --request PUT 'http://localhost:8081/mail?email=&&cuerpoMensaje=' 
		 *			--header 'Accept: application/json' 
		 * 			--header 'Content-Type: application/json'
		 * @param email
		 * @param cuerpoMensaje
		 * @return 
		 * @throws Excepcion
		 */
		@PutMapping
		public ResponseEntity<Object>  mandarMail(@Valid @RequestParam(name = "email",required = true) String mail, @RequestParam(name = "cuerpoMensaje",required = true) String cuerpoMensaje ) throws Excepcion
		{
			
				
				
				// invoco al servicio y paso los parametros
				emailService.mandarMail(mail, cuerpoMensaje);
				
				return null;
				
			

		}
}
