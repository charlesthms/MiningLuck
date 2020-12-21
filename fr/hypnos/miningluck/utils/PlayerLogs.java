package fr.hypnos.miningluck.utils;


import fr.hypnos.miningluck.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

public class PlayerLogs {

    public static File logs;
    public static FileConfiguration logsConfig;

    private Main main;

    public PlayerLogs(Main main) {
        this.main = main;
    }

    public void logsInit() {

        File folder = new File(main.getDataFolder(), "Local");
        if (!folder.exists()) {
            folder.mkdir();
        }

        logs = new File(String.valueOf(main.getDataFolder().toPath().resolve("Local")), "logs.yml");

        if (!logs.exists()) {
            try {
                logs.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        logsConfig = YamlConfiguration.loadConfiguration(logs);

        logsConfig.options().header("-----------------------------------------------------------\n" +
                "\nIn this file you can see all logs from player disconnection\n" +
                "You can remove logs over time whenever you want\n"
                + "\n-----------------------------------------------------------\n");
        save();
    }

    public static void save() {
        try {
            logsConfig.save(logs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FileConfiguration getLogsConfig() {
        return logsConfig;
    }
}
