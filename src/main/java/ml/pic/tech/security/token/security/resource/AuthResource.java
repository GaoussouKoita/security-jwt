package ml.pic.tech.security.token.security.resource;

import ml.pic.tech.security.token.security.entity.AuthentificationRequest;
import ml.pic.tech.security.token.security.entity.ChangePassword;
import ml.pic.tech.security.token.security.entity.Utilisateur;
import ml.pic.tech.security.token.security.service.AccountService;
import ml.pic.tech.security.token.security.service.AuthService;
import ml.pic.tech.security.token.utilis.Endpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(Endpoint.UTILISATEUR_ENDPOINT)
public class AuthResource {


    private AuthService authService;
    private AccountService service;

    public AuthResource(AuthService authService, AccountService service) {
        this.authService = authService;
        this.service = service;
    }

    @PostMapping(Endpoint.AUTHENTIFICATION_ENDPOINT)
    public ResponseEntity<?> createAuthentificationToken(@RequestBody AuthentificationRequest authRequest) {

        return ResponseEntity.ok(authService.createAuthResponseToken(authRequest));
    }

    @PostMapping
    public Utilisateur utilisateur(@RequestBody Utilisateur utilisateur) {
        return service.addUtilisateur(utilisateur);
    }

    @GetMapping
    public List<Utilisateur> utilisateurList() {
        return service.utilisateurList();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PostMapping(Endpoint.PASSWORD_ENDPOINT)
    public void changePassword(@RequestBody ChangePassword newPassword) {
        service.updatePassword(newPassword);
    }
}
