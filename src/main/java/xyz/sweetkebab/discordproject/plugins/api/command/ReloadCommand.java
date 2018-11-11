package xyz.sweetkebab.discordproject.plugins.api.command;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import xyz.sweetkebab.discordproject.plugins.api.command.meta.Command;
import xyz.sweetkebab.discordproject.plugins.api.command.meta.CommandCategory;
import xyz.sweetkebab.discordproject.user.PermissionsAccess;

/**
 * This file is a part of discordbot, located on xyz.sweetkebab.discordproject.plugins.api.command
 *
 * @author SweetKebab_
 * Created the 2018-11-06 at 21:03.
 */
@Command(command = "reload", permission = PermissionsAccess.BOT_ADMINISTRATOR, category = CommandCategory.BOTMANAGEMENT)
public class ReloadCommand extends AbstractCommand{
    public void execute(String[] args, MessageReceivedEvent e) {
        System.out.println("oOoWoOwoOSd");
    }
}
