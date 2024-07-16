package com.AppRH.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.AppRH.AppRH.Models.Dependentes;
import com.AppRH.AppRH.Models.Funcionario;

@Repository
public interface DependentesRepository extends CrudRepository<Dependentes, String> {

    Iterable<Dependentes> findByFuncionario(Funcionario funcionario);

    //pensando no metodo delete
    Dependentes findByCpf(String cpf);
    Dependentes findById(long id);

    //criando para implementar 
    List<Dependentes> findByNome(String nome);
}
