package fr.hypnos.miningluck.utils;


import fr.hypnos.miningluck.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class GUIManager {

    private Main mainInstance;
    private Inventory menu;
    private List<String> lore;

    public GUIManager(Main main) {
        this.mainInstance = main;
        this.menu = Bukkit.createInventory(null, 54, ChatColor.DARK_GRAY + "[" + ChatColor.YELLOW + "MiningLuck" + ChatColor.DARK_GRAY + "]");
        this.lore = new ArrayList<>();
    }

    public void createGUI(Player player) {

        menu.clear();

        // Placement des têtes
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            lore = new ArrayList<>();
            ItemStack head = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta headMeta = (SkullMeta) head.getItemMeta();

            headMeta.setOwningPlayer(p);
            headMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&5" + p.getName()));

            FileConfiguration cfg = mainInstance.getConfig();

            for (String listened : cfg.getStringList("listened-blocks")) {
                lore.add(ChatColor.AQUA + listened.toLowerCase() + " luck: " + String.format("%.2f", mainInstance.getPlayerManager().calcPercent(Material.getMaterial(listened), p, ConfigManager.getData())) + "%");
            }


            headMeta.setLore(lore);
            head.setItemMeta(headMeta);
            menu.addItem(head);
        }
        player.openInventory(menu);
    }
}
