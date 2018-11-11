package xyz.sweetkebab.discordproject.plugins.api;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import xyz.sweetkebab.discordproject.BilBerry;
import xyz.sweetkebab.discordproject.plugins.api.command.AbstractCommand;
import xyz.sweetkebab.discordproject.plugins.api.command.ReloadCommand;
import xyz.sweetkebab.discordproject.plugins.api.command.meta.Command;

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
    private HashMap<String[], AbstractCommand> commands;
    private BilBerry instance;
    private JDA jda;

    public DiscordAPI(BilBerry instance) {
        this.instance = instance;
        this.commands = new HashMap<>();
        this.jda = instance.getBot().getJDA();
    }

    public void registerCommand(Object handler) {
        Class<?> clasz = handler.getClass();
        if(!clasz.isAssignableFrom(AbstractCommand.class))
            try {
                throw new DiscordException(handler.getClass().getName()+" class doesn't extends AbstractCommand");
            } catch (DiscordException e) {
                e.printStackTrace();
            }
        Command command = clasz.getAnnotation(Command.class);
        this.commands.put(command.command(), (AbstractCommand) handler);
    }

    public void registerListener(ListenerAdapter listener) {
        this.jda.addEventListener(listener);
    }

    private void processCommand(Guild guild, MessageChannel channel, User author, MessageReceivedEvent event) {

    }
}
