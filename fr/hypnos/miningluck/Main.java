package fr.hypnos.miningluck;


import fr.hypnos.miningluck.commands.CommandManager;
import fr.hypnos.miningluck.events.BlockBreak;
import fr.hypnos.miningluck.events.ClickEvent;
import fr.hypnos.miningluck.events.FreezeEvent;
import fr.hypnos.miningluck.events.PlayerQuit;
import fr.hypnos.miningluck.utils.ConfigManager;
import fr.hypnos.miningluck.utils.PlayerLogs;
import fr.hypnos.miningluck.utils.PlayerManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private PlayerManager playerManager = new PlayerManager(this);
    private ConfigManager configManager = new ConfigManager(this);
    private PlayerLogs playerLogs = new PlayerLogs(this);

    @Override
    public void onEnable() {

        getCommand("miningluck").setExecutor(new CommandManager(this));

        getServer().getPluginManager().registerEvents(new ClickEvent(), this);
        getServer().getPluginManager().registerEvents(new BlockBreak(playerManager, this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(this), this);
        getServer().getPluginManager().registerEvents(new FreezeEvent(this), this);

        playerLogs.logsInit();

        configManager.loadConfig();
        configManager.create();
        configManager.loadingSuccess(getServer().getPluginManager().isPluginEnabled(this));
    }

    // Getters
    public PlayerManager getPlayerManager() {
        return playerManager;
    }
}
