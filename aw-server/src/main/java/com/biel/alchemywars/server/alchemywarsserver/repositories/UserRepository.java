package com.biel.alchemywars.server.alchemywarsserver.repositories;


import com.biel.alchemywars.server.alchemywarsserver.model.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> getByEmail(String email);
    Optional<User> getByName(String email);
}
