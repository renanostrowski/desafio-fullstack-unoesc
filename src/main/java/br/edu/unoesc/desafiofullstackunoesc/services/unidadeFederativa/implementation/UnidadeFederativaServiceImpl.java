package com.springframe.springframe.services.unidadeFederativa.implementation;

import com.springframe.springframe.model.dto.MunicipioDTO;
import com.springframe.springframe.model.dto.UnidadeFederativaDTO;
import com.springframe.springframe.model.entity.UnidadeFederativa;
import com.springframe.springframe.reposository.iUnidadeFederativaRepository;
import com.springframe.springframe.services.unidadeFederativa.interfaces.iUnidadeFedetativaService;
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
