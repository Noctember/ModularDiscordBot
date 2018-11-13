package xyz.sweetkebab.discordproject;

import net.dv8tion.jda.core.entities.Guild;
import xyz.sweetkebab.discordproject.entities.GuildWrapper;
import xyz.sweetkebab.discordproject.plugins.PluginLoadException;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.Yaml;
import xyz.sweetkebab.discordproject.utils.Assert;

import javax.security.auth.login.LoginException;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * This file is a part of BerryGames, located on net.berrygames.bilberry
 * <p>
 * Copyright (c) BerryGames https://berrygames.net/ - All rights reserved
 * <p>
 *
 * @author SweetKebab_ {@literal <sweetkebab@berrygames.net>}
 * Created the 2018-10-26 at 18:27.
 */
public class DiscordBot {
    private BilBerry instance;
    private JDA jda;


    DiscordBot(BilBerry instance) {
        this.instance = instance;
    }

    void start() throws LoginException, InterruptedException, FileNotFoundException {
        File file = new File("discord/configs/discordbot.yml");
        InputStream inputStream = new FileInputStream(file);
        Map<String, Object> properties = new Yaml().load(inputStream);
        Config config = null;
        try {
            config = construct(properties);
            Assert.notNull(config);
        } catch (PluginLoadException e) {
            e.printStackTrace();
        }

        jda = new JDABuilder(AccountType.BOT)
                .setToken(config.getToken())
                .setAutoReconnect(true)
                .setMaxReconnectDelay(300)
                .setEnableShutdownHook(true)
                .addEventListener(new BotEventListener())
                .buildBlocking();

        instance.info("Bot started");

        jda.getSelfUser().getManager().setName(config.getName()).queue();

        loadGuilds();
    }

    private void loadGuilds() {
        for(Guild guild : jda.getGuilds()) {
            BilBerry.guilds.put(guild.getId(), new GuildWrapper(jda, guild));
        }
    }

    private Config construct(Map<String, Object> properties) throws PluginLoadException {
         String token = null;
         String name = null;
         URL avatar = null;
        try {
            token = (String) properties.get("token");
            Assert.notNull(token);
        } catch (Exception e) {
            throw new PluginLoadException("Could not parse bot's token");
        }
        try {
            name = (String) properties.get("name");
            Assert.notNull(name);
        } catch (Exception e) {
            throw new PluginLoadException("Could not parse bot's name");
        }
        return new Config(token, name);
    }

    public JDA getJDA() {
        return this.jda;
    }

    private class Config {
        @NotNull
        private String token;
        private String name;

        Config(@NotNull String token, String name) {
            this.token = token;
            this.name = name;
        }

        String getToken() {
            return token;
        }

        String getName() {
            return name;
        }
    }
}
