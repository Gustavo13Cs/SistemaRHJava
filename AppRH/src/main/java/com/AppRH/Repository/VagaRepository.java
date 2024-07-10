package com.AppRH.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.AppRH.AppRH.Models.Vaga;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;

public interface VagaRepository extends CrudRepository<Vaga, String> {
    Vaga findByCodigo(long codigo);
	List<Vaga> findByNome(String nome);
}
