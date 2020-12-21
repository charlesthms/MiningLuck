package fr.hypnos.miningluck.events;


import fr.hypnos.miningluck.Main;
import fr.hypnos.miningluck.utils.Freeze;
import fr.hypnos.miningluck.utils.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    private PlayerManager playerManagerInstance;
    private Main main;

    public BlockBreak(PlayerManager playerManager, Main main) {
        this.playerManagerInstance = playerManager;
        this.main = main;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (!main.getConfig().getBoolean("allow-block-break")) {
            if (Freeze.getFrozenPlayers().contains(e.getPlayer())) {
                e.setCancelled(true);
            }
        }
        playerManagerInstance.addValue(e.getBlock().getType(), e.getPlayer());
    }
}
