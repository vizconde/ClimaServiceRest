package com.tpdaos2022.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpdaos2022.entities.Ciudad;
import com.tpdaos2022.entities.ClimaActual;

public interface ClimaActualRepo extends JpaRepository<ClimaActual, Long> {
	
	public Optional<ClimaActual> findByCiudad(Optional<Ciudad> c);
	
	
	
}
