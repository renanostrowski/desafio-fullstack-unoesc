package br.edu.unoesc.desafiofullstackunoesc.services.municipio.interfaces;

import br.edu.unoesc.desafiofullstackunoesc.model.dto.MunicipioDTO;
import br.edu.unoesc.desafiofullstackunoesc.model.entity.Municipio;
import br.edu.unoesc.desafiofullstackunoesc.model.form.MunicipioForm;
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
