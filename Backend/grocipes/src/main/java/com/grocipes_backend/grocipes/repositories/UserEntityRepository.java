package com.grocipes_backend.grocipes.repositories;


import com.grocipes_backend.grocipes.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity,Integer> {
    Optional<UserEntity>findByEmail(String email);
    Boolean existsByEmail(String email);

    Optional<UserEntity>findUserEntitiesById(Integer id);


}
