package com.fyeeme.resourceserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class MessageController {

    @GetMapping("/")
    public String index(@AuthenticationPrincipal Jwt jwt) {
        return String.format("Hello, %s!", jwt.getSubject());
    }

    @GetMapping("/message/{id}")
    public String message(@PathVariable Long id, @CurrentSecurityContext(expression = "authentication") Authentication authentication) {
        log.info("{}", authentication);
        return "secret message";
    }

    @PostMapping("/message")
    public String createMessage(@RequestBody String message) {
        return String.format("Message was created. Content: %s", message);
    }

    @GetMapping("/users/oidc-principal")
    public Map getOidcUserPrincipal(
//            @AuthenticationPrincipal OidcUser principal,
            @CurrentSecurityContext(expression = "authentication") Authentication authentication) {
        var map = new HashMap<String, Object>();
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        map.put("principal", principal);
        map.put("authentication", authentication);
        return map;
    }
}
