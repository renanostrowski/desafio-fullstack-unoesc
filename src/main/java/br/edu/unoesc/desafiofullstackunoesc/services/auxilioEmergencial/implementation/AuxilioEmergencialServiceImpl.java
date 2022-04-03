package br.edu.unoesc.desafiofullstackunoesc.services.auxilioEmergencial.implementation;

import br.edu.unoesc.desafiofullstackunoesc.api.interfaces.iApiExterna;
import br.edu.unoesc.desafiofullstackunoesc.model.dto.AuxilioEmergencialDTO;
import br.edu.unoesc.desafiofullstackunoesc.model.entity.AuxilioEmergencial;
import br.edu.unoesc.desafiofullstackunoesc.model.entity.Municipio;
import br.edu.unoesc.desafiofullstackunoesc.model.form.AuxilioEmergencialForm;
import br.edu.unoesc.desafiofullstackunoesc.reposository.iAuxilioEmergencialRepository;
import br.edu.unoesc.desafiofullstackunoesc.reposository.iMunicipioRepository;
import br.edu.unoesc.desafiofullstackunoesc.services.auxilioEmergencial.interfaces.iAuxlioEmergencialService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AuxilioEmergencialServiceImpl implements iAuxlioEmergencialService {

    private iAuxilioEmergencialRepository auxilioEmergencialRepository;
    private iMunicipioRepository municipioRepository;
    private iApiExterna apiExterna;

    public AuxilioEmergencialServiceImpl(iAuxilioEmergencialRepository auxilioEmergencialRepository,
                                         iApiExterna apiExterna, iMunicipioRepository municipioRepository){
        this.auxilioEmergencialRepository = auxilioEmergencialRepository;
        this.apiExterna = apiExterna;
        this.municipioRepository = municipioRepository;
    }

    public List<AuxilioEmergencialDTO> listarAuxilios(){
        List<AuxilioEmergencial> list = auxilioEmergencialRepository.findAll();
        return list.stream().map(AuxilioEmergencialDTO::new).collect(Collectors.toList());
    }

    @Override
    public String gravarAuxilio(String ano, String mes, String codigo) {

        try {
            List<AuxilioEmergencialForm> auxilios = apiExterna.callApi(ano+mes, codigo);
            auxilios = agruparAuxilio(auxilios);
            for(AuxilioEmergencialForm auxilio : auxilios){
                AuxilioEmergencial aux = new AuxilioEmergencial();
                aux.setDataConsulta(auxilio.getDataConsulta());
                aux.setNumeroParcela(auxilio.getNumeroParcela());
                aux.setValorTotal(auxilio.getValor());
                Municipio municipio =  municipioRepository.findByCodigoIBGE(auxilio .getMunicipio().getCodigoIBGE());
                aux.setMunicipio(municipio);
                aux.setAnoMes(ano+mes);

                auxilioEmergencialRepository.save(aux);
            }
        }catch (Exception e) {
            return e.getMessage();
        }


        return "OK";
    }

    @Transactional
    @Override
    public void deletarAuxilio(String codigo, String anoMes) {
        List<AuxilioEmergencial> auxilios = auxilioEmergencialRepository.listarAuxiliosPorCodigo(Long.parseLong(codigo), anoMes);
        for(AuxilioEmergencial auxilio : auxilios){
            auxilioEmergencialRepository.delete(auxilio);
        }
    }

    @Override
    public String atualizarAuxilio(String ano, String mes, String codigo) {
        try{
            deletarAuxilio(codigo, ano+mes);
            gravarAuxilio(ano, mes, codigo);
        }catch (Exception ex) {
            return ex.getMessage();
        }
        return "OK";
    }

    public List<AuxilioEmergencialForm> homogenizarAuxilio(String ano, String mes, String codigo){
        String anoMes = ano + mes;
        List<AuxilioEmergencialForm> auxilios = apiExterna.callApi(codigo, anoMes);
        return agruparAuxilio(auxilios);
    }

    public List<AuxilioEmergencialForm> agruparAuxilio(List<AuxilioEmergencialForm> auxilios){
        ArrayList<AuxilioEmergencialForm> response = new ArrayList<>();
        Map<String, Double> list = auxilios.stream().collect(
                Collectors.groupingBy(AuxilioEmergencialForm::getNumeroParcela, Collectors.summingDouble(a -> a.getValor().doubleValue())));


        Set<String> parcelas = list.keySet();

        for(String parcela : parcelas){
            AuxilioEmergencialForm total = new AuxilioEmergencialForm();
            total.setDataConsulta(new Date());
            total.setMunicipio(auxilios.get(0).getMunicipio());
            total.setNumeroParcela(parcela);
            total.setValor(BigDecimal.valueOf(list.get(parcela)));
            response.add(total);
        }
        Collections.reverse(response);
        return response;
    }
}
