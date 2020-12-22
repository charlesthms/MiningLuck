package fr.hypnos.miningluck.events;

import fr.hypnos.miningluck.Main;
import fr.hypnos.miningluck.commands.subcommands.Freeze;
import org.bukkit.BanList;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    private Main mainInstance;

    private final String banMessage = ChatColor.DARK_RED + "Déconnection suite à immobilisation.";

    public PlayerQuit(Main main){
        this.mainInstance = main;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        mainInstance.getPlayerManager().onQuitManager(e.getPlayer());

        if (Freeze.getFrozenPlayers().contains(e.getPlayer().getUniqueId())){
            mainInstance.getServer().getBanList(BanList.Type.NAME).addBan(e.getPlayer().getName(), banMessage, null, null);
            mainInstance.getServer().getBanList(BanList.Type.IP).addBan(e.getPlayer().getName(), banMessage, null, null);
        }
    }
}
