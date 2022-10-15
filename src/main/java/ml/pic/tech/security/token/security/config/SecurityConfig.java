package ml.pic.tech.security.token.security.config;

import ml.pic.tech.security.token.utilis.Constante;
import ml.pic.tech.security.token.utilis.Endpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig {

    private JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

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

}
