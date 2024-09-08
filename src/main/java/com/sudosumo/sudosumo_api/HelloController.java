package com.sudosumo.sudosumo_api;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @GetMapping("/")
    public static String hello(JwtAuthenticationToken principal) {
        return "You are connected from the account : " + principal.getCredentials();
    }
}
