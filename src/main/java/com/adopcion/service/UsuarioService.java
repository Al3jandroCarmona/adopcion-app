package com.adopcion.service;

import com.adopcion.model.Usuario;
import java.util.List;
import com.adopcion.dto.PerfilUpdateDTO;

public interface UsuarioService {
    Usuario actualizarPerfil(Integer id, PerfilUpdateDTO dto);
    List<Usuario> findAll();
    Usuario findById(Integer id);
    Usuario create(Usuario usuario);
    Usuario update(Integer id, Usuario usuario);
    void delete(Integer id);
}
