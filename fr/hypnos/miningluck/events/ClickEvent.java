package fr.hypnos.miningluck.events;


import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ClickEvent implements Listener {

    @EventHandler
    public void clickEvent (InventoryClickEvent e){
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "[" + ChatColor.YELLOW + "MiningLuck" + ChatColor.DARK_GRAY + "]")){
            e.setCancelled(true);
        }
    }
}
