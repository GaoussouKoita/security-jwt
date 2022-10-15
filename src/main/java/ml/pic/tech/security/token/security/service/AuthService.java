package ml.pic.tech.security.token.security.service;

import ml.pic.tech.security.token.security.entity.AuthentificationRequest;
import ml.pic.tech.security.token.security.entity.AuthentificationResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private AuthenticationManager authManager;
    private JwtTokenUtils jwtTokenUtils;
    private UserDetailsServiceImpl userDetailsService;

    public AuthService(AuthenticationManager authManager, JwtTokenUtils jwtTokenUtils,
                       UserDetailsServiceImpl userDetailsService) {
        this.authManager = authManager;
        this.jwtTokenUtils = jwtTokenUtils;
        this.userDetailsService = userDetailsService;
    }

    public AuthentificationResponse createAuthResponseToken(AuthentificationRequest authRequest) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwtToken = jwtTokenUtils.generateToken(userDetails);
        return new AuthentificationResponse(jwtToken);
    }
}
