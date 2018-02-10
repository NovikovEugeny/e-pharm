package by.pharmsystem.gateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class SecurityService {

    @Value("${expiration.time}")
    private String expirationTime;

    @Value("${secrete.code}")
    private String secret;

    @Value("${token.name}")
    private String tokenName;

    @Value("${token.prefix}")
    private String tokenPrefix;

    private final static String ROLE = "role";
    private final static String AUTHORITY = "authority";

    @Autowired
    private AuthenticationManager authenticationManager;

    public SecurityService() {
    }

    public Authentication getAuthentication(HttpServletRequest request) throws ExpiredTokenException {
        String token = null;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(tokenName)) {
                    token = cookie.getValue();
                }
            }
        }

        if (token != null) {
            try {
                Claims body = Jwts.parser()
                        .setSigningKey(secret)
                        .parseClaimsJws(token.replace(tokenPrefix, ""))
                        .getBody();

                String userName = body.getSubject();
                String role = body.get(ROLE, Map.class).get(AUTHORITY).toString();

                Set<GrantedAuthority> roleSet = new HashSet<>();
                roleSet.add(new SimpleGrantedAuthority(role));

                return userName != null ?
                        new UsernamePasswordAuthenticationToken(userName, null, roleSet) :
                        null;
            } catch (ExpiredJwtException exc) {
                throw new ExpiredTokenException();
            }
        }
        return null;
    }

}
