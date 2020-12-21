package fr.hypnos.miningluck.events;


import fr.hypnos.miningluck.Main;
import fr.hypnos.miningluck.utils.Freeze;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class FreezeEvent implements Listener {

    private Main main;

    public FreezeEvent(Main main){
        this.main = main;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        if (Freeze.getFrozenPlayers().contains(e.getPlayer().getUniqueId())){
            e.setTo(e.getFrom());
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        if (!main.getConfig().getBoolean("allow-block-place")){
            if (Freeze.getFrozenPlayers().contains(e.getPlayer().getUniqueId())){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if (!main.getConfig().getBoolean("freeze-settings.allow-damage")){
            if (e.getEntity() instanceof Player && Freeze.getFrozenPlayers().contains(e.getEntity().getUniqueId())){
                e.setCancelled(true);
            }
        }
    }

}
