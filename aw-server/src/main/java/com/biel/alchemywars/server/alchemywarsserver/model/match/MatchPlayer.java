package com.biel.alchemywars.server.alchemywarsserver.model.match;

import com.biel.alchemywars.server.alchemywarsserver.model.base.BaseEntity;
import com.biel.alchemywars.server.alchemywarsserver.model.user.User;
import com.biel.alchemywars.server.alchemywarsserver.model.user.UserSession;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class MatchPlayer extends BaseEntity {

    @ManyToOne
    User user;
    @ManyToOne
    UserSession userSession;
    @ManyToOne
    MatchTeam team;

    //Statistics
    Integer damageDealt, damageTaken;
    Integer kills, deaths, assists;
    Integer objectivesTaken;
    Integer score;
    Integer accumulatedValue;
    //...


}
