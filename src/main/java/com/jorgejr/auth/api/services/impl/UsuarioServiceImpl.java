package com.jorgejr.auth.api.services.impl;

import com.jorgejr.auth.api.dtos.UsuarioDto;
import com.jorgejr.auth.api.models.Usuario;
import com.jorgejr.auth.api.repositories.UsuarioRepository;
import com.jorgejr.auth.api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UsuarioDto salvar(UsuarioDto usuarioDto) {

        Usuario usuarioExists = usuarioRepository.findByLogin(usuarioDto.login());

        if(usuarioExists != null) {
            throw new RuntimeException("Usuário já existe");
        }

        var passwordHash = passwordEncoder.encode(usuarioDto.senha());

        Usuario entity = new Usuario(usuarioDto.nome(), usuarioDto.login(), passwordHash);
        Usuario novoUsuario = usuarioRepository.save(entity);

        return new UsuarioDto(novoUsuario.getNome(), novoUsuario.getLogin(), novoUsuario.getPassword());
    }
}
