package com.biel.alchemywars.server.alchemywarsserver.components;

import com.biel.alchemywars.communication.protobuf.SpringToBungee;
import com.biel.alchemywars.server.alchemywarsserver.model.server.LobbyServer;
import com.biel.alchemywars.server.alchemywarsserver.model.server.Server;
import com.biel.alchemywars.server.alchemywarsserver.model.user.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.biel.alchemywars.communication.protobuf.SpringToBungee.*;

@Component
public class BungeeController {
    @Autowired
    RabbitTemplate rabbitTemplate;
    public void registerServer(Server server){
        ServerRegistration.ServerType type = (server instanceof LobbyServer) ?
                ServerRegistration.ServerType.LOBBY : ServerRegistration.ServerType.MATCH;
        ServerRegistration serverRegistration = ServerRegistration.newBuilder()
                .setPort(server.getPort())
                .setAction(ServerRegistration.Action.REGISTER)
                .setServerType(type)
                .build();
        rabbitTemplate.convertAndSend("server-registrations", serverRegistration.toByteArray());
    }
    public void sendPlayerToServer(User player, Server server){
        //File1 file1;
        //MsgList msgList;
    }
}
