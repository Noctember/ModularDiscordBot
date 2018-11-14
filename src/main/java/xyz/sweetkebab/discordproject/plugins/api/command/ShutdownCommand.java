package xyz.sweetkebab.discordproject.plugins.api.command;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import xyz.sweetkebab.discordproject.plugins.api.command.meta.Command;
import xyz.sweetkebab.discordproject.user.PermissionsAccess;

/**
 * This file is a part of discordbot, located on xyz.sweetkebab.discordproject.plugins.api.command
 *
 * @author SweetKebab_
 * Created the 2018-11-13 at 20:15.
 */
public class ShutdownCommand extends AbstractCommand{
    @Override
    @Command(command = {"shutdown"}, permission = PermissionsAccess.BOT_ADMINISTRATOR)
    public void execute(String[] args, MessageReceivedEvent e) {

    }
}
