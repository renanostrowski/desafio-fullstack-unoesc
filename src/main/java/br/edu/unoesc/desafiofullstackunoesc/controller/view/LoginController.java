package com.springframe.springframe.controller.view;

import com.springframe.springframe.model.dto.UsuarioDTO;
import com.springframe.springframe.model.form.UsuarioForm;
import com.springframe.springframe.security.UserDetailsServiceImpl;
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
