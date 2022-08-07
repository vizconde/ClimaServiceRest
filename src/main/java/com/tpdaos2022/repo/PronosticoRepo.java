package com.tpdaos2022.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tpdaos2022.entities.Ciudad;
import com.tpdaos2022.entities.Pronostico;

public interface PronosticoRepo extends JpaRepository<Pronostico, Long> {
	
	public Optional<Pronostico> findByCiudadAndFechaPronostico(Optional<Ciudad> c, LocalDate fechaPronostico);
	
	public Optional<Pronostico> findByCiudadAndFechaPronostico(Ciudad ciudad, LocalDate fechaPronostico);
	
	
	
	//public List<Pronostico> findByRangoFechas(LocalDate fecha1, LocalDate fecha2, Optional<Ciudad> ciudad);
	public List<Pronostico> findByfechaPronosticoBetween(LocalDate fecha1, LocalDate fecha2);
	
	@Query(value= "select * from pronostico p where p.fecha_pronostico between :fecha1 and :fecha2 and p.ciudad_id = :ciudad_id",  nativeQuery=true)
	public List<Pronostico> filtrarExtCiudad(LocalDate fecha1, LocalDate fecha2, Long ciudad_id );
	
	
	//@Query("SELECT pr FROM Pronostico pr WHERE pr.fecha_pronostico BETWEEN :fecha1 AND :fecha2")
	//blic List<Pronostico> bucarPronosticosExtRango(LocalDate fecha1, LocalDate fecha2);
	
	

}
