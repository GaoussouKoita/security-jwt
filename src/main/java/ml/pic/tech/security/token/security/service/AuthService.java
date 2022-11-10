package ml.pic.tech.security.token.security.service;

import ml.pic.tech.security.token.security.entity.AuthentificationRequest;
import ml.pic.tech.security.token.security.entity.AuthentificationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private DaoAuthenticationProvider authManager;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private AccountService accountService;


    public AuthentificationResponse createAuthResponseToken(AuthentificationRequest authRequest) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        final UserDetails userDetails = accountService.loadUserByUsername(authRequest.getUsername());
        final String jwtToken = jwtTokenUtils.generateToken(userDetails);
        return new AuthentificationResponse(jwtToken);
    }
}
