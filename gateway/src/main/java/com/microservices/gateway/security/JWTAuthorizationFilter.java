package com.microservices.gateway.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.microservices.core.security.SecurityConstants.*;

@Component
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {

        try {
            String header = req.getHeader(HEADER_STRING);

            if (header != null && header.startsWith(TOKEN_PREFIX)) {
                UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            chain.doFilter(req, res);
        } catch (RuntimeException e) {
            res.setContentType("application/json");
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            DecodedJWT jwt = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""));
            String user = jwt.getSubject();
            Claim claim = jwt.getClaim("roles");
            List<SimpleGrantedAuthority> roles =
                    claim.asList(String.class)
                            .stream().map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, roles);
            }
            return null;
        }
        return null;
    }

}
