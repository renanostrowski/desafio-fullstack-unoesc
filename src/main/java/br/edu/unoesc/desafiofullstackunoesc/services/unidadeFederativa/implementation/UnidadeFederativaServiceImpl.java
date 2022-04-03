package br.edu.unoesc.desafiofullstackunoesc.services.unidadeFederativa.implementation;

import br.edu.unoesc.desafiofullstackunoesc.model.dto.MunicipioDTO;
import br.edu.unoesc.desafiofullstackunoesc.model.dto.UnidadeFederativaDTO;
import br.edu.unoesc.desafiofullstackunoesc.model.entity.UnidadeFederativa;
import br.edu.unoesc.desafiofullstackunoesc.reposository.iUnidadeFederativaRepository;
import br.edu.unoesc.desafiofullstackunoesc.services.unidadeFederativa.interfaces.iUnidadeFedetativaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UnidadeFederativaServiceImpl implements iUnidadeFedetativaService {

    private iUnidadeFederativaRepository unidadeFederativaRepository;

    public UnidadeFederativaServiceImpl(iUnidadeFederativaRepository unidadeFederativaRepository){
        this.unidadeFederativaRepository = unidadeFederativaRepository;
    }

    public List<UnidadeFederativaDTO> findAll(){
        List<UnidadeFederativa> ufs = unidadeFederativaRepository.findAll();
        return  ufs.stream().map(UnidadeFederativaDTO::new).collect(Collectors.toList());
    }

    public UnidadeFederativaDTO findById(long id){
        return new UnidadeFederativaDTO(unidadeFederativaRepository.findById(id));
    }

    public List<UnidadeFederativaDTO> ufCidades(List<MunicipioDTO> municipioForms){
        List<UnidadeFederativaDTO> unidadeFederativaDTOS = new ArrayList<>();
        for(MunicipioDTO municipio : municipioForms){
            Optional<UnidadeFederativa> uf = unidadeFederativaRepository.buscarPorSigla(municipio.getUnidadeFederativaDTO().getSigla());
            unidadeFederativaDTOS.add(new UnidadeFederativaDTO(uf.get()));
        }

        return unidadeFederativaDTOS;
    }
}
