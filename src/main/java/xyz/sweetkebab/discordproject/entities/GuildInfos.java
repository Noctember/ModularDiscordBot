package xyz.sweetkebab.discordproject.entities;

import java.util.List;

/**
 * This file is a part of discordbot, located on xyz.sweetkebab.discordproject.entities
 *
 * @author SweetKebab_
 * Created the 2018-11-11 at 10:06.
 */
public class GuildInfos {
    private String id;
    private String prefix;
    private List<String>  modRoles;

    public GuildInfos(String id, String prefix, List<String> modRoles) {
        this.id = id;
        this.prefix = prefix;
        this.modRoles = modRoles;
    }

    public String getID() {
        return id;
    }

    public String getPrefix() {
        return prefix;
    }

    public List<String> getModRoles() {
        return modRoles;
    }
}
