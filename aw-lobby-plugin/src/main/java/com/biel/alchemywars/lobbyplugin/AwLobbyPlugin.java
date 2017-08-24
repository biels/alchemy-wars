package com.biel.alchemywars.lobbyplugin;

import com.apollographql.apollo.exception.ApolloException;
import com.biel.alchemywars.lobbyplugin.config.UnirestConfigurer;
import org.bukkit.plugin.java.JavaPlugin;

public class AwLobbyPlugin extends JavaPlugin {
    private Context context;
    @Override
    public void onEnable() {
        super.onEnable();
        getServer().getLogger().info("Preapring application context...");
        UnirestConfigurer.configure();
        context = new Context(this);
        context.initialize();
        getServer().getLogger().info("Context ready!");
        context.getRegisterer().ensureRegistered();
        getServer().broadcastMessage("Welcome to the LOBBY!");
        try {
            context.getGraphQLComponent().test();
        } catch (ApolloException e) {
            e.printStackTrace();
        }
    }
}
