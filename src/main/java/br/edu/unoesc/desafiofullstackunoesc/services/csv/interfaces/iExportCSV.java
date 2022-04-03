package br.edu.unoesc.desafiofullstackunoesc.services.csv.interfaces;

import br.edu.unoesc.desafiofullstackunoesc.model.entity.AuxilioEmergencial;
import br.edu.unoesc.desafiofullstackunoesc.model.form.AuxilioEmergencialForm;

import java.util.List;

public interface iExportCSV {
    String gerarCSV(List<AuxilioEmergencialForm> auxilios);
}
