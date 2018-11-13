package xyz.sweetkebab.discordproject.plugins.api.command.meta;

import xyz.sweetkebab.discordproject.user.PermissionsAccess;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This file is a part of BerryGames, located on net.berrygames.bilberry.plugins.api.command.meta
 * <p>
 * Copyright (c) BerryGames https://berrygames.net/ - All rights reserved
 * <p>
 *
 * @author SweetKebab_ {@literal <sweetkebab@berrygames.net>}
 * Created the 2018-10-27 at 20:18.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    String[] command();
    PermissionsAccess permission();
    CommandCategory category() default CommandCategory.UNKNOWN;
}
