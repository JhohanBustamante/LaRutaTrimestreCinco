package com.laruta.api.servicio;


import com.laruta.api.model.Usuario;
import com.laruta.api.repositories.IUserRep;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioServicio {
    private final IUserRep iUserRep;

    public UsuarioServicio(IUserRep usuarioRepository) {
        this.iUserRep = usuarioRepository;
    }

    public List<Usuario> listarTodos() {
        return iUserRep.findAll();
    }

    public void guardar(Usuario usuario) {
        iUserRep.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return iUserRep.findById(id).orElse(null);

    }

    public void eliminar(Long id) {
        iUserRep.deleteById(id);
    }
}