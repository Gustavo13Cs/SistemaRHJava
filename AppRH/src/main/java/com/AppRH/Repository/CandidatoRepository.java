package com.AppRH.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.AppRH.AppRH.Models.Candidato;
import com.AppRH.AppRH.Models.Vaga;

@Repository
public interface CandidatoRepository extends CrudRepository<Candidato, Long> {
	
	Iterable<Candidato>findByVaga(Vaga vaga);
	
	Candidato findByRg(String rg);
	
	Candidato findById(long id);
	
	//List<Candidato>findByNomeCandidato(String nomeCandidato);

	
	// para a busca
	@Query(value = "select u from Candidato u where u.nomeCandidato like %?1%")
	List<Candidato>findByNomescandidatos(String nomeCandidato);
}