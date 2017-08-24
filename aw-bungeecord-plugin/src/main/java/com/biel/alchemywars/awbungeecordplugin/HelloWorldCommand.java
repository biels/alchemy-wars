package com.biel.alchemywars.awbungeecordplugin;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class HelloWorldCommand extends Command {
    public HelloWorldCommand() {
        super("helloworld");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(new ComponentBuilder("Hello world!").color(ChatColor.AQUA).create());
    }
}
