package com.AppRH.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.AppRH.AppRH.Models.Funcionario;

@Repository
public interface FuncionarioRepository  extends CrudRepository <Funcionario,String>{

    Funcionario findById(long id);

    //busca
    Funcionario findByNome(String nome);
}
