package br.edu.unoesc.desafiofullstackunoesc.controller.view;

import br.edu.unoesc.desafiofullstackunoesc.model.dto.UsuarioDTO;
import br.edu.unoesc.desafiofullstackunoesc.model.form.UsuarioForm;
import br.edu.unoesc.desafiofullstackunoesc.security.UserDetailsServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    UserDetailsServiceImpl userDetailsService;

    public LoginController(UserDetailsServiceImpl userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String form(Model model){
        return "/login/index";
    }
}
