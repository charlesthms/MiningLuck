package fr.hypnos.miningluck.utils;

import fr.hypnos.miningluck.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private File file;
    private FileConfiguration customFile;
    private Main main;

    public ConfigManager(Main main) {
        this.main = main;
    }

    public void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("MiningLuck").getDataFolder(), "custom.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
        customFile.options().header("\n" +
                "        _       _               __            _    \n" +
                "  /\\/\\ (_)_ __ (_)_ __   __ _  / / _   _  ___| | __\n" +
                " /    \\| | '_ \\| | '_ \\ / _` |/ / | | | |/ __| |/ /\n" +
                "/ /\\/\\ \\ | | | | | | | | (_| / /__| |_| | (__|   < \n" +
                "\\/    \\/_|_| |_|_|_| |_|\\__, \\____/\\__,_|\\___|_|\\_\\\n" +
                "                        |___/                      \n" +
                "\n" +
                "Current version: " + main.getDescription().getVersion() + "\n"
        );
    }

    public void saveConfig() {
        try {
            customFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
