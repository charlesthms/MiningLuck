package fr.hypnos.miningluck.events;


import fr.hypnos.miningluck.utils.Freeze;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class FreezeEvent implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        if (Freeze.getFrozenPlayers().contains(e.getPlayer().getUniqueId())){
            e.setTo(e.getFrom());
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if (e.getEntity() instanceof Player && Freeze.getFrozenPlayers().contains(e.getEntity().getUniqueId())){
            e.setCancelled(true);
        }
    }

}
