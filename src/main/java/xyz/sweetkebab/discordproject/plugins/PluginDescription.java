package xyz.sweetkebab.discordproject.plugins;

import java.io.File;

/**
 * This file is a part of BerryGames, located on net.berrygames.bilberry.plugins
 * <p>
 * Copyright (c) BerryGames https://berrygames.net/ - All rights reserved
 * <p>
 *
 * @author SweetKebab_ {@literal <sweetkebab@berrygames.net>}
 * Created the 2018-10-26 at 17:48.
 */
public class PluginDescription {
    private String name;
    private String author;
    private String version;
    private String mainClass;
    private File file;

    private DiscordPlugin plugin;

    public PluginDescription(String name, String author, String version, String mainClass, File file) {
        this.name = name;
        this.author = author;
        this.version = version;
        this.mainClass = mainClass;

        this.file = file;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getVersion() {
        return version;
    }

    public String getMainClass() {
        return mainClass;
    }

    public File getFile() {
        return file;
    }

    public DiscordPlugin getPlugin() {
        return plugin;
    }

    public void setPlugin(DiscordPlugin plugin) {
        this.plugin = plugin;
    }
}
