package com.microservices.auth.security;

import com.microservices.auth.user.User;
import com.microservices.auth.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    @Lazy
    UserService userService;

    @Autowired
    @Lazy
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    DaoAuthenticationProvider databaseAuthenticationProvider;

    @Bean
    protected DaoAuthenticationProvider databaseAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return authProvider;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED": authentication.getName();
        User user = userService.loadUserByUsername(username);
        if(!isNull(user)) {
            return databaseAuthenticationProvider.authenticate(authentication);
        } else {
            throw new BadCredentialsException("Erro ao autenticar.");
        }

    }

    @Override
    public boolean supports(Class<?> authentication){
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }


}
