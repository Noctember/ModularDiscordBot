package xyz.sweetkebab.discordproject.plugins.api.command;

import xyz.sweetkebab.discordproject.plugins.api.command.meta.CommandCategory;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * This file is a part of BerryGames, located on net.berrygames.bilberry.plugins.api.command
 * <p>
 * Copyright (c) BerryGames https://berrygames.net/ - All rights reserved
 * <p>
 *
 * @author SweetKebab_ {@literal <sweetkebab@berrygames.net>}
 * Created the 2018-10-27 at 17:43.
 */
public abstract class AbstractCommand {

    public abstract void execute(String[] args, MessageReceivedEvent e);

}
