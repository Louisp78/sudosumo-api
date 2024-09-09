package com.sudosumo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudosumo.user.dto.request.NoodlesDTO;
import com.sudosumo.user.exception.UserNotFoundException;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public ResponseEntity<?> getCurrentUser(JwtAuthenticationToken principal) {
        final String sub = principal.getToken().getSubject();
        try {
            final UserDTO user = repository.getUserBySub(sub);
            return ResponseEntity.ok(user);

        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /*
     * Call this endpoint after successfull OAuth2 auth
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUserIfNeeded(JwtAuthenticationToken principal) {
        final String sub = principal.getToken().getSubject();
        try {
            final UserDTO user = repository.getUserBySub(sub);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            final String avatarUrl = principal.getToken().getClaimAsString("picture");
            final String email = principal.getToken().getClaimAsString("email");
            final UserDTO userCreated = repository.createUserWithSub(sub, email, avatarUrl);
            return ResponseEntity.ok(userCreated);
        }

    }

    @PutMapping("/loseALife")
    public ResponseEntity<?> loseALife(JwtAuthenticationToken principal) {
        final String sub = principal.getToken().getSubject();
        try {
            repository.loseALifeFromSub(sub);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No life left");
        }
    }

    @PutMapping("/winNoodles")
    public ResponseEntity<?> winNoodles(JwtAuthenticationToken principal,
            @RequestBody NoodlesDTO body) {
        final String sub = principal.getToken().getSubject();
        repository.winNoodlesBySub(sub, body.getNoodles());
        return ResponseEntity.ok().build();
    }
}