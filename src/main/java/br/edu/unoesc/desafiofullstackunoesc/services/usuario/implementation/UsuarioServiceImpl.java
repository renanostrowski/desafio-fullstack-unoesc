package br.edu.unoesc.desafiofullstackunoesc.services.usuario.implementation;

import br.edu.unoesc.desafiofullstackunoesc.exceptions.CustomErrorType;
import br.edu.unoesc.desafiofullstackunoesc.model.dto.UsuarioDTO;
import br.edu.unoesc.desafiofullstackunoesc.model.entity.Municipio;
import br.edu.unoesc.desafiofullstackunoesc.model.entity.Usuario;
import br.edu.unoesc.desafiofullstackunoesc.model.form.UsuarioForm;
import br.edu.unoesc.desafiofullstackunoesc.reposository.iMunicipioRepository;
import br.edu.unoesc.desafiofullstackunoesc.reposository.iUsuarioRepository;
import br.edu.unoesc.desafiofullstackunoesc.services.municipio.interfaces.iMunicipioService;
import br.edu.unoesc.desafiofullstackunoesc.services.usuario.interfaces.iUsuarioService;
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
