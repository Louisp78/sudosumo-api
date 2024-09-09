package com.sudosumo.controller;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public static String hello(JwtAuthenticationToken principal) {
        return String.format("Here you're infos :\nSub: %s\nEmail: %s\nAvatarUrl: %s",
                principal.getToken().getSubject(), principal.getToken().getClaimAsString("email"),
                principal.getToken().getClaimAsString("picture"));
    }
}
