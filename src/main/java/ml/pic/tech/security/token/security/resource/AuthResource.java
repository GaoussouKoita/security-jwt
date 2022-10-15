package ml.pic.tech.security.token.security.resource;

import ml.pic.tech.security.token.security.entity.AuthentificationRequest;
import ml.pic.tech.security.token.security.entity.AuthentificationResponse;
import ml.pic.tech.security.token.security.entity.Utilisateur;
import ml.pic.tech.security.token.security.service.AccountService;
import ml.pic.tech.security.token.security.service.JwtTokenUtils;
import ml.pic.tech.security.token.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class AuthResource {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private AccountService service;

    @PostMapping("/authentification")
    public ResponseEntity<?> createAuthentificationToken(@RequestBody AuthentificationRequest authRequest) {

        authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwtToken = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new AuthentificationResponse(jwtToken));
    }

    @PostMapping("/")
    public Utilisateur utilisateur(@RequestBody Utilisateur utilisateur) {
        return service.addUtilisateur(utilisateur);
    }

    @GetMapping("/")
    public List<Utilisateur> utilisateurList() {
        return service.utilisateurList();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
