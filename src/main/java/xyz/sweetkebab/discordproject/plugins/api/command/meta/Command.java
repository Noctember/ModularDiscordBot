package xyz.sweetkebab.discordproject.plugins.api.command.meta;

import xyz.sweetkebab.discordproject.user.PermissionsAccess;

/**
 * This file is a part of BerryGames, located on net.berrygames.bilberry.plugins.api.command.meta
 * <p>
 * Copyright (c) BerryGames https://berrygames.net/ - All rights reserved
 * <p>
 *
 * @author SweetKebab_ {@literal <sweetkebab@berrygames.net>}
 * Created the 2018-10-27 at 20:18.
 */
public @interface Command {
    String[] command() default {};
    PermissionsAccess permission();
    CommandCategory category() default CommandCategory.UNKNOWN;
}
