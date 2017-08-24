package com.biel.alchemywars.lobbyplugin.components;

import com.biel.alchemywars.lobbyplugin.Context;
import com.biel.alchemywars.lobbyplugin.PluginComponent;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.URI;

@Data
public class LocalInfo extends PluginComponent{
    private URI myURI;
    private boolean registered;

    public LocalInfo(Context context) {
        super(context);
    }


}
