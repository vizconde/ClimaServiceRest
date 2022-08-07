package com.tpdaos2022.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tpdaos2022.entities.Ciudad;
import com.tpdaos2022.entities.Suscripcion;
import com.tpdaos2022.exceptions.Excepcion;
import com.tpdaos2022.restServices.PronosticoDTO;


@Service
public class EventoExtremoServiceImpl implements EventoExtremoService {
	
	@Autowired
	private SuscripcionService serviceSuscripcion;
	
	@Autowired 
	private MailService serviceMail;
	
	@Autowired
	private CiudadService serviceCiudad;
	
	
	
	@Override
	public java.util.List<Suscripcion> buscarSuscriptos(Optional<Ciudad> ciudad) {
		
		return serviceSuscripcion.filtrar(ciudad);
	}
	
	
	
	public String CreaMensajeAlerta(PronosticoDTO form) {
		
		String cuerpoMensaje = "Alerta Evento Extremo! " + form.getDescripcionPronostico() + ". " + "Con Fecha: " + form.getFechaPronostico() + ". LLuvia prevista:  " + form.getLluviaPrevista() + ". Porcentaje LLuvia: " + form.getPorcentajeLluvia();
			
		return cuerpoMensaje;
	}
	
	
	
	@Override
	public Optional<Ciudad> obtenerCiudad(Long idCiudad) {
		Optional<Ciudad> c = serviceCiudad.getById(idCiudad);
		
		return c;
	}
	
	
	@Override
	public void prepararMails(List<Suscripcion> susc, String cuerpoMensaje) {
		
		//iterar la lista de suscriptos mandando el email, invocando al servicio email en cada 
		//iteracion y pasando los parametros mail + mensaje
		 for (int i = 0; i<susc.size(); i++) {
			 Suscripcion suscrito = susc.get(i);
			 String email = suscrito.getEmail();
			
			 
			 //invocar al servicio Mail pasando los parametros
			 try {
				serviceMail.mandarMail(email, cuerpoMensaje);
			} catch (Excepcion e) {
				// TODO Auto-generated catch block
				System.out.println("No se pudo enviar el email");
				e.printStackTrace();
			}
		
		
		
	}
	}
	
	
	@Override
	public void mandarMails(Long idCiudad, String cuerpoMensaje){
		
		//buscar una ciudad por su id
		Optional<Ciudad> c = obtenerCiudad(idCiudad);
		
		//buscar email suscriptos a una ciudad
		List<Suscripcion> suscriptos = buscarSuscriptos(c);
		
		
		//mandar mails
		prepararMails(suscriptos, cuerpoMensaje);
		
		
	}
}


