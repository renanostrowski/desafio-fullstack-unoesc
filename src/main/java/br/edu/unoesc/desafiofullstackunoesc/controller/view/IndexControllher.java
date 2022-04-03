package br.edu.unoesc.desafiofullstackunoesc.controller.view;

import br.edu.unoesc.desafiofullstackunoesc.api.interfaces.iApiExterna;
import br.edu.unoesc.desafiofullstackunoesc.model.dto.AuxilioEmergencialDTO;
import br.edu.unoesc.desafiofullstackunoesc.model.dto.MunicipioDTO;
import br.edu.unoesc.desafiofullstackunoesc.model.form.AuxilioEmergencialForm;
import br.edu.unoesc.desafiofullstackunoesc.model.form.ConsultaAuxilioForm;
import br.edu.unoesc.desafiofullstackunoesc.model.form.UsuarioForm;
import br.edu.unoesc.desafiofullstackunoesc.security.UserDetailsServiceImpl;
import br.edu.unoesc.desafiofullstackunoesc.services.auxilioEmergencial.interfaces.iAuxlioEmergencialService;
import br.edu.unoesc.desafiofullstackunoesc.services.csv.interfaces.iExportCSV;
import br.edu.unoesc.desafiofullstackunoesc.services.municipio.interfaces.iMunicipioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class IndexControllher {

    private UserDetailsServiceImpl userDetailsService;
    private iAuxlioEmergencialService auxlioEmergencialService;
    private iApiExterna apiExterna;
    private iExportCSV exportCSV;

    public IndexControllher(UserDetailsServiceImpl userDetailsService, iAuxlioEmergencialService auxlioEmergencialService,
                            iApiExterna apiExterna, iExportCSV exportCSV){
        this.userDetailsService = userDetailsService;
        this.auxlioEmergencialService = auxlioEmergencialService;
        this.apiExterna = apiExterna;
        this.exportCSV = exportCSV;
    }

    @RequestMapping("/")
    public String index(Model model, ConsultaAuxilioForm consulta){
        model.addAttribute("consulta", consulta);
        List<AuxilioEmergencialDTO> auxilios = auxlioEmergencialService.listarAuxilios();
        model.addAttribute("auxilios", auxilios);
        return "/index";
    }

    @PostMapping("/busca")
    public String buscar(Model model, ConsultaAuxilioForm consulta){
        model.addAttribute("consulta", consulta);
        String anoMes = String.valueOf(consulta.getAno()) + String.valueOf(consulta.getMes());
        try{
            List<AuxilioEmergencialForm> auxilios = apiExterna.callApi(anoMes, consulta.getCodigoIBGE());
            List<AuxilioEmergencialForm> list = auxlioEmergencialService.agruparAuxilio(auxilios);
            model.addAttribute("auxilios", list);
        }catch (Exception e){
            model.addAttribute("error", "Erro ao realizar busca!");
        }
        return "/index";
    }

    @GetMapping("/busca")
    public String redIndex(){
        return "redirect:/";
    }

    @GetMapping("/csv/codigo/{codigo}/ano/{ano}/mes/{mes}")
    public String csv(@PathVariable("codigo") String codigo,
                      @PathVariable("ano") String ano, @PathVariable("mes") String mes, Model model){
        try{
            List<AuxilioEmergencialForm> auxilios = auxlioEmergencialService.homogenizarAuxilio(ano, mes, codigo);
            exportCSV.gerarCSV(auxilios);
        }catch (Exception e){
            model.addAttribute("error", "Erro ao gerar CSV!");
        }
        return "redirect:/";
    }

    @GetMapping("/auxilio/deletar/codigo/{codigo}/ano/{ano}/mes/{mes}")
    public String deletarAuxilio(@PathVariable("codigo") String codigo,
                      @PathVariable("ano") String ano, @PathVariable("mes") String mes, Model model){
        try{
            auxlioEmergencialService.deletarAuxilio(codigo, ano+mes);
        }catch (Exception ex){
            model.addAttribute("error", "Erro ao deletar Consulta");
        }

        return "redirect:/";
    }

    @GetMapping("/auxilio/gravar/codigo/{codigo}/ano/{ano}/mes/{mes}")
    public String gravarAuxilio(@PathVariable("codigo") String codigo,
                                 @PathVariable("ano") String ano, @PathVariable("mes") String mes, Model model){
        try{
            auxlioEmergencialService.gravarAuxilio(ano, mes, codigo);
        }catch (Exception ex) {
            model.addAttribute("error", "Erro ao deletar Consulta");
        }
        return "redirect:/";
    }


    @GetMapping("/auxilio/atualizar/codigo/{codigo}/ano/{ano}/mes/{mes}")
    public String atualizarAuxilio(@PathVariable("codigo") String codigo,
                                @PathVariable("ano") String ano, @PathVariable("mes") String mes, Model model){
        try{
            auxlioEmergencialService.deletarAuxilio(codigo, ano+mes);
            auxlioEmergencialService.gravarAuxilio(ano, mes, codigo);
        }catch (Exception e){
            model.addAttribute("error", "Erro ao atualizar Consulta");
        }

        return "redirect:/";
    }

}
