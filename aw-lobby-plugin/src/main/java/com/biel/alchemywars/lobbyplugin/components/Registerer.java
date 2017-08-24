package com.biel.alchemywars.lobbyplugin.components;


import com.biel.alchemywars.communication.rest.ServerRegistrationRequest;
import com.biel.alchemywars.communication.utils.DockerServiceResolver;
import com.biel.alchemywars.lobbyplugin.AwLobbyPlugin;
import com.biel.alchemywars.lobbyplugin.Context;
import com.biel.alchemywars.lobbyplugin.PluginComponent;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.NonNull;

import java.net.URI;

import static com.biel.alchemywars.communication.utils.DockerServiceResolver.*;


public class Registerer extends PluginComponent {
    public Registerer(Context context) {
        super(context);
    }
    public boolean register(){
        try {
            ServerRegistrationRequest entity = new ServerRegistrationRequest(ServerRegistrationRequest.ServerType.LOBBY, getPlugin().getServer().getPort());

            HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.post(DockerService.SERVER.getHttpURL("/servers/register").toString())
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(entity)
//                    .field("port", getPlugin().getServer().getPort())
//                    .field("serverType", "LOBBY")
                    .asJson();
            int status = jsonNodeHttpResponse.getStatus();
            if(status != 201){
                l.severe("Cannot register, server returned " + status + " " + jsonNodeHttpResponse.getStatusText());
                return false;
            }
            URI location = URI.create(jsonNodeHttpResponse.getHeaders().getFirst("Location"));
            getLocalInfo().setMyURI(location);
            getLocalInfo().setRegistered(true);
        } catch (UnirestException e) {
            l.info("Cannot register, server unreachable.");
            l.throwing("Registerer", "register", e);
            e.printStackTrace();
            return false;
        }
        l.info("Registered successfully! as " + getLocalInfo().getMyURI());
        return true;
    }
    public void ensureRegistered() {
        if (!getLocalInfo().isRegistered())register();
        while (!getLocalInfo().isRegistered()){
            l.info("Registration failed. Retrying in 5s.");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            register();
        }
    }
}
