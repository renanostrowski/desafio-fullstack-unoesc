package br.edu.unoesc.desafiofullstackunoesc.services.unidadeFederativa.interfaces;

import br.edu.unoesc.desafiofullstackunoesc.model.dto.MunicipioDTO;
import br.edu.unoesc.desafiofullstackunoesc.model.dto.UnidadeFederativaDTO;
import br.edu.unoesc.desafiofullstackunoesc.model.entity.UnidadeFederativa;
import br.edu.unoesc.desafiofullstackunoesc.model.form.MunicipioForm;
import org.springframework.stereotype.Service;

import java.util.List;

public interface iUnidadeFedetativaService {
    UnidadeFederativaDTO findById(long id);
    List<UnidadeFederativaDTO> findAll();
    List<UnidadeFederativaDTO> ufCidades(List<MunicipioDTO> municipioDTOS);
}
