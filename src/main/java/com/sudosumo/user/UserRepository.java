package com.sudosumo.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.sudosumo.user.dto.request.UpdateUserDTO;
import com.sudosumo.user.exception.NoLifeLeftException;
import com.sudosumo.user.exception.UserNotFoundException;
import com.sudosumo.user.types.DifficultyEnum;

@Service
public class UserRepository {

    private static final Logger log = LoggerFactory.getLogger(UserRepository.class);

    private final UserJPARepository repository;

    @Autowired
    public UserRepository(UserJPARepository repository) {
        this.repository = repository;
    }

    public UserDTO getUserBySub(String sub) throws UserNotFoundException {
        try {
            final UserEntity user = repository.findBySub(sub);
            return UserMapper.getDTO(user);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    public UserDTO createUserWithSub(String sub, String email, String name, String avatarUrl) {
        final UserEntity user = new UserEntity(null, sub, email, name, null, avatarUrl, 0, 5, 0,
                DifficultyEnum.EASY.getAttribute(), null);
        final UserEntity registerUser = repository.save(user);
        return UserMapper.getDTO(registerUser);
    }

    public void loseALifeFromSub(String sub) throws NoLifeLeftException {
        try {
            repository.decrementLifeByOne(sub);
        } catch (DataAccessException e) {
            if (e.getMessage().contains("users_lifes_check")) {
                throw new NoLifeLeftException();
            }
        }
    }

    public void refillLifesBySub(String sub) {
        repository.setByMaxLifesBySub(5, sub);
    }

    public void refillLifesForEveryone() {
        repository.setByMaxLifes(5);
    }

    public void winNoodlesBySub(String sub, Integer noodlesWon) {
        repository.addNoodlesBySub(sub, noodlesWon);
    }

    public void winPuzzleBySub(String sub) {
        repository.addPuzzleBySub(sub);
    }

    public UserDTO updateUserBySub(UpdateUserDTO body, String sub) throws UserNotFoundException {
        try {
            final UserEntity user = repository.findBySub(sub);
            final UserEntity modifiedUser = UserMapper.getEntity(user, body);
            return UserMapper.getDTO(repository.save(modifiedUser));
        } catch (Exception e) {
            throw new UserNotFoundException();
        }

    }
}
