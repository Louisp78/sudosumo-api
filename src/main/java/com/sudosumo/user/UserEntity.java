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

    private String name;

    @Column(nullable = true, unique = true)
    private String username = null;

    private String avatarUrl;

    private Integer noodles;

    private Integer lifes;

    private Integer nbOfSolvedPuzzle;

    // TODO: add constraint for enum
    private String puzzleDifficulty;

    private String bio = null;

    public UserEntity() {
    }

    public UserEntity(Long id, String sub, String email, String name, String username, String avatarUrl,
            Integer noodles,
            Integer lifes,
            Integer nbOfSolvedPuzzle, String puzzleDifficulty, String bio) {
        this.id = id;
        this.sub = sub;
        this.email = email;
        this.name = name;
        this.username = username;
        this.avatarUrl = avatarUrl;
        this.noodles = noodles;
        this.lifes = lifes;
        this.nbOfSolvedPuzzle = nbOfSolvedPuzzle;
        this.puzzleDifficulty = puzzleDifficulty;
        this.bio = bio;
    }

    public Long getId() {
        return this.id;
    }

    public String getSub() {
        return this.sub;
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
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

    public Integer getNbOfSolvedPuzzle() {
        return this.nbOfSolvedPuzzle;
    }

    public String getPuzzleDifficulty() {
        return this.puzzleDifficulty;
    }

    public String getBio() {
        return this.bio;
    }
}
