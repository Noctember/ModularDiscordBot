package xyz.sweetkebab.discordproject.entities;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;
import org.jetbrains.annotations.NotNull;
import xyz.sweetkebab.discordproject.BilBerry;

import java.util.*;

import static xyz.sweetkebab.discordproject.utils.Utils.getGson;

/**
 * This file is a part of discordbot, located on xyz.sweetkebab.discordproject.entities
 *
 * @author SweetKebab_
 * Created the 2018-11-10 at 00:19.
 */
public class GuildWrapper {
    /*
    *   JDA
    * */
    private final JDA jda;
    private final Guild guild;

    /*
    *   Music
    * */
    private VoiceChannel vc;
    private TextChannel tc;

    /*
     * Guild ID and Prefix
     * */
    private String guildId, prefix;
    private GuildInfos guildInfos;

    public GuildWrapper(JDA jda/*, AudioPlayerManager manager*/, Guild guild) {
        this.jda = jda;
        this.guild = guild;
        this.guildId = guild.getId();
        if(BilBerry.getInstance().getJedis().exists("discord:guild:"+guildId))
            this.guildInfos = getGson().fromJson(BilBerry.getInstance().getJedis().get("discord:guild:"+guildId), GuildInfos.class);
        else
            this.guildInfos = new GuildInfos(guildId, "!", new ArrayList<>());
        this.prefix = guildInfos.getPrefix();
    }

    public String getPrefix() {
        return prefix;
    }
}
