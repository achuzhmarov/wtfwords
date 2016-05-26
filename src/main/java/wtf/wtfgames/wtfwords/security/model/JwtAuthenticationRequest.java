package wtf.wtfgames.wtfwords.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationRequest {
    private String login;
    private String password;
}
