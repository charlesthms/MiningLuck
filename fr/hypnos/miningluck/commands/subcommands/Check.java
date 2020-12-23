package fr.hypnos.miningluck.commands.subcommands;

import fr.hypnos.miningluck.Main;
import fr.hypnos.miningluck.commands.SubCommand;
import fr.hypnos.miningluck.utils.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class Check extends SubCommand {

    private final Main main;

    public Check(Main main) {
        this.main = main;
    }

    @Override
    public String getName() {
        return "check";
    }

    @Override
    public String getDescription() {
        return "Get the current luck of the specified player";
    }

    @Override
    public String getSyntax() {
        return "/ml check <player>";
    }

    @Override
    public void runCommand(CommandSender sender, String[] args) {
        FileConfiguration cfg = main.getConfig();
        FileConfiguration data = ConfigManager.getData();
        List<String> listened = cfg.getStringList("listened-blocks");

        if (sender.hasPermission("miningluck.admin")) {
            if (args.length == 2) {
                if (Bukkit.getServer().getPlayer(args[1]) != null) {
                    sender.sendMessage(ChatColor.DARK_GRAY + "============ [ " + ChatColor.GOLD + args[1] +" Luck"+ ChatColor.DARK_GRAY + " ] ============");
                    sender.sendMessage("");
                    for (String block : listened) {
                        sender.sendMessage(ChatColor.AQUA.toString() + block.toLowerCase() + ": " + String.format("%.2f", main.getPlayerManager().calcPercent(
                                Material.getMaterial(block),
                                (Player) sender,
                                ConfigManager.getData()
                        )) + "%");
                    }
                }
            }
        }

    }

    @Override
    public List<String> getSubcommandArgs(CommandSender sender, String[] args) {
        return null;
    }
}
