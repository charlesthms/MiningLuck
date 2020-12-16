package fr.hypnos.miningluck.utils;

import fr.hypnos.miningluck.Main;
import fr.hypnos.miningluck.events.BlockBreak;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GUIManager {

    private Main mainInstance;
    private BlockBreak blockBreak;
    private Inventory menu;
    private List<String> lore;

    public GUIManager(Main main) {
        this.mainInstance = main;
        this.menu = Bukkit.createInventory(null, 54, ChatColor.DARK_GRAY + "[" + ChatColor.YELLOW + "MiningLuck" + ChatColor.DARK_GRAY + "]");
        this.lore = new ArrayList<>();
        this.blockBreak = new BlockBreak(mainInstance.getPlayerManager());
    }

    public void createGUI(Player player) {

        menu.clear();

        // Placement des têtes
        for (Map.Entry<Double, Player> playerPercent : mainInstance.getPlayerManager().getPlayersPercent().entrySet()) {
            lore = new ArrayList<>();
            ItemStack head = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta headMeta = (SkullMeta) head.getItemMeta();

            headMeta.setOwningPlayer(playerPercent.getValue());
            headMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&5" + playerPercent.getValue().getName()));

            // TODO Ajouter le lore -> stats
            lore.add(ChatColor.AQUA + "Diamond luck: " + String.format("%.2f", mainInstance.getPlayerManager().calcPercent(mainInstance.getPlayerManager().getGlobalStats(), "DIAMOND_ORE", playerPercent.getValue())) + "%");

            headMeta.setLore(lore);
            head.setItemMeta(headMeta);
            menu.addItem(head);
        }
        player.openInventory(menu);
    }
}
