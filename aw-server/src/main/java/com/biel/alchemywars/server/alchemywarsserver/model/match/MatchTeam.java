package com.biel.alchemywars.server.alchemywarsserver.model.match;

import com.biel.alchemywars.server.alchemywarsserver.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class MatchTeam extends BaseEntity{

    @ManyToOne
    Match match;

    @OneToMany
    List<MatchPlayer> players;

    //Statistics

    public Integer getTotalAccumulatedValue(){
        return players.stream().map(p -> p.getAccumulatedValue()).mapToInt(i -> i).sum();
    }
}
