package com.AppRH.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.AppRH.AppRH.Models.Vaga;

@Repository
public interface VagaRepository extends CrudRepository<Vaga, String> {
	Vaga findByCodigo(long codigo);
	List<Vaga> findByNome(String nome);

	// para a busca
	@Query(value = "select u from Vaga u where u.nome like %?1%")
	List<Vaga>findByVaga(String nome);
}

