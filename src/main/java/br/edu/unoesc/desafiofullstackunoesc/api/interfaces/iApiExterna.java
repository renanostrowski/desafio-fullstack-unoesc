package br.edu.unoesc.desafiofullstackunoesc.api.interfaces;

import br.edu.unoesc.desafiofullstackunoesc.model.dto.AuxilioEmergencialDTO;
import br.edu.unoesc.desafiofullstackunoesc.model.form.AuxilioEmergencialForm;

import java.util.List;

public interface iApiExterna {
    List<AuxilioEmergencialForm> callApi(String anoMes, String codigoIBGE);
}
