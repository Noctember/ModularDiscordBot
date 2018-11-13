package xyz.sweetkebab.discordproject;

import redis.clients.jedis.Jedis;
import xyz.sweetkebab.discordproject.database.RedisManager;
import xyz.sweetkebab.discordproject.entities.GuildWrapper;
import xyz.sweetkebab.discordproject.managers.FileManager;
import xyz.sweetkebab.discordproject.managers.PluginManager;
import xyz.sweetkebab.discordproject.plugins.api.DiscordAPI;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 * This file is a part of BerryGames, located on net.berrygames.bilberry
 * <p>
 * Copyright (c) BerryGames https://berrygames.net/ - All rights reserved
 * <p>
 *
 * @author SweetKebab_ {@literal <sweetkebab@berrygames.net>}
 * Created the 2018-10-26 at 17:39.
 */
public class BilBerry {
    private static BilBerry instance;
    private FileManager fileManager;
    private PluginManager pluginManager;
    private DiscordBot discordBot;
    private Logger logger = Logger.getLogger("BilBerry");
    private DiscordAPI api;
    private Jedis jedis;

    public static HashMap<String, GuildWrapper> guilds = new HashMap<>();

    public void load() throws Exception {

        RedisManager redis = new RedisManager("localhost",6379);
        redis.connect();
        this.jedis = redis.getRedisDatabase().getJedisPool().getResource();

        instance = this;
        this.discordBot = new DiscordBot(this);
        this.fileManager = new FileManager();

        fileManager.load();
        discordBot.start();
        this.api = new DiscordAPI(this);
        this.pluginManager = new PluginManager();
        pluginManager.loadPlugins();

    }

    public static BilBerry getInstance() {
        return instance;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public PluginManager getPluginManager() {
        return this.pluginManager;
    }

    public void info(String message) {
        getLogger().info(message);
    }

    public Logger getLogger() {
        return logger;
    }

    public DiscordBot getBot() {
        return this.discordBot;
    }

    public DiscordAPI getAPI() {
        return this.api;
    }

    public Jedis getJedis() {
        return this.jedis;
    }

    public HashMap<String, GuildWrapper> getGuilds() {
        return guilds;
    }

}
