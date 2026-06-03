package com.adopcion.service;

import com.adopcion.model.Usuario;
import java.util.List;

public interface UsuarioService {
    List<Usuario> findAll();
    Usuario findById(Integer id);
    Usuario create(Usuario usuario);
    Usuario update(Integer id, Usuario usuario);
    void delete(Integer id);
}
