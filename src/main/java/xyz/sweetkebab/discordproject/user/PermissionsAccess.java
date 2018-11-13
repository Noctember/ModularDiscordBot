package xyz.sweetkebab.discordproject.user;

/**
 * This file is a part of discordbot, located on xyz.sweetkebab.discordproject.user
 *
 * @author SweetKebab_
 * Created the 2018-11-06 at 21:28.
 */
public enum PermissionsAccess {
    USER(0), MODERATOR(5), DEVELOPPER(10), BOT_ADMINISTRATOR(20);

    private int id;

    PermissionsAccess(int id) {
        this.id = id;
    }

    public int getID() {return id;}
}
