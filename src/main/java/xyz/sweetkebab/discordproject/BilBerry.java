package xyz.sweetkebab.discordproject;

import xyz.sweetkebab.discordproject.managers.FileManager;
import xyz.sweetkebab.discordproject.managers.PluginManager;
import xyz.sweetkebab.discordproject.plugins.api.DiscordAPI;

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
    private Logger logger;
    private DiscordAPI api;

    public void load() throws Exception {
        instance = this;
        logger = Logger.getLogger("BilBerry");
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
}
