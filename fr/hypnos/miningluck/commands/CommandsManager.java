package fr.hypnos.miningluck.commands;


import fr.hypnos.miningluck.Main;
import fr.hypnos.miningluck.utils.GUIManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandsManager implements CommandExecutor, TabCompleter {

    private final Main mainInstance;
    private GUIManager guiManager;

    private final ArrayList<String> subArgs = new ArrayList<>();
    private String[] checkedMaterials = {
            "DIAMOND_ORE",
            "GOLD_ORE",
            "EMERALD_ORE",
            "ANCIENT_DEBRIS"
    };

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
            }

        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) {

        subArgs.add("gui");

        if (args.length == 1) {
            // If argument exist then return the suggestions
            return subArgs;
        }
        return null;
    }
}
