package com.jorgejr.auth.api.services.impl;

import com.jorgejr.auth.api.dtos.UsuarioDto;
import com.jorgejr.auth.api.models.Usuario;
import com.jorgejr.auth.api.repositories.UsuarioRepository;
import com.jorgejr.auth.api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public UsuarioDto salvar(UsuarioDto usuarioDto) {

        Usuario entity = new Usuario(usuarioDto.nome(), usuarioDto.login(), usuarioDto.senha());
        Usuario novoUsuario = usuarioRepository.save(entity);

        return new UsuarioDto(novoUsuario.getNome(), novoUsuario.getLogin(), novoUsuario.getLogin());
    }
}
