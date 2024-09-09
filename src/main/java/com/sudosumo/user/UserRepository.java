package com.sudosumo.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import com.sudosumo.user.exception.NoLifeLeftException;
import com.sudosumo.user.exception.UserNotFoundException;

@Service
public class UserRepository {

    private static final Logger log = LoggerFactory.getLogger(UserRepository.class);

    private final UserJPARepository repository;

    private final TaskScheduler taskScheduler;

    @Autowired
    public UserRepository(UserJPARepository repository, TaskScheduler taskScheduler) {
        this.repository = repository;
        this.taskScheduler = taskScheduler;
    }

    public UserDTO getUserBySub(String sub) throws UserNotFoundException {
        try {
            final UserEntity user = repository.findBySub(sub);
            return new UserDTO(user.getUsername(), user.getAvatarUrl(), user.getNoodles(), user.getLifes());
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    public UserDTO createUserWithSub(String sub, String email, String avatarUrl) {
        final UserEntity user = new UserEntity(sub, email, avatarUrl, 0, 0);
        final UserEntity registerUser = repository.save(user);
        return new UserDTO(registerUser.getSub(), registerUser.getAvatarUrl(), registerUser.getNoodles(),
                registerUser.getLifes());
    }

    public void loseALifeFromSub(String sub) throws NoLifeLeftException {
        try {
            repository.decrementLifeByOne(sub);

            // TODO: implements life refill in a day
            /*
             * Instant triggerTime = Instant.now().plusMillis(5000);
             * taskScheduler.schedule(() -> refillLifes(sub), new Trigger() {
             * 
             * @Override
             * public Instant nextExecution(TriggerContext triggerContext) {
             * return triggerTime;
             * }
             * });
             */
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
}
