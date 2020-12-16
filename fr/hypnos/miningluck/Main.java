package fr.hypnos.miningluck;

import fr.hypnos.miningluck.commands.CommandsManager;
import fr.hypnos.miningluck.events.BlockBreak;
import fr.hypnos.miningluck.events.ClickEvent;
import fr.hypnos.miningluck.events.PlayerQuit;
import fr.hypnos.miningluck.utils.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private PlayerManager playerManager = new PlayerManager(this);
    private ConfigManager configManager = new ConfigManager(this);

    @Override
    public void onEnable() {
        configManager.setup();
        configManager.saveConfig();

        getCommand("luck").setExecutor(new CommandsManager(this));
        getCommand("luck").setTabCompleter(new CommandsManager(this));

        getServer().getPluginManager().registerEvents(new ClickEvent(), this);
        getServer().getPluginManager().registerEvents(new BlockBreak(playerManager), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(this), this);
    }


    // Getters
    public PlayerManager getPlayerManager() {
        return playerManager;
    }
}
