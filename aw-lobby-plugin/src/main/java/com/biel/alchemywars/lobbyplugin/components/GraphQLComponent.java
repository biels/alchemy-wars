package com.biel.alchemywars.lobbyplugin.components;

import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.ApolloQueryCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.biel.alchemywars.communication.utils.DockerServiceResolver;
import com.biel.alchemywars.lobbyplugin.Context;
import com.biel.alchemywars.lobbyplugin.LobbyServerQuery;
import com.biel.alchemywars.lobbyplugin.PluginComponent;
import okhttp3.HttpUrl;

public class GraphQLComponent extends PluginComponent {
    ApolloClient apolloClient;
    public GraphQLComponent(Context context) {
        super(context);
    }
    public void test() throws ApolloException {
        apolloClient = ApolloClient.builder()
                .serverUrl(DockerServiceResolver.DockerService.SERVER.getHttpURL("/graphql").toString())
                .build();
        LobbyServerQuery lobbyServerQuery = LobbyServerQuery.builder()
                .id(1)
                .build();
        Response<LobbyServerQuery.Data> response = apolloClient.query(lobbyServerQuery).execute();
        l.info("Queried port (id 1): " + response.data().lobbyServer().port());
    }
}
