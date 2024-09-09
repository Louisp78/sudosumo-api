package com.sudosumo.user;

import org.hibernate.annotations.Check;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "users")
@Check(constraints = "lifes >= 0")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String sub;

    @Column(unique = true)
    private String email;

    @Column(nullable = true, unique = true)
    private String username = null;
    @Column(name = "avatar_url")
    private String avatarUrl;

    private Integer noodles;

    private Integer lifes;

    public UserEntity() {
    }

    public UserEntity(String sub, String email, String avatarUrl, Integer noodles, Integer lifes) {
        this.sub = sub;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.noodles = noodles;
        this.lifes = lifes;
    }

    public String getSub() {
        return this.sub;
    }

    public String getEmail() {
        return this.email;
    }

    public String getUsername() {
        return this.username;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public Integer getNoodles() {
        return this.noodles;
    }

    public Integer getLifes() {
        return this.lifes;
    }
}
