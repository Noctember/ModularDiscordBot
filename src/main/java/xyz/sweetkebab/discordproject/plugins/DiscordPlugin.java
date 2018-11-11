package xyz.sweetkebab.discordproject.plugins;

import xyz.sweetkebab.discordproject.plugins.api.DiscordAPI;
import xyz.sweetkebab.discordproject.plugins.api.DiscordException;

/**
 * This file is a part of BerryGames, located on net.berrygames.bilberry.plugins
 * <p>
 * Copyright (c) BerryGames https://berrygames.net/ - All rights reserved
 * <p>
 *
 * @author SweetKebab_ {@literal <sweetkebab@berrygames.net>}
 * Created the 2018-10-26 at 17:48.
 */
public abstract class DiscordPlugin {
    public DiscordAPI api;

    public abstract void onLoad();
    public abstract void onUnload();

    public DiscordAPI getApi() {
        return api;
    }
}
