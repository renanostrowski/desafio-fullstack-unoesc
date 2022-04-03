package com.springframe.springframe.api.implementation;

import com.springframe.springframe.api.interfaces.iApiExterna;
import com.springframe.springframe.model.dto.AuxilioEmergencialDTO;
import com.springframe.springframe.model.entity.AuxilioEmergencial;
import com.springframe.springframe.model.form.AuxilioEmergencialForm;
import com.springframe.springframe.reposository.iAuxilioEmergencialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiWebClient implements iApiExterna {

    private final WebClient localApiClient;
    private iAuxilioEmergencialRepository auxilioEmergencialRepository;
    public ApiWebClient(@NotNull WebClient.Builder webClientBuilder, iAuxilioEmergencialRepository auxilioEmergencialRepository) {
        this.localApiClient = webClientBuilder.build();
        this.auxilioEmergencialRepository = auxilioEmergencialRepository;
    }

    public List<AuxilioEmergencialForm> callApi(String anoMes, String codigoIBGE){
        List<AuxilioEmergencial> list = auxilioEmergencialRepository.listarAuxiliosPorCodigo(Long.parseLong(codigoIBGE), anoMes);

        if(list.size() == 0) {
            Mono<List<AuxilioEmergencialForm>> response = localApiClient
                    .get()
                    .uri("https://api.portaldatransparencia.gov.br/api-de-dados/auxilio-emergencial-beneficiario-por-municipio?codigoIbge="+codigoIBGE+"&mesAno="+anoMes+"&pagina=1")
                    .header("chave-api-dados", "6631067809ec42c4a5a3ba0274bb750d")
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<AuxilioEmergencialForm>>() {
                    });

            return response.block();
        } else {
            return list.stream().map(AuxilioEmergencialForm::new).collect(Collectors.toList());
        }
    }

}
