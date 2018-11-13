package xyz.sweetkebab.discordproject.zz;

import xyz.sweetkebab.discordproject.plugins.DiscordPlugin;
import xyz.sweetkebab.discordproject.plugins.api.DiscordAPI;
import xyz.sweetkebab.discordproject.plugins.api.DiscordException;
import xyz.sweetkebab.discordproject.plugins.api.command.ReloadCommand;

/**
 * This file is a part of BerryGames, located on net.berrygames.bilberry
 * <p>
 * Copyright (c) BerryGames https://berrygames.net/ - All rights reserved
 * <p>
 *
 * @author SweetKebab_ {@literal <sweetkebab@berrygames.net>}
 * Created the 2018-10-27 at 00:57.
 */
public class tt extends DiscordPlugin {

    public void onLoad()  {
        getApi().registerCommand(new ReloadCommand());
//        getApi().registerListener(new JoinListener());
    }

    public void onUnload() {

    }
}
