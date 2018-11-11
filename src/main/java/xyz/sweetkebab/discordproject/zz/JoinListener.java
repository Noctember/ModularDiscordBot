package xyz.sweetkebab.discordproject.zz;

import net.dv8tion.jda.core.events.channel.category.CategoryDeleteEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * This file is a part of discordbot, located on xyz.sweetkebab.discordproject.zz
 *
 * @author SweetKebab_
 * Created the 2018-11-09 at 21:27.
 */
public class JoinListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        System.out.println(event.getMessage().getContentRaw());
    }
}
