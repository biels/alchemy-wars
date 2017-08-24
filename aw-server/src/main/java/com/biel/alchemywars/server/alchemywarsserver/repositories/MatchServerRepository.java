package com.biel.alchemywars.server.alchemywarsserver.repositories;

import com.biel.alchemywars.server.alchemywarsserver.model.server.MatchServer;
import org.springframework.data.repository.CrudRepository;

public interface MatchServerRepository extends CrudRepository<MatchServer, Long> {
}
