package com.biel.alchemywars.lobbyplugin;

import com.biel.alchemywars.lobbyplugin.components.GraphQLComponent;
import com.biel.alchemywars.lobbyplugin.components.LocalInfo;
import com.biel.alchemywars.lobbyplugin.components.Registerer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Context {
    @NonNull private AwLobbyPlugin plugin;
    private LocalInfo localInfo;
    private Registerer registerer;
    private GraphQLComponent graphQLComponent;

    public void initialize(){
        localInfo = new LocalInfo(this);
        registerer = new Registerer(this);
        graphQLComponent = new GraphQLComponent(this);
    }
}
