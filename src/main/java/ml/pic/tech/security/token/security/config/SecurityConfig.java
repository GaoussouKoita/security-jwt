package ml.pic.tech.security.token.security.config;

import ml.pic.tech.security.token.security.service.AccountService;
import ml.pic.tech.security.token.utilis.Constante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Autowired
    private AccountService accountService;
    @Autowired
    private PasswordEncoder passwordEnconder;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().
                authorizeRequests().antMatchers(Constante.WHITE_LIST).permitAll().
                antMatchers(Constante.WHITE_LIST_UTILISATEUR_ROLE)
                .hasAnyAuthority(new String[]{Constante.ADMIN_ROLE, Constante.USER_ROLE}).
                antMatchers(Constante.WHITE_LIST_ADMINISTRATEUR_ROLE)
                .hasAuthority(Constante.ADMIN_ROLE).
                anyRequest().authenticated().
                and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(accountService);
        authProvider.setPasswordEncoder(passwordEnconder);
        return authProvider;
    }

}
