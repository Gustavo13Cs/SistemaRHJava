package com.AppRH.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.AppRH.AppRH.Models.Dependente;
import com.AppRH.AppRH.Models.Funcionario;

@Repository
public interface DependenteRepository extends CrudRepository<Dependente, Long> {

    Iterable<Dependente> findByFuncionario(Funcionario funcionario);

    //pensando no metodo delete
    Dependente findByCpf(String cpf);
    Dependente findById(long id);

    //criando para implementar 
    List<Dependente> findByNome(String nome);

	// para a busca
	@Query(value = "select u from Dependente u where u.nome like %?1%")
	List<Dependente>findByNomesDependentes(String nome);
}
