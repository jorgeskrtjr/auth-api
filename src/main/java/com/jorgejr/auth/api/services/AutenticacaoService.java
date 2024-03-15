package com.jorgejr.auth.api.services;

import com.jorgejr.auth.api.dtos.AuthDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AutenticacaoService extends UserDetailsService {

     public String obterToken(AuthDto authDto);

     public String validaTokenJwt(String token);

}
