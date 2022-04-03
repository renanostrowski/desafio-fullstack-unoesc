package com.springframe.springframe.services.csv.interfaces;

import com.springframe.springframe.model.entity.AuxilioEmergencial;
import com.springframe.springframe.model.form.AuxilioEmergencialForm;

import java.util.List;

public interface iExportCSV {
    String gerarCSV(List<AuxilioEmergencialForm> auxilios);
}
