package xyz.sweetkebab.discordproject.managers;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This file is a part of BerryGames, located on net.berrygames.bilberry.managers
 * <p>
 * Copyright (c) BerryGames https://berrygames.net/ - All rights reserved
 * <p>
 *
 * @author SweetKebab_ {@literal <sweetkebab@berrygames.net>}
 * Created the 2018-10-26 at 17:53.
 */
public class FileManager {
    private File baseDirectory;
    private File pluginsDirectory;
    private File configsDirectory;

    private File configFile;
    private Map<String, Object> config;

    public FileManager() {
    }

    public void load() {
        try {

            baseDirectory = new File("discord/");
            baseDirectory.mkdirs();

            configsDirectory = new File(baseDirectory, "configs/");
            configsDirectory.mkdirs();
            pluginsDirectory = new File(baseDirectory, "plugins/");
            pluginsDirectory.mkdirs();
/*
            this.configFile = new File(configsDirectory, "discordbot.yml");
            configFile.createNewFile();
            config = (Map<String, Object>) loadYaml(configFile);
            if (config == null) config = new LinkedHashMap<>();
            Map<String, Object> defaults = new Yaml().load(this.getClass().getResourceAsStream("/configs/discordbot.yml"));
            for (String key : defaults.keySet()) {
                if (!config.containsKey(key)) config.put(key, defaults.get(key));
            }*/
            ClassLoader classLoader = getClass().getClassLoader();

            this.configFile = new File(configsDirectory, "discordbot.yml");
            configFile.createNewFile();
            config = (Map<String, Object>) loadYaml(configFile);
            if (config == null) config = new LinkedHashMap<>();
            Map<String, Object> defaults = new Yaml().load(classLoader.getResource("configs/discordbot.yml").openStream());
            for (String key : defaults.keySet()) {
                if (!config.containsKey(key)) config.put(key, defaults.get(key));
            }
            saveConfigs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveConfigs() throws IOException {
        saveYaml(config, configFile);
    }

    public void saveYaml(Object data, File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        new Yaml(dumperOptions).dump(data, writer);
        writer.close();
    }


    public Object loadYaml(File file) throws IOException {
        FileReader reader = new FileReader(file);
        Object data = new Yaml().load(reader);
        reader.close();
        return data;
    }

    public File getConfigsDirectory() {
        return configsDirectory;
    }

    public File getPluginsDirectory() {
        return pluginsDirectory;
    }
}
