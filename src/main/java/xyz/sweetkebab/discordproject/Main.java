package xyz.sweetkebab.discordproject;

/**
 * This file is a part of BerryGames, located on net.berrygames.bilberry
 * <p>
 * Copyright (c) BerryGames https://berrygames.net/ - All rights reserved
 * <p>
 *
 * @author SweetKebab_ {@literal <sweetkebab@berrygames.net>}
 * Created the 2018-10-26 at 17:39.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF] [%4$-7s] %5$s %n");

        BilBerry bb = new BilBerry();
        bb.info("Starting..");
        bb.load();
    }
}
