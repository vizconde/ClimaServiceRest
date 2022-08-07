package com.tpdaos2022.repo;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tpdaos2022.entities.Ciudad;
import com.tpdaos2022.entities.Suscripcion;



public interface SuscripcionRepo extends JpaRepository<Suscripcion, Long>{

	public Optional<Suscripcion> findByEmail(String email);
	
	@Transactional
	@Modifying
    @Query("delete from Suscripcion s where s.email = ?1")
    void deleteSuscripcionByEmail(String email);
	
	
	
	public Optional<Suscripcion> findByCiudadAndEmail(Optional <Ciudad> ciudad, String email);
	
	public Optional<Suscripcion> findByCiudadAndEmail(Ciudad ciudad, String email);
	
	public List<Suscripcion> findByCiudad(Optional<Ciudad> ciudad);
	
	
	
}
