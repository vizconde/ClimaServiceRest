package com.tpdaos2022.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tpdaos2022.entities.Ciudad;



public interface CiudadRepo extends JpaRepository<Ciudad, Long>{

	public Optional<Ciudad> findById(Long id);
	
	
	@Query(value= "select * from ciudad c where c.id = :identificador",  nativeQuery=true)
	public Ciudad findByIdentificador(Long identificador);
}
