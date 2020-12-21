package fr.hypnos.miningluck.events;

import fr.hypnos.miningluck.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    private Main mainInstance;

    public PlayerQuit(Main main){
        this.mainInstance = main;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        mainInstance.getPlayerManager().onQuitManager(e.getPlayer());
    }


}
