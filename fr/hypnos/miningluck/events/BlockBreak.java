package fr.hypnos.miningluck.events;


import fr.hypnos.miningluck.utils.Freeze;
import fr.hypnos.miningluck.utils.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    private PlayerManager playerManagerInstance;
    public BlockBreak(PlayerManager playerManager){
        this.playerManagerInstance = playerManager;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        if (Freeze.getFrozenPlayers().contains(e.getPlayer())){
            e.setCancelled(true);
        }

        playerManagerInstance.addValue(e.getBlock().getType(), e.getPlayer());
    }
}
