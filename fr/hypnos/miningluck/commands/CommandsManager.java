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
        if (sender instanceof Player && args.length > 0) {

            Player player = (Player) sender;
            FileConfiguration cfg = mainInstance.getConfig();

            if (args[0].equalsIgnoreCase("gui") && args.length == 1) {
                guiManager.createGUI(player);
            } else if (args[0].equalsIgnoreCase("listen") && args.length == 2) {

                if (Material.getMaterial(args[1].toUpperCase()) != null) {
                    List<String> updated = cfg.getStringList("listened-blocks");

                    if (updated.contains(args[1].toUpperCase())) {
                        sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.DARK_GRAY + "MiningLuck" + ChatColor.GOLD + "] " + ChatColor.DARK_RED + "Erreur: " + args[1].toUpperCase() + " est déjà un block écouté.");
                    } else {
                        updated.add(args[1].toUpperCase());
                        cfg.set("listened-blocks", updated);
                        mainInstance.saveConfig();

                        sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.DARK_GRAY + "MiningLuck" + ChatColor.GOLD + "] " + ChatColor.GREEN + args[1].toUpperCase() + " ajouté aux blocks écoutés");
                    }

                } else {
                    sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.DARK_GRAY + "MiningLuck" + ChatColor.GOLD + "] " + ChatColor.DARK_RED + "Erreur: " + args[1].toUpperCase() + " n'existe pas.");
                }

            } else if (args[0].equalsIgnoreCase("unlisten")) {
                List<String> updated = cfg.getStringList("listened-blocks");

                if (!updated.contains(args[1].toUpperCase())) {
                    sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.DARK_GRAY + "MiningLuck" + ChatColor.GOLD + "] " + ChatColor.DARK_RED + "Erreur: " + args[1].toUpperCase() + " n'est déjà pas un block écouté.");
                } else {
                    updated.remove(args[1].toUpperCase());
                    cfg.set("listened-blocks", updated);
                    mainInstance.saveConfig();

                    FileConfiguration data = ConfigManager.get();
                    ConfigManager.get().set("Players." + player.getUniqueId().toString() + "." + args[1].toUpperCase(), null);
                    ConfigManager.save();

                    sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.DARK_GRAY + "MiningLuck" + ChatColor.GOLD + "] " + ChatColor.GREEN + args[1].toUpperCase() + " retiré des blocks écoutés");
                }
            } else if (args[0].equalsIgnoreCase("list")) {
                List<String> list = cfg.getStringList("listened-blocks");

                for (String value : list) {
                    sender.sendMessage(value);
                }
            } else if (args[0].equalsIgnoreCase("resetPlayerData")){
                ConfigManager.get().set("Players", null);
                ConfigManager.save();
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) {

        List<String> subArgs = new ArrayList<>();

        if (args.length == 1) {
            subArgs.add("gui");
            subArgs.add("listen");
            subArgs.add("unlisten");
            subArgs.add("list");
            subArgs.add("resetPlayerData");
        }
        if (args.length == 2) {
            subArgs.addAll(Arrays.stream(Material.values()).map(m -> m.name().toLowerCase()).collect(Collectors.toList()));
        }
        if (args[0].equalsIgnoreCase("unlisten")) {
            FileConfiguration cfg = mainInstance.getConfig();
            return cfg.getStringList("listened-blocks");
        }

        return subArgs;
    }
}
