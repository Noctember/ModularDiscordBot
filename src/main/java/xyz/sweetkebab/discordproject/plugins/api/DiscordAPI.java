package xyz.sweetkebab.discordproject.plugins.api;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import xyz.sweetkebab.discordproject.BilBerry;
import xyz.sweetkebab.discordproject.entities.GuildWrapper;
import xyz.sweetkebab.discordproject.plugins.api.command.AbstractCommand;
import xyz.sweetkebab.discordproject.plugins.api.command.meta.Command;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

/**
 * This file is a part of BerryGames, located on net.berrygames.bilberry.plugins.api
 * <p>
 * Copyright (c) BerryGames https://berrygames.net/ - All rights reserved
 * <p>
 *
 * @author SweetKebab_ {@literal <sweetkebab@berrygames.net>}
 * Created the 2018-10-27 at 01:30.
 */
public class DiscordAPI {
    public static HashMap<String, AbstractCommand> commands = new HashMap<>();
    public static HashMap<String, Command> commandInfos = new HashMap<>();
    private BilBerry instance;
    private JDA jda;

    public DiscordAPI(BilBerry instance) {
        this.instance = instance;
        this.jda = instance.getBot().getJDA();
    }

    public void registerCommand(AbstractCommand handler) {
        Command command = null;
        try {
            command = handler.getClass().getMethod("execute", String[].class, MessageReceivedEvent.class).getAnnotation(Command.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.asList(command.command()).toString()+"\n"+handler.getClass().getName());
        for(String commande : command.command()) {
            commands.put(commande, handler);
            commandInfos.put(commande, command);
        }
    }


    public void registerListener(ListenerAdapter listener) {
        this.jda.addEventListener(listener);
    }

    public GuildWrapper getGuild(Guild guild) {
        return BilBerry.getInstance().getGuilds().get(guild.getId());
    }

    public GuildWrapper getGuild(String guild) {
        return BilBerry.getInstance().getGuilds().get(guild);
    }

    public HashMap<String, AbstractCommand> getCommands() {
        return commands;
    }
}
