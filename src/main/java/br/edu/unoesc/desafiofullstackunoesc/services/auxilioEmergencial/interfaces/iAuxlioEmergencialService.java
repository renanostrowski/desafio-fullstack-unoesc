package br.edu.unoesc.desafiofullstackunoesc.services.auxilioEmergencial.interfaces;

import br.edu.unoesc.desafiofullstackunoesc.model.dto.AuxilioEmergencialDTO;
import br.edu.unoesc.desafiofullstackunoesc.model.form.AuxilioEmergencialForm;

import java.util.List;

public interface iAuxlioEmergencialService {
    List<AuxilioEmergencialDTO> listarAuxilios();
    String gravarAuxilio(String ano, String mes, String codigo);
    void deletarAuxilio(String codigo, String anoMes);
    String atualizarAuxilio(String ano, String mes, String codigo);
    List<AuxilioEmergencialForm> homogenizarAuxilio(String ano, String mes, String codigo);
    List<AuxilioEmergencialForm> agruparAuxilio(List<AuxilioEmergencialForm> auxilios);
}
