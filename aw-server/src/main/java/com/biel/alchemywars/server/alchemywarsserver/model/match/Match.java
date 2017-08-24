package com.biel.alchemywars.server.alchemywarsserver.model.match;

import com.biel.alchemywars.server.alchemywarsserver.model.base.BaseEntity;
import com.biel.alchemywars.server.alchemywarsserver.model.server.MatchServer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Match extends BaseEntity {

    @OneToOne
    MatchServer server;

    Date creationTime;
    Date startTime;
    Date endTime;

    @OneToMany(mappedBy = "match")
    List<MatchTeam> teams;

    public Match() {
        this.creationTime = new Date();
    }

    public void finish(){
        endTime = new Date();
    }
    public boolean isStarted(){
        return startTime != null;
    }
    public boolean isFinished(){
        return endTime != null;
    }
}
