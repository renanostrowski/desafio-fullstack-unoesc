package br.edu.unoesc.desafiofullstackunoesc.security;

import br.edu.unoesc.desafiofullstackunoesc.model.entity.Usuario;
import br.edu.unoesc.desafiofullstackunoesc.model.entity.Usuario;
import br.edu.unoesc.desafiofullstackunoesc.reposository.iUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    iUsuarioRepository usuarioRepository;
    public UserDetailsServiceImpl(iUsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.buscarPorEmail(username);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(usuario != null){

        }
        return usuario;
    }
}
