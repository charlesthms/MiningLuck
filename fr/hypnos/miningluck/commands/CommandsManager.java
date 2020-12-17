package fr.hypnos.miningluck.commands;


import fr.hypnos.miningluck.Main;
import fr.hypnos.miningluck.utils.ConfigManager;
import fr.hypnos.miningluck.utils.GUIManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandsManager implements CommandExecutor, TabCompleter {

    private final Main mainInstance;
    private GUIManager guiManager;

    public CommandsManager(Main main) {
        this.mainInstance = main;
        guiManager = new GUIManager(mainInstance);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args[0].equalsIgnoreCase("gui") && args.length == 1) {
                guiManager.createGUI(player);
            } else if (args[0].equalsIgnoreCase("addBlock") && args.length == 2) {

                if (Material.getMaterial(args[1].toUpperCase()) != null) {
                    FileConfiguration cfg = mainInstance.getConfig();
                    List<String> updated = cfg.getStringList("listened-blocks");

                    updated.add(args[1].toUpperCase());

                    cfg.set("listened-blocks", updated);
                    mainInstance.saveConfig();
                } else {
                    sender.sendMessage(ChatColor.AQUA + "[" + ChatColor.DARK_GRAY + "MiningLuck" + ChatColor.AQUA + "] " + ChatColor.AQUA + "Erreur: " + args[1].toUpperCase() + " n'existe pas.");
                }

            }

        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) {

        List<String> subArgs = new ArrayList<>();

        if (args.length == 1) {
            subArgs.add("gui");
            subArgs.add("addBlock");
        }
        if (args.length == 2){
            subArgs.addAll(Arrays.stream(Material.values()).map(m->m.name().toLowerCase()).collect(Collectors.toList()));
        }

        return subArgs;
    }
}
