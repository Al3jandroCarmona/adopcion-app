package com.adopcion.service.impl;

import com.adopcion.exception.ResourceNotFoundException;
import com.adopcion.model.Usuario;
import com.adopcion.repository.UsuarioRepository;
import com.adopcion.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findByActivoTrue();
    }

    @Override
    public Usuario findById(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", id));
    }

    @Override
    public Usuario create(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario update(Integer id, Usuario data) {
        Usuario usuario = findById(id);
        usuario.setNombre(data.getNombre());
        usuario.setApellidoPaterno(data.getApellidoPaterno());
        usuario.setApellidoMaterno(data.getApellidoMaterno());
        usuario.setEmail(data.getEmail());
        usuario.setTelefono(data.getTelefono());
        if (data.getPassword() != null && !data.getPassword().isBlank()) {
            usuario.setPassword(data.getPassword());
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public void delete(Integer id) {
        Usuario usuario = findById(id);
        usuario.setActivo(false);
        usuarioRepository.save(usuario);
    }
}
