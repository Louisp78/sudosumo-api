package com.sudosumo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface UserJPARepository extends JpaRepository<UserEntity, Long> {
    UserEntity findBySub(String sub);

    @Transactional
    @Modifying
    @Query("UPDATE users u SET u.lifes = u.lifes - 1 WHERE u.sub = :sub")
    void decrementLifeByOne(String sub);

    @Transactional
    @Modifying
    @Query("UPDATE users u SET u.lifes = :maxLifes WHERE u.sub = :sub")
    void setByMaxLifesBySub(Integer maxLifes, String sub);

    @Transactional
    @Modifying
    @Query("UPDATE users u SET u.lifes = :maxLifes")
    void setByMaxLifes(Integer maxLifes);

    @Transactional
    @Modifying
    @Query("UPDATE users u SET u.noodles = u.noodles + :noodles WHERE u.sub = :sub")
    void addNoodlesBySub(String sub, Integer noodles);

    @Transactional
    @Modifying
    @Query("UPDATE users u SET u.nbOfSolvedPuzzle = u.nbOfSolvedPuzzle + 1 WHERE u.sub = :sub")
    void addPuzzleBySub(String sub);
}
