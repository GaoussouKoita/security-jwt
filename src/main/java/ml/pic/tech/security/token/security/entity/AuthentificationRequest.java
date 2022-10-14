package ml.pic.tech.security.token.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class AuthentificationRequest {
    private String username;
    private String password;
}
