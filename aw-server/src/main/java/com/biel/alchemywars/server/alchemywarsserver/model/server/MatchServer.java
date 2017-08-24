package com.biel.alchemywars.server.alchemywarsserver.model.server;

import com.biel.alchemywars.server.alchemywarsserver.model.match.Match;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class MatchServer extends Server {
    @OneToOne
    Match match;


}

