package by.pharmsystem.userservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SecurityService {

    private final static String ROLE = "role";
    private final static String AUTHORITY = "authority";

    @Value("${expiration.time}")
    private String expirationTime;
    @Value("${secrete.code}")
    private String secret;
    @Value("${token.name}")
    private String tokenName;
    @Value("${token.prefix}")
    private String tokenPrefix;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;

    public String setAuthentication(String login, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(login);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        Authentication authentication =
                authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        List<GrantedAuthority> authorities = new ArrayList<>(authentication.getAuthorities());

        Claims claims = Jwts.claims().setSubject(authentication.getName());
        claims.put(ROLE, authorities.get(0));

        String JWT = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expirationTime)))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        return tokenPrefix + JWT;
    }

}
