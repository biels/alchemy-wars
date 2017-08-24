package com.biel.alchemywars.server.alchemywarsserver.components;

import com.biel.alchemywars.server.alchemywarsserver.model.user.User;
import com.biel.alchemywars.server.alchemywarsserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Populator implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {
        User bielUser = userRepository.save(new User("biel", "1234"));
    }
}
