package com.biel.alchemywars.lobbyplugin;

import lombok.Data;
import lombok.experimental.Delegate;


public class TestContext {
    private AwLobbyPlugin plugin;
    @Delegate private Context context;

    public TestContext() {
        plugin = new AwLobbyPlugin();
        context = new Context(plugin);
        context.initialize();
    }
}
