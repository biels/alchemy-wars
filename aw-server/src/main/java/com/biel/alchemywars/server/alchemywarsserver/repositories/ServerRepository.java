package com.biel.alchemywars.server.alchemywarsserver.repositories;

import com.biel.alchemywars.server.alchemywarsserver.model.server.Server;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ServerRepository extends CrudRepository<Server, Long> {
    Optional<Server> findByPort(Integer port);
}
