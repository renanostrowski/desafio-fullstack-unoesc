package com.springframe.springframe.services.usuario.implementation;

import com.springframe.springframe.exceptions.CustomErrorType;
import com.springframe.springframe.model.dto.UsuarioDTO;
import com.springframe.springframe.model.entity.Municipio;
import com.springframe.springframe.model.entity.Usuario;
import com.springframe.springframe.model.form.UsuarioForm;
import com.springframe.springframe.reposository.iMunicipioRepository;
import com.springframe.springframe.reposository.iUsuarioRepository;
import com.springframe.springframe.services.municipio.interfaces.iMunicipioService;
import com.springframe.springframe.services.usuario.interfaces.iUsuarioService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("usuarioServiceImpl")
public class UsuarioServiceImpl implements iUsuarioService {

    private iUsuarioRepository usuarioReposity;
    private iMunicipioRepository municipioRepository;


    public UsuarioServiceImpl(iUsuarioRepository usuarioReposity, iMunicipioRepository municipioRepository){
        this.usuarioReposity = usuarioReposity;
        this.municipioRepository = municipioRepository;
    }

    public UsuarioDTO salvarUsuario(UsuarioForm usuarioForm) {
        Usuario usuario = usuarioReposity.buscarPorEmail(usuarioForm.getEmail());
        if(usuario == null) {
            usuario = usuarioForm.convert();
            if(usuarioForm.getCodigoIBGE() == null) throw new CustomErrorType("Munícipio não informado!");
            Municipio municipio = municipioRepository.findByCodigoIBGE(usuarioForm.getCodigoIBGE());
            usuario.setMunicipio(municipio);
            usuario = usuarioReposity.save(usuario);
            return new UsuarioDTO(usuario);
        } else {
            throw  new CustomErrorType("E-mail já cadastrado para outro usuário!");
        }

    }

    public void deletarUsuario(String email){
        Usuario usuario = usuarioReposity.buscarPorEmail(email);
        if(usuario != null) {
            usuarioReposity.delete(usuario);
        } else {
            throw  new CustomErrorType("Usuário que deseja excluir não existe!");
        }
    }

    public List<UsuarioDTO> listarUsuarios(){
        List<Usuario> usuarios = usuarioReposity.findAll();
        return usuarios.stream().map(UsuarioDTO::new).collect(Collectors.toList());
    }

    public UsuarioDTO buscaUsuarioEmail(String email) {
        Usuario usuario = usuarioReposity.buscarPorEmail(email);
        if(usuario == null) throw new CustomErrorType("Usuário Não encontrado!");
        return new UsuarioDTO(usuario);
    }

    public UsuarioForm alterarUsuario(String email) {
        Usuario usuario = usuarioReposity.buscarPorEmail(email);
        if(usuario == null) throw new CustomErrorType("Usuário Não encontrado!");
        return new UsuarioForm(usuario);
    }
}
