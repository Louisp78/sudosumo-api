package com.sudosumo.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private String fullname;
    private String username;
    private String avatarUrl;
    private Integer noodles;
    private Integer lifes;
    private Integer nbOfSolvedPuzzle;
    private String puzzleDifficulty;
    private String bio;
}
