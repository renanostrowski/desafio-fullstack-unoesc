package com.springframe.springframe.controller.view;

import com.springframe.springframe.exceptions.CustomErrorType;
import com.springframe.springframe.model.dto.MunicipioDTO;
import com.springframe.springframe.model.dto.UsuarioDTO;
import com.springframe.springframe.model.form.UnidadeFederativaForm;
import com.springframe.springframe.model.form.UsuarioForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UFController {

    @RequestMapping(method = RequestMethod.GET, value = "/uf/novo")
    public String form(Model model) {
        UnidadeFederativaForm ufForm = new UnidadeFederativaForm();
        model.addAttribute("formUf", ufForm);
        return "/cadastros/uf/form";
    }

    @PostMapping(value = "/uf/salvar")
    public String salvarUsuario(UnidadeFederativaForm ufForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/cadastros/uf/form";
        }
        try {
        } catch (CustomErrorType e) {
            model.addAttribute("formUf", ufForm);
            return "/cadastros/uf/form";
        } catch (Exception e) {
            model.addAttribute("formUf", ufForm);
            return "/cadastros/uf/form";
        }
        return "/cadastros/uf/form";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/uf/alterar/{sigla}")
    public String alterarUsuario(@PathVariable("sigla") String sigla, Model model) {



        return "/cadastros/uf/form";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/uf/deletar/{email}")
    public String deletarUsuario(@PathVariable("sigla") String sigla) {


        return "redirect:/list";
    }
}