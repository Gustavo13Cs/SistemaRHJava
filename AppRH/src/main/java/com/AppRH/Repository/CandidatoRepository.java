package com.AppRH.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.AppRH.AppRH.Models.Candidato;
import com.AppRH.AppRH.Models.Vaga;

@Repository
public interface CandidatoRepository extends CrudRepository<Candidato, String> {

    // muito importante manter a escrita assim , pois e padrão de projeto
    Iterable<Candidato>findByVaga(Vaga vaga);
	
	Candidato findByRg(java.lang.String string);
	
	Candidato findById(long id);
	
	List<Candidato>findByNomeCandidato(String nomeCandidato);
}
