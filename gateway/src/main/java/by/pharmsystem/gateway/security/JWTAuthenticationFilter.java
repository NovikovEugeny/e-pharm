package by.pharmsystem.gateway.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JWTAuthenticationFilter extends GenericFilterBean {

    private SecurityService securityService;

    public JWTAuthenticationFilter(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        try {
            Authentication authentication = securityService.getAuthentication((HttpServletRequest) request);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (ExpiredTokenException exc) {
            //если не обработать, то бросит 403
        }

        filterChain.doFilter(request, response);
    }
}
