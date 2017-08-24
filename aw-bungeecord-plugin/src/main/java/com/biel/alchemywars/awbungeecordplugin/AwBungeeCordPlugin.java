package com.biel.alchemywars.awbungeecordplugin;

import net.md_5.bungee.api.config.ListenerInfo;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

public class AwBungeeCordPlugin extends Plugin {
    RabbitMQManager rabbitMQManager;
    ServerManager serverManager;

    @Override
    public void onEnable() {
        getProxy().getPluginManager().registerCommand(this, new HelloWorldCommand());
        serverManager = new ServerManager();
        serverManager.prepareListener();
        configureRabbitMQ();

    }

    private void configureRabbitMQ() {
        getLogger().info("Configuring RabbitMQ...");
        rabbitMQManager = new RabbitMQManager(serverManager);
        try {
            rabbitMQManager.start();
        } catch (IOException | TimeoutException e) {
            getLogger().severe("Could not configure RabbitMQ. See stack trace below:");
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getLogger().info("RabbitMQ is now ready");
    }

}
