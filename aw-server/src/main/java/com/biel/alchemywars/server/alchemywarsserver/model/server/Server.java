package com.biel.alchemywars.server.alchemywarsserver.model.server;

import com.biel.alchemywars.server.alchemywarsserver.model.base.BaseEntity;
import com.biel.alchemywars.server.alchemywarsserver.model.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public abstract class Server extends BaseEntity {
    private Integer port;

    public enum Status {SCALING_UP, STARTING, UP, DOWN}

    private Status status;

    @OneToMany
    private List<User> users;

    @Override
    public String toString() {
        return "Server{" +
                "id=" + getId() +
                ", port=" + port +
                ", status=" + status +
                '}';
    }
}
