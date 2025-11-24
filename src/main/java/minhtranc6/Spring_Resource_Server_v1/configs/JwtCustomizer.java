package minhtranc6.Spring_Resource_Server_v1.configs;

import minhtranc6.Spring_Resource_Server_v1.entities.MyUser;
import minhtranc6.Spring_Resource_Server_v1.repositories.UserDetailRepository;
import org.springframework.context.annotation.*;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

@Configuration
public class JwtCustomizer {
    private final UserDetailRepository users;

    public JwtCustomizer(UserDetailRepository users) {
        this.users = users;
    }

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> customizeJwt() {
        return context -> {

            if (!context.getTokenType().getValue().equals("access_token")) return;

            String username = context.getPrincipal().getName();
            MyUser user = users.findByUserName(username).orElse(null);

            if (user == null) return;

            context.getClaims().claim("user_id", user.getId());
            context.getClaims().claim("username", user.getUserName());
            context.getClaims().claim("roles", user.getRole());
        };
    }
}
