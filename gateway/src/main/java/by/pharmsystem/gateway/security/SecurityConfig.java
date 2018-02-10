package by.pharmsystem.gateway.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityService securityService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/e-pharm/user-service/sign-in").permitAll()
                .antMatchers(HttpMethod.POST, "/e-pharm/user-service/sign-up").permitAll()
                .antMatchers(HttpMethod.POST, "/e-pharm/user-service/log-out").permitAll()
                .antMatchers(HttpMethod.POST, "/e-pharm/user-service/add").hasAuthority("admin")
                .antMatchers(HttpMethod.DELETE, "/e-pharm/user-service/delete").hasAuthority("admin")
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JWTAuthenticationFilter(securityService), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
