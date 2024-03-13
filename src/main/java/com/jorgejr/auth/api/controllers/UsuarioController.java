package com.jorgejr.auth.api.controllers;

import com.jorgejr.auth.api.dtos.UsuarioDto;
import com.jorgejr.auth.api.models.Usuario;
import com.jorgejr.auth.api.repositories.UsuarioRepository;
import com.jorgejr.auth.api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

     @Autowired
     private UsuarioService usuarioService;

     @Autowired
     private UsuarioRepository usuarioRepository;

     @PostMapping
     private UsuarioDto salvar(@RequestBody UsuarioDto usuarioDto){
         return usuarioService.salvar(usuarioDto);
     }

     @GetMapping
     private List<Usuario> buscarUsuarios(){
           return usuarioRepository.findAll();
     }
}
