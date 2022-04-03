package com.springframe.springframe.reposository;

import com.springframe.springframe.model.entity.UnidadeFederativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface iUnidadeFederativaRepository extends JpaRepository<UnidadeFederativa, Long> {
    UnidadeFederativa findById(long id);
    @Query("select uf from UnidadeFederativa uf where uf.sigla = :sigla")
    Optional<UnidadeFederativa> buscarPorSigla(String sigla);
}
