package com.example.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;


public class JWTAuthFilter extends OncePerRequestFilter {
    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";
    private final String SECRET = "thisissecret";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            if (checkToken(request,response))
            {
                Claims claims=validateToken(request);
                if (!claims.isEmpty())
                    setSpringAuth(claims.getSubject());
                else
                    SecurityContextHolder.clearContext();
                chain.doFilter(request, response);
            }
            else
            {
                SecurityContextHolder.clearContext();
                chain.doFilter(request, response);
            }
        }
        catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException ex)
        {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_FORBIDDEN,ex.getMessage());
        }

    }
    private Claims validateToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
        return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
    }
    private void setSpringAuth(String username)
    {
        UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(username,null,Arrays.asList(new SimpleGrantedAuthority("prueba")));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
    private boolean checkToken(HttpServletRequest request, HttpServletResponse res) {
        String authenticationHeader = request.getHeader(HEADER);
        if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
            return false;
        return true;
    }


}

