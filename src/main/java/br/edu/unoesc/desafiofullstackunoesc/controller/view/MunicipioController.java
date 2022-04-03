package br.edu.unoesc.desafiofullstackunoesc.controller.view;

import br.edu.unoesc.desafiofullstackunoesc.exceptions.CustomErrorType;
import br.edu.unoesc.desafiofullstackunoesc.model.dto.MunicipioDTO;
import br.edu.unoesc.desafiofullstackunoesc.model.dto.UnidadeFederativaDTO;
import br.edu.unoesc.desafiofullstackunoesc.model.dto.UsuarioDTO;
import br.edu.unoesc.desafiofullstackunoesc.model.entity.Municipio;
import br.edu.unoesc.desafiofullstackunoesc.model.form.MunicipioForm;
import br.edu.unoesc.desafiofullstackunoesc.services.municipio.interfaces.iMunicipioService;
import br.edu.unoesc.desafiofullstackunoesc.services.unidadeFederativa.interfaces.iUnidadeFedetativaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MunicipioController {


    private iMunicipioService municipioService;
    private iUnidadeFedetativaService unidadeFederativaService;

    public MunicipioController(iMunicipioService municipioService, iUnidadeFedetativaService unidadeFederativaService){
        this.municipioService = municipioService;
        this.unidadeFederativaService = unidadeFederativaService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "municipio/novo")
    public String form(Model model){
        model.addAttribute("form", new MunicipioForm());
        List<MunicipioDTO> municipios = municipioService.listarMunicipios();
        model.addAttribute("municipios", municipios);
        List<UnidadeFederativaDTO> ufDtos = unidadeFederativaService.findAll();
        model.addAttribute("ufs", ufDtos);
        return "/cadastros/municipio/form";
    }

    @PostMapping(value = "/municipio/salvar")
    public String salvarMunicipio(MunicipioForm municipioForm, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("error", result.getAllErrors());
            model.addAttribute("form", new MunicipioForm());
            List<MunicipioDTO> municipios = municipioService.listarMunicipios();
            model.addAttribute("municipios", municipios);
            List<UnidadeFederativaDTO> ufDtos = unidadeFederativaService.findAll();
            model.addAttribute("ufs", ufDtos);
            return "/cadastros/municipio/form";
        }
        try{
            municipioService.salvarMunicipio(municipioForm);
        } catch (CustomErrorType ex){
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("form", new MunicipioForm());
            List<MunicipioDTO> municipios = municipioService.listarMunicipios();
            model.addAttribute("municipios", municipios);
            List<UnidadeFederativaDTO> ufDtos = unidadeFederativaService.findAll();
            model.addAttribute("ufs", ufDtos);
            return "/cadastros/municipio/form";
        }
        return "/cadastros/municipio/form";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/municipio/alterar/{codigoibge}")
    public String alterarUsuario(@PathVariable("codigoibge") long codigoIBGE, Model model) {
        MunicipioDTO municipioDTO = municipioService.findByCodigoIBGE(codigoIBGE);

        MunicipioForm municipioForm = new MunicipioForm();
        municipioForm.setSigla(municipioDTO.getUnidadeFederativaDTO().getSigla());
        municipioForm.setCodigoIBGE(municipioDTO.getCodigoIBGE());
        municipioForm.setNomeIBGE(municipioDTO.getNomeIBGE());
        municipioForm.setCodigoRegiao(municipioDTO.getCodigoRegiao());
        municipioForm.setPais(municipioDTO.getPais());

        model.addAttribute("form", municipioForm);
        List<MunicipioDTO> municipios = municipioService.listarMunicipios();
        model.addAttribute("municipios", municipios);
        List<UnidadeFederativaDTO> ufDtos = unidadeFederativaService.findAll();
        model.addAttribute("ufs", ufDtos);
        return "/cadastros/municipio/form";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/municipio/deletar/{codigoibge}")
    public String deletarUsuario(@PathVariable("codigoibge") long codigoIBGE, Model model) {
        MunicipioDTO municipioDTO = municipioService.findByCodigoIBGE(codigoIBGE);
        try{
            if(municipioDTO != null) municipioService.deletarMunicipio(codigoIBGE);
        }catch (Exception ex){
            model.addAttribute("error", "Erro ao deletar Usuário!");
        }


        model.addAttribute("form", new MunicipioForm());
        List<MunicipioDTO> municipios = municipioService.listarMunicipios();
        model.addAttribute("municipios", municipios);
        List<UnidadeFederativaDTO> ufDtos = unidadeFederativaService.findAll();
        model.addAttribute("ufs", ufDtos);

        return "/cadastros/municipio/form";
    }
}
