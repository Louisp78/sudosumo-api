package com.sudosumo.user;

import java.util.Optional;
import com.sudosumo.user.dto.request.UpdateUserDTO;

public class UserMapper {
    static public UserDTO getDTO(UserEntity entity) {
        return new UserDTO(entity.getName(), entity.getUsername(), entity.getAvatarUrl(),
                entity.getNoodles(),
                entity.getLifes(), entity.getNbOfSolvedPuzzle(),
                entity.getPuzzleDifficulty(), entity.getBio());
    }

    static public UserEntity getEntity(UserEntity init, UpdateUserDTO dto) {
        return new UserEntity(
                init.getId(),
                init.getSub(),
                init.getEmail(),
                Optional.ofNullable(dto.getName()).orElse(init.getName()),
                Optional.ofNullable(dto.getUsername()).orElse(init.getUsername()),
                init.getAvatarUrl(),
                init.getNoodles(),
                init.getLifes(),
                init.getNbOfSolvedPuzzle(),
                init.getPuzzleDifficulty(),
                Optional.ofNullable(dto.getBio()).orElse(init.getBio()));
    }
}
