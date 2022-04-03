package com.springframe.springframe.services.municipio.interfaces;

import com.springframe.springframe.model.dto.MunicipioDTO;
import com.springframe.springframe.model.entity.Municipio;
import com.springframe.springframe.model.form.MunicipioForm;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

public interface iMunicipioService {
    MunicipioDTO findByCodigoIBGE(long codigo);
    MunicipioDTO findByNome(String nome);
    MunicipioDTO salvarMunicipio(MunicipioForm municipioForm);
    void deletarMunicipio(long codigoIBGE);
    List<MunicipioDTO> listarMunicipios();
}
