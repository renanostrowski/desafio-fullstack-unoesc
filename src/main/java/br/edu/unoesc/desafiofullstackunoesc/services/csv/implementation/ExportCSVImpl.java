package com.springframe.springframe.services.csv.implementation;

import com.opencsv.CSVWriter;
import com.springframe.springframe.model.dto.AuxilioEmergencialDTO;
import com.springframe.springframe.model.entity.AuxilioEmergencial;
import com.springframe.springframe.model.form.AuxilioEmergencialForm;
import com.springframe.springframe.services.csv.interfaces.iExportCSV;
import org.springframework.stereotype.Service;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExportCSVImpl implements iExportCSV {

    @Override
    public String gerarCSV(List<AuxilioEmergencialForm> auxilios) {
        String[] cabecalho = {"dataCaonsulta", "codigoIBGE", "nomeIBGE", "numeroParcela", "valorTotal"};

        List<String[]> linhas = new ArrayList<>();

        for(AuxilioEmergencialForm auxilio : auxilios){
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String dataFormatada = dateFormat.format(auxilio.getDataConsulta());

            String codigoIBGE = auxilio.getMunicipio().getCodigoIBGE().toString();
            String nomeIBGE = auxilio.getMunicipio().getNomeIBGE();
            String numeroParcela = auxilio.getNumeroParcela();
            String valorTotal = String.valueOf(auxilio.getValor());

            linhas.add(new String[]{dataFormatada, codigoIBGE, nomeIBGE, numeroParcela, valorTotal});
        }

        try {
            Writer writer = Files.newBufferedWriter(Paths.get("auxilios.csv"));
            CSVWriter csvWriter = new CSVWriter(writer);

            csvWriter.writeNext(cabecalho);
            csvWriter.writeAll(linhas);

            csvWriter.flush();
            writer.close();
        }catch (Exception ex){
            return "Erro ao gerar CSV!";
        }
        return "CSV gerado com sucesso!";
    }
}
