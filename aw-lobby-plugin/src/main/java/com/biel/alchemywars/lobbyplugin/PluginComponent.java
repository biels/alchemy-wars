package com.biel.alchemywars.lobbyplugin;

import com.biel.alchemywars.lobbyplugin.components.LocalInfo;
import com.biel.alchemywars.lobbyplugin.components.Registerer;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.logging.Logger;

@AllArgsConstructor
public class PluginComponent {
    @NonNull private Context context;
    @NonNull protected Logger l;

    public PluginComponent(Context context) {
        this.context = context;
        this.l = context.getPlugin().getLogger();
    }

    @NonNull
    protected AwLobbyPlugin getPlugin() {
        return context.getPlugin();
    }

    protected LocalInfo getLocalInfo() {
        return context.getLocalInfo();
    }

    protected Registerer getRegisterer() {
        return context.getRegisterer();
    }
}
