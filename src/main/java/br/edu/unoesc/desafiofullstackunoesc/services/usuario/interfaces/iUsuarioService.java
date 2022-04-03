package br.edu.unoesc.desafiofullstackunoesc.services.usuario.interfaces;

import br.edu.unoesc.desafiofullstackunoesc.model.dto.UsuarioDTO;
import br.edu.unoesc.desafiofullstackunoesc.model.entity.Usuario;
import br.edu.unoesc.desafiofullstackunoesc.model.form.UsuarioForm;

import java.util.List;

public interface iUsuarioService {
    UsuarioDTO salvarUsuario(UsuarioForm usuarioForm);
    void deletarUsuario(String email);
    List<UsuarioDTO> listarUsuarios();
    UsuarioDTO buscaUsuarioEmail(String email);
    UsuarioForm alterarUsuario(String emaul);
}
