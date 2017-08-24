package com.biel.alchemywars.server.alchemywarsserver.model.user;

import com.biel.alchemywars.server.alchemywarsserver.model.match.MatchPlayer;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class UserSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    User user;

    Date startDate;
    Date endDate;

    @OneToMany(mappedBy = "userSession")
    List<MatchPlayer> matchesPlayed;

    public UserSession() {
        this.startDate = new Date();
    }

    public void finish(){
        endDate = new Date();
    }
    public boolean isFinished() {
        return endDate != null;
    }
    public boolean isEmpty(){
        return matchesPlayed.size() == 0;
    }
}
