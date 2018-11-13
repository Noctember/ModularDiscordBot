package xyz.sweetkebab.discordproject.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dv8tion.jda.core.entities.User;
import redis.clients.jedis.Jedis;
import xyz.sweetkebab.discordproject.BilBerry;
import xyz.sweetkebab.discordproject.user.PermissionsAccess;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * This file is a part of discordbot, located on xyz.sweetkebab.discordproject.utils
 *
 * @author SweetKebab_
 * Created the 2018-11-11 at 10:38.
 */
public class Utils {
    private static Gson gson = new GsonBuilder().create();

    public static Gson getGson() {
        return gson;
    }

    public static boolean hasPermission(User user, PermissionsAccess permission) {
        Jedis j = BilBerry.getInstance().getJedis();
        if(j.exists("discord:rank:"+user.getId())){
            int rank = Integer.parseInt(j.get("discord:rank:"+user.getId()));
            return rank >= permission.getID();
        } else {
            j.set("discord:rank:"+user.getId(), PermissionsAccess.USER.getID()+"");
            return false;
        }
    }

    public static String getMessage(String[] args, int min) {
        return Arrays.stream(args).skip(min).collect(Collectors.joining(" ")).trim();
    }
}
