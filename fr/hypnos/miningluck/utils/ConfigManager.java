package fr.hypnos.miningluck.utils;


import fr.hypnos.miningluck.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigManager {

    private static FileConfiguration data;
    private static FileConfiguration lang;
    private static File dataFile;
    private static File langFile;

    private Main main;

    public ConfigManager(Main main) {
        this.main = main;
    }

    // Custom config
    public void create()
    {
        File folder = new File(main.getDataFolder(),"Local");

        if (!folder.exists()){
            folder.mkdir();
        }

        dataFile = new File(String.valueOf(main.getDataFolder().toPath().resolve("Local")), "data.yml");

        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        data = YamlConfiguration.loadConfiguration(dataFile);
    }

    public static FileConfiguration getData(){
        return data;
    }

    public static void save(){
        try {
            data.save(dataFile);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void reload() {
        data = YamlConfiguration.loadConfiguration(dataFile);
    }

    // Regular config
    public void loadConfig(){

        FileConfiguration cfg = main.getConfig();
        List<String> defaultBlocks = new ArrayList<>();
        defaultBlocks.add("DIAMOND_ORE");

        // Ajout des valeures initiales
        cfg.addDefault("listened-blocks", defaultBlocks);

        // Mise en place du header
        cfg.options().header("\n" +
                "        _       _               __            _    \n" +
                "  /\\/\\ (_)_ __ (_)_ __   __ _  / / _   _  ___| | __\n" +
                " /    \\| | '_ \\| | '_ \\ / _` |/ / | | | |/ __| |/ /\n" +
                "/ /\\/\\ \\ | | | | | | | | (_| / /__| |_| | (__|   < \n" +
                "\\/    \\/_|_| |_|_|_| |_|\\__, \\____/\\__,_|\\___|_|\\_\\\n" +
                "                        |___/                      \n" +
                "\n" +
                "Current version: " + main.getDescription().getVersion() + "\n"
        );
        cfg.options().copyHeader(true);

        // Sauvegarde de la config
        cfg.options().copyDefaults(true);
        main.saveConfig();
    }

    public void loadingSuccess(Boolean pluginStatus) {
        if (pluginStatus){
            System.out.println(
                    "\n" +
                            ChatColor.AQUA + "_  _ " + ChatColor.DARK_AQUA + "_    \n" +
                            ChatColor.AQUA + "|\\/| " + ChatColor.DARK_AQUA + "|      " + ChatColor.AQUA + "MiningLuck Enabled\n" +
                            ChatColor.AQUA + "|  | " + ChatColor.DARK_AQUA + "|___   " + ChatColor.DARK_GRAY + "Version " + main.getDescription().getVersion() + "\n" +
                            "          \n"
            );
        }
    }

}
