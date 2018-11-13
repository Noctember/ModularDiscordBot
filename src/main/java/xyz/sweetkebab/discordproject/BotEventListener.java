package xyz.sweetkebab.discordproject;

import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.ShutdownEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.ErrorResponseException;
import net.dv8tion.jda.core.exceptions.PermissionException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import xyz.sweetkebab.discordproject.plugins.api.DiscordAPI;
import xyz.sweetkebab.discordproject.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;

import static xyz.sweetkebab.discordproject.utils.Utils.hasPermission;

/**
 * This file is a part of discordbot, located on xyz.sweetkebab.discordproject
 *
 * @author SweetKebab_
 * Created the 2018-11-11 at 12:06.
 */
public class BotEventListener extends ListenerAdapter {
    private static void handleCommand(CommandContainer cmd) {
        if (DiscordAPI.commands.containsKey(cmd.invoke)) {
            cmd.event.getChannel().sendTyping().queue(success -> {
                MessageReceivedEvent e = cmd.event;
                try {
                    if (cmd.args.length > 0 && cmd.args[0].equals("-h")) {
                        e.getTextChannel().sendMessage("rip").queue();
                    } else {
                        if (hasPermission(e.getAuthor(), DiscordAPI.commandInfos.get(cmd.invoke).permission()))
                            DiscordAPI.commands.get(cmd.invoke).execute(cmd.args, e);
                    }
                } catch (NullPointerException | ErrorResponseException npe) {
                    npe.printStackTrace();

                } catch (PermissionException pe) {
                    pe.printStackTrace();
                    e.getChannel().sendMessage(" These permissions are needed! (" + e.getGuild().getName() + ")\n"
                            + "`" + pe.getPermission().getName() + "`").queue();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        }
    }

    public void onShutdown(ShutdownEvent event) {
        BilBerry bb = BilBerry.getInstance();
        bb.info("Shutting down");

        bb.getPluginManager().unloadPlugins();
    }

    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot() || event.getAuthor().isFake())
            return;

        if (event.getChannelType().isGuild() && !event.getGuild().isAvailable() ||
                (event.getChannelType().isGuild() && !event.getTextChannel().canTalk()) ||
                (!event.getChannelType().isGuild() && event.getPrivateChannel().isFake()))
            return;
        if (!event.getMessage().getAuthor().getId().equals(event.getJDA().getSelfUser().getId())) {
            if (event.getChannelType() == ChannelType.TEXT &&
                    (event.getMessage().getContentRaw().startsWith(BilBerry.getInstance().getAPI().getGuild(event.getGuild()).getPrefix()) ||
                            event.getMessage().getContentStripped().startsWith("@" + event.getGuild().getSelfMember().getEffectiveName()))) {
                handleCommand(parse(event.getMessage().getContentRaw(), event));
            }
        } else if (event.getChannelType() == ChannelType.PRIVATE) {
            handleCommand(parsePrivate(event.getMessage().getContentRaw(), event));
        }
    }


    private CommandContainer parsePrivate(String rw, MessageReceivedEvent e) {
        ArrayList<String> split = new ArrayList<>();

        String beheaded;
        if (rw.startsWith("!"))
            beheaded = rw.replaceFirst("!", "");
        else if (rw.startsWith("@" + e.getJDA().getSelfUser().getName()))
            beheaded = rw.replaceFirst("@" + e.getGuild().getSelfMember().getEffectiveName() + " ", "");
        else
            beheaded = rw;

        String[] splitBeheaded = beheaded.split("\\s+");

        split.addAll(Arrays.asList(splitBeheaded));

        String invoke = split.get(0);
        String[] args = new String[split.size() - 1];
        split.subList(1, split.size()).toArray(args);

        return new CommandContainer(rw, null, splitBeheaded, invoke, args, e);
    }

    public CommandContainer parse(String rw, MessageReceivedEvent e) {
        ArrayList<String> split = new ArrayList<>();

        String beheaded = "";

        if (rw.startsWith(BilBerry.getInstance().getAPI().getGuild(e.getGuild()).getPrefix())) {
            String a = Utils.getMessage(rw.split(" "), 1);
            beheaded = rw.split(" ")[0].substring(BilBerry.getInstance().getAPI().getGuild(e.getGuild()).getPrefix().length()) + " " + a;

        } else if (rw.startsWith("@" + e.getGuild().getSelfMember().getEffectiveName()))
            beheaded = rw.replace("@" + e.getGuild().getSelfMember().getEffectiveName() + " ", "");

        String[] splitBeheaded = beheaded.split("\\s+");

        split.addAll(Arrays.asList(splitBeheaded));

        String invoke = split.get(0).toLowerCase();
        String[] args = new String[split.size() - 1];
        split.subList(1, split.size()).toArray(args);

        return new CommandContainer(rw, beheaded, splitBeheaded, invoke, args, e);
    }

    public class CommandContainer {
        final String raw;
        final String beheaded;
        final String[] splitBeheaded;
        final String invoke;
        final String[] args;
        final MessageReceivedEvent event;

        CommandContainer(String rw, String Beheaded, String[] splitBeheaded, String invoke, String[] args, MessageReceivedEvent e) {
            this.raw = rw;
            this.beheaded = Beheaded;
            this.splitBeheaded = splitBeheaded;
            this.invoke = invoke;
            this.args = args;
            this.event = e;
        }
    }
}
