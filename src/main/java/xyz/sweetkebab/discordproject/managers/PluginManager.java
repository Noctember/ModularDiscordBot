package xyz.sweetkebab.discordproject.managers;

import xyz.sweetkebab.discordproject.utils.Assert;
import xyz.sweetkebab.discordproject.BilBerry;
import xyz.sweetkebab.discordproject.plugins.DiscordPlugin;
import xyz.sweetkebab.discordproject.plugins.PluginClassLoader;
import xyz.sweetkebab.discordproject.plugins.PluginDescription;
import xyz.sweetkebab.discordproject.plugins.PluginLoadException;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * This file is a part of BerryGames, located on net.berrygames.bilberry.plugins
 * <p>
 * Copyright (c) BerryGames https://berrygames.net/ - All rights reserved
 * <p>
 *
 * @author SweetKebab_ {@literal <sweetkebab@berrygames.net>}
 * Created the 2018-10-26 at 17:46.
 */
public class PluginManager {

    private LinkedHashMap<PluginDescription, DiscordPlugin> plugins;

    public void loadPlugins() {
        BilBerry.getInstance().info("Loading plugins");
        Collection<PluginDescription> pluginDescriptions = new HashSet<>();
        for(File file : BilBerry.getInstance().getFileManager().getPluginsDirectory().listFiles()) {
            if(file.isDirectory()) continue;
            if(!file.getName().endsWith(".jar")) continue;
            try {
                PluginDescription plugin = loadPlugin(file);
                pluginDescriptions.add(plugin);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        loadPlugins(pluginDescriptions);
    }

    private void loadPlugins(Collection<PluginDescription> pluginDescriptions) {
        plugins = new LinkedHashMap<>();
        Map<String, PluginDescription> descriptionsByName = new HashMap<>();
        for (PluginDescription description : pluginDescriptions) {
            descriptionsByName.put(description.getName(), description);
        }
        Collection<PluginDescription> order = new LinkedHashSet<>();
        for (PluginDescription plugin : pluginDescriptions) {
            try {
                load(plugin, order, descriptionsByName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for(PluginDescription plugin : order) {
            try {
                URLClassLoader classLoader = new PluginClassLoader(new URL[]{plugin.getFile().toURI().toURL()});
                Class<?> main = classLoader.loadClass(plugin.getMainClass());
                if (!DiscordPlugin.class.isAssignableFrom(main))
                    throw new PluginLoadException("Main class doesn't extend DiscordPlugin");
                DiscordPlugin mainInstance = (DiscordPlugin) main.getDeclaredConstructor().newInstance();
                try {
                    setField(mainInstance, "api", BilBerry.getInstance().getAPI());

                    mainInstance.onLoad();
                    BilBerry.getInstance().info("Loaded plugin " + plugin.getName() + " version " + plugin.getVersion() + " by " + plugin.getAuthor() + ".");

                    plugins.put(plugin, mainInstance);
                    plugin.setPlugin(mainInstance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void load(PluginDescription plugin, Collection<PluginDescription> order, Map<String, PluginDescription> descriptionsByName) {
        order.add(plugin);
    }

    public PluginDescription loadPlugin(File file) throws IOException, PluginLoadException {
        JarFile jar = new JarFile(file);
        JarEntry entry = jar.getJarEntry("plugin.yml");
        if (entry == null) {
            throw new PluginLoadException("Jar does not contain plugin.yml");
        }
        InputStream inputStream = jar.getInputStream(entry);
        Map<String, Object> properties = new Yaml().load(inputStream);
        return construct(properties, file);
    }

    private PluginDescription construct(Map<String, Object> properties, File file) throws PluginLoadException {
        String name = null;
        String author = null;
        String version = null;
        String main = null;
        try {
            name = (String) properties.get("name");
            Assert.notNull(name);
        } catch (Exception e) {
            throw new PluginLoadException("Could not parse plugin's name");
        }
        try {
            author = (String) properties.get("author");
            Assert.notNull(author);
        } catch (Exception e) {
            throw new PluginLoadException("Could not parse plugin's author");
        }
        try {
            version = "" + properties.get("version");
            Assert.notNull(version);
        } catch (Exception e) {
            throw new PluginLoadException("Could not parse plugin's version");
        }
        try {
            main = (String) properties.get("main");
            Assert.notNull(main);
        } catch (Exception e) {
            throw new PluginLoadException("Could not parse plugin's main class");
        }
        return new PluginDescription(name, author, version, main, file);
    }

    public static boolean setField(Object targetObject, String fieldName, Object fieldValue) {
        Field field;
        try {
            field = targetObject.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            field = null;
        }
        Class superClass = targetObject.getClass().getSuperclass();
        while (field == null && superClass != null) {
            try {
                field = superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                superClass = superClass.getSuperclass();
            }
        }
        if (field == null) {
            return false;
        }
        field.setAccessible(true);
        try {
            field.set(targetObject, fieldValue);
            return true;
        } catch (IllegalAccessException e) {
            return false;
        }
    }

    public void unloadPlugins() {
        plugins.forEach(((pluginDescription, discordPlugin) -> {
            discordPlugin.onUnload();
            BilBerry.getInstance().info("Unloaded " + pluginDescription.getName());
        }));
    }
}
