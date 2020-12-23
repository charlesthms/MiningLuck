package fr.hypnos.miningluck.commands.subcommands;

import fr.hypnos.miningluck.Main;
import fr.hypnos.miningluck.commands.SubCommand;
import fr.hypnos.miningluck.utils.ConfigManager;
import fr.hypnos.miningluck.utils.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class Unlisten extends SubCommand {

    private final Main main;

    public Unlisten(Main main) {
        this.main = main;
    }

    @Override
    public String getName() {
        return "unlisten";
    }

    @Override
    public String getDescription() {
        return "Remove listened block";
    }

    @Override
    public String getSyntax() {
        return "/ml unlisten <block>";
    }

    @Override
    public void runCommand(CommandSender sender, String[] args) {

        Player player = (Player) sender;
        FileConfiguration cfg = main.getConfig();
        List<String> updated = cfg.getStringList("listened-blocks");

        if (player.hasPermission("miningluck.admin")) {
            if (args.length == 2) {
                if (!updated.contains(args[1].toUpperCase())) {
                    sender.sendMessage(Messages.PREFIX.getMessage() + ChatColor.DARK_RED + "Erreur: " + args[1].toUpperCase() + " n'est déjà pas un block écouté.");
                } else {
                    updated.remove(args[1].toUpperCase());
                    cfg.set("listened-blocks", updated);
                    main.saveConfig();

                    ConfigManager.getData().set("Players." + player.getUniqueId().toString() + "." + args[1].toUpperCase(), null);
                    ConfigManager.save();

                    sender.sendMessage(Messages.PREFIX.getMessage() + ChatColor.GREEN + args[1].toUpperCase() + " retiré des blocks écoutés");
                }
            } else {
                player.sendMessage(Messages.PREFIX.getMessage() + ChatColor.DARK_RED + "Erreur de syntaxe: " + getSyntax());
            }
        } else {
            player.sendMessage(Messages.PREFIX.getMessage() + ChatColor.DARK_RED + Messages.NO_PERM.getMessage());
        }

    }

    @Override
    public List<String> getSubcommandArgs(CommandSender sender, String[] args) {
        return main.getConfig().getStringList("listened-blocks");
    }
}
