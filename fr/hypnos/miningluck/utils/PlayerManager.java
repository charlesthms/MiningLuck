package fr.hypnos.miningluck.utils;


import fr.hypnos.miningluck.Main;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlayerManager {

    private Main mainInstance;

    private List<Material> netherBlocks;
    private List<String> listenedBlocks;

    public PlayerManager(Main main) {
        this.mainInstance = main;
        fillTypeBlocks();
    }

    public void addValue(Material m, Player p) {

        FileConfiguration data = ConfigManager.getData();
        FileConfiguration cfg = mainInstance.getConfig();
        listenedBlocks = cfg.getStringList("listened-blocks");

        if (listenedBlocks.contains(m.name())) {
            if (data.contains("Players." + p.getUniqueId().toString() + "." + m.name())) {
                int value = data.getInt("Players." + p.getUniqueId().toString() + "." + m.name());
                value++;
                data.set("Players." + p.getUniqueId().toString() + "." + m.name(), value);
            } else {
                data.set("Players." + p.getUniqueId().toString() + "." + m.name(), 1);
            }
        }
        if (netherBlocks.contains(m)) {
            setConfig(p, "Nether", data);
        } else {
            setConfig(p, "Overworld", data);
        }

        ConfigManager.save();
    }

    private void setConfig(Player p, String world, FileConfiguration data) {
        if (data.contains("Players." + p.getUniqueId().toString() + "." + world + "-Blocks")) {
            int value = data.getInt("Players." + p.getUniqueId().toString() + "." + world + "-Blocks");
            value++;
            data.set("Players." + p.getUniqueId().toString() + "." + world + "-Blocks", value);
        } else {
            data.set("Players." + p.getUniqueId().toString() + "." + world + "-Blocks", 1);
        }
    }

    public Double calcPercent(Material m, Player p, FileConfiguration data) {
        data = ConfigManager.getData();

        if (m.name().equals("ANCIENT_DEBRIS")) {
            return data.getDouble("Players." + p.getUniqueId().toString() + "." + m.name()) / data.getDouble("Players." + p.getUniqueId().toString() + ".Nether-Blocks") * 100;
        } else {
            return data.getDouble("Players." + p.getUniqueId().toString() + "." + m.name()) / data.getDouble("Players." + p.getUniqueId().toString() + ".Overworld-Blocks") * 100;
        }
    }

    public void onQuitManager(Player p) {
        FileConfiguration data = ConfigManager.getData();
        FileConfiguration logs = PlayerLogs.getLogsConfig();
        FileConfiguration cfg = mainInstance.getConfig();

        List<String> listenedBlocks = new ArrayList<>();

        for (String listened : cfg.getStringList("listened-blocks")) {
                listenedBlocks.add(listened.toLowerCase() + " luck: " + String.format("%.2f", mainInstance.getPlayerManager().calcPercent(Material.getMaterial(listened), p, ConfigManager.getData())) + "%");
        }

        logs.set("LastStats." + p.getName() + "." + PlayerManager.getCurrentTime(), listenedBlocks);
        PlayerLogs.save();

        // Suppression du joueur de data.yml
        data.set("Players." + p.getUniqueId().toString(), null);
        ConfigManager.save();
    }

    public static String getCurrentTime() {
        Format f = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        return f.format(new Date());
    }

    private void fillTypeBlocks() {
        netherBlocks = new ArrayList<>();

        netherBlocks.add(Material.ANCIENT_DEBRIS);
        netherBlocks.add(Material.BASALT);
        netherBlocks.add(Material.BLACKSTONE);
        netherBlocks.add(Material.CHAIN);
        netherBlocks.add(Material.GILDED_BLACKSTONE);
        netherBlocks.add(Material.GLOWSTONE);
        netherBlocks.add(Material.MAGMA_BLOCK);
        netherBlocks.add(Material.NETHER_BRICKS);
        netherBlocks.add(Material.NETHER_GOLD_ORE);
        netherBlocks.add(Material.NETHER_QUARTZ_ORE);
        netherBlocks.add(Material.NETHER_SPROUTS);
        netherBlocks.add(Material.NETHER_WART);
        netherBlocks.add(Material.NETHER_WART_BLOCK);
        netherBlocks.add(Material.NETHERRACK);
        netherBlocks.add(Material.POLISHED_BLACKSTONE);
        netherBlocks.add(Material.POLISHED_BLACKSTONE_BRICKS);
        netherBlocks.add(Material.SHROOMLIGHT);
        netherBlocks.add(Material.SOUL_SAND);
        netherBlocks.add(Material.SOUL_SOIL);
        netherBlocks.add(Material.TWISTING_VINES);
        netherBlocks.add(Material.WEEPING_VINES);
    }
}
