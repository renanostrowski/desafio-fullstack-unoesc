package com.springframe.springframe.services.municipio.implementation;

import com.springframe.springframe.exceptions.CustomErrorType;
import com.springframe.springframe.model.dto.MunicipioDTO;
import com.springframe.springframe.model.entity.Municipio;
import com.springframe.springframe.model.entity.UnidadeFederativa;
import com.springframe.springframe.model.form.MunicipioForm;
import com.springframe.springframe.reposository.iMunicipioRepository;
import com.springframe.springframe.reposository.iUnidadeFederativaRepository;
import com.springframe.springframe.services.municipio.interfaces.iMunicipioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MunicipioServiceImpl implements iMunicipioService {

    private iUnidadeFederativaRepository unidadeFederativaRepository;
    private iMunicipioRepository municipioRepository;

    public MunicipioServiceImpl(iMunicipioRepository municipioRepository, iUnidadeFederativaRepository unidadeFederativaRepository){
        this.municipioRepository = municipioRepository;
        this.unidadeFederativaRepository = unidadeFederativaRepository;
    }

    public MunicipioDTO findByCodigoIBGE(long codigo){
        Municipio municipio = municipioRepository.findByCodigoIBGE(codigo);
        if(municipio == null) throw new CustomErrorType("Munícipio informado não pode ser encontrado!");
        return new MunicipioDTO(municipio);
    }

    public MunicipioDTO findByNome(String nome){
        Municipio municipio = municipioRepository.findByNome(nome);
        if(municipio == null) throw new CustomErrorType("Munícipio informado não pode ser encontrado!");
        return new MunicipioDTO(municipio);
    }

    public List<MunicipioDTO> listarMunicipios(){
        List<Municipio> municipios = municipioRepository.findAll();
        return municipios.stream().map(MunicipioDTO::new).collect(Collectors.toList());
    }

    public MunicipioDTO salvarMunicipio(MunicipioForm municipioForm){
        Municipio municipio = null;
        // Verifica UF
        Optional<UnidadeFederativa> uf = unidadeFederativaRepository.buscarPorSigla(municipioForm.getSigla());
        if(!uf.isPresent()) throw new CustomErrorType("Unidade federativa (UF) não informada!");

        municipio = municipioRepository.findByCodigoIBGE(municipioForm.getCodigoIBGE());
        if(municipio != null) throw new CustomErrorType("Munícipio já existe na base de dados!");

        municipio = municipioRepository.findByNome(municipioForm.getNomeIBGE());
        if(municipio != null) throw new CustomErrorType("Munícipio já existe na base de dados!");

        municipio = municipioForm.convert();
        municipio.setUnidadeFederativa(uf.get());
        municipio = municipioRepository.save(municipio);

        return new MunicipioDTO(municipio);
    }

    public void deletarMunicipio(long codigoIBGE){
        Municipio municipio = municipioRepository.findByCodigoIBGE(codigoIBGE);
        if(municipio == null) throw new CustomErrorType("Munícipio informado não pode ser encontrado!");
        municipioRepository.delete(municipio);
    }
}
