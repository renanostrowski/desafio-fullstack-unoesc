package com.springframe.springframe.api.interfaces;

import com.springframe.springframe.model.dto.AuxilioEmergencialDTO;
import com.springframe.springframe.model.form.AuxilioEmergencialForm;

import java.util.List;

public interface iApiExterna {
    List<AuxilioEmergencialForm> callApi(String anoMes, String codigoIBGE);
}
