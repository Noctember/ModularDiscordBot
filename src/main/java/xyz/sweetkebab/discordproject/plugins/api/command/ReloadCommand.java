package xyz.sweetkebab.discordproject.plugins.api.command;

import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import xyz.sweetkebab.discordproject.BilBerry;
import xyz.sweetkebab.discordproject.managers.PluginManager;
import xyz.sweetkebab.discordproject.plugins.api.command.meta.Command;
import xyz.sweetkebab.discordproject.plugins.api.command.meta.CommandCategory;
import xyz.sweetkebab.discordproject.user.PermissionsAccess;

/**
 * This file is a part of discordbot, located on xyz.sweetkebab.discordproject.plugins.api.command
 *
 * @author SweetKebab_
 * Created the 2018-11-06 at 21:03.
 */
public class ReloadCommand extends AbstractCommand {
    @Command(command = {"reload"}, permission = PermissionsAccess.BOT_ADMINISTRATOR, category = CommandCategory.BOTMANAGEMENT)
    @Override
    public void execute(String[] args, MessageReceivedEvent e) {
        PluginManager pm = BilBerry.getInstance().getPluginManager();
        TextChannel tc = e.getTextChannel();
        tc.sendMessage("Unloading plugins...").queue(pm::unloadPlugins);
        tc.sendMessage("Loading plugins...").queue(pm::loadPlugins);
    }
}