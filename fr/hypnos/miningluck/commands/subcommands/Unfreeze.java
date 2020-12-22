package fr.hypnos.miningluck.commands.subcommands;

import fr.hypnos.miningluck.commands.SubCommand;
import fr.hypnos.miningluck.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Unfreeze extends SubCommand {

    @Override
    public String getName() {
        return "unfreeze";
    }

    @Override
    public String getDescription() {
        return "Unfreeze the specified player";
    }

    @Override
    public String getSyntax() {
        return "/ml unfreeze <player>";
    }

    @Override
    public void runCommand(CommandSender sender, String[] args) {

        if (sender.hasPermission("miningluck.admin")) {
            if (args.length == 2) {
                if (Bukkit.getServer().getPlayer(args[1]) != null) {
                    if ((Freeze.getFrozenPlayers()).contains(Bukkit.getServer().getPlayer(args[1]).getUniqueId())) {
                        Player target = Bukkit.getServer().getPlayer(args[1]);
                        Freeze.getFrozenPlayers().remove(target.getUniqueId());

                        target.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD.toString() + "AVERTISSEMENT: " + ChatColor.GREEN + "Vous pouvez de nouveau bouger.");
                        sender.sendMessage(Messages.PREFIX.getMessage() + ChatColor.GREEN + args[1] + " est maintenant unfreeze.");
                    }
                } else {
                    sender.sendMessage(Messages.PREFIX.getMessage() + ChatColor.DARK_RED + "Le joueur spécifié est hors-ligne");
                }
            } else {
                sender.sendMessage(Messages.PREFIX.getMessage() + ChatColor.DARK_RED + "Erreur de syntaxe: " + getSyntax());
            }
        } else {
            sender.sendMessage(Messages.PREFIX.getMessage() + ChatColor.DARK_RED + Messages.NO_PERM.getMessage());
        }
    }

    @Override
    public List<String> getSubcommandArgs(CommandSender sender, String[] args) {
        List<String> frozenPlayers = new ArrayList<>();
        for (UUID uuid : Freeze.getFrozenPlayers()) {
            frozenPlayers.add(Bukkit.getServer().getPlayer(uuid).getName());
        }
        return frozenPlayers;
    }
}
