package com.jorgejr.auth.api.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.jorgejr.auth.api.dtos.AuthDto;
import com.jorgejr.auth.api.models.Usuario;
import com.jorgejr.auth.api.repositories.UsuarioRepository;
import com.jorgejr.auth.api.services.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class AutenticacaoServiceImpl implements AutenticacaoService {

    @Autowired
    private UsuarioRepository repository;

    private AutenticacaoService autenticacaoService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return repository.findByLogin(login);
    }

    @Override
    public String obterToken(AuthDto authDto){

        Usuario usuario = repository.findByLogin(authDto.login());

        return geraTokenJwt(usuario);
    }

    public String geraTokenJwt(Usuario usuario){

        try {

            Algorithm algorithm = Algorithm.HMAC256("my-secret");

            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(geraDataExpiracao())
                    .sign(algorithm);


        } catch (JWTCreationException exception){
             throw new RuntimeException("Erro ao tentar gerar token! " + exception.getMessage());
        }
    }

    private Instant geraDataExpiracao(){
          return LocalDateTime.now()
                  .plusHours(8)
                  .toInstant(ZoneOffset.of("-03:00"));
    }

    public String validaTokenJwt(String token){

        try {

            Algorithm algorithm = Algorithm.HMAC256("my-secret");

            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();

        }catch (JWTVerificationException exception){
            return "";
        }

    }
}
