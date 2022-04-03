package com.springframe.springframe.services.unidadeFederativa.interfaces;

import com.springframe.springframe.model.dto.MunicipioDTO;
import com.springframe.springframe.model.dto.UnidadeFederativaDTO;
import com.springframe.springframe.model.entity.UnidadeFederativa;
import com.springframe.springframe.model.form.MunicipioForm;
import org.springframework.stereotype.Service;

import java.util.List;

public interface iUnidadeFedetativaService {
    UnidadeFederativaDTO findById(long id);
    List<UnidadeFederativaDTO> findAll();
    List<UnidadeFederativaDTO> ufCidades(List<MunicipioDTO> municipioDTOS);
}
