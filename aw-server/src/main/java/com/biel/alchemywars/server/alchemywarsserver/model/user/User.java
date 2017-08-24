package com.biel.alchemywars.server.alchemywarsserver.model.user;

import com.biel.alchemywars.server.alchemywarsserver.model.match.Match;
import com.biel.alchemywars.server.alchemywarsserver.model.match.MatchPlayer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NaturalId
    private String name;
    @JsonIgnore
    private String password;
    @Email
    private String email;

    @OneToMany
    private List<MatchPlayer> matchPlayers;

    @OneToMany(mappedBy = "user")
    private List<UserSession> sessions;

    protected User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public List<Match> getMatches(){
        return matchPlayers.stream()
                .map(matchPlayer -> matchPlayer.getTeam().getMatch())
                .collect(Collectors.toList());
    }

}
