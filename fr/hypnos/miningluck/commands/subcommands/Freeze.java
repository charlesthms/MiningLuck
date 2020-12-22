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

public class Freeze extends SubCommand {

    public static ArrayList<UUID> frozenPlayers = new ArrayList<>();

    public static List<UUID> getFrozenPlayers() {
        return frozenPlayers;
    }

    @Override
    public String getName() {
        return "freeze";
    }

    @Override
    public String getDescription() {
        return "Freeze the given player";
    }

    @Override
    public String getSyntax() {
        return "/ml freeze <player>";
    }

    @Override
    public void runCommand(CommandSender sender, String[] args) {

        if (sender.hasPermission("miningluck.admin")) {
            if (args.length == 2){
                if (Bukkit.getServer().getPlayer(args[1]) != null) {
                    // Si le joueur est bien connecté, on l'ajoute à la liste
                    Player target = Bukkit.getServer().getPlayer(args[1]);
                    getFrozenPlayers().add(target.getUniqueId());

                    target.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD.toString() + "AVERTISSEMENT: " +
                            ChatColor.DARK_RED + "Vous avez été immobilisé par un administrateur, veuillez rester connecté.");
                    target.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + " Déconnection = ban def");

                    sender.sendMessage(Messages.PREFIX.getMessage() + ChatColor.GREEN + args[1] + " est maintenant freeze.");
                } else {
                    sender.sendMessage(Messages.PREFIX.getMessage() + ChatColor.DARK_RED + "Le joueur spécifié est hors-ligne");
                }
            } else {
                sender.sendMessage(Messages.PREFIX.getMessage() + ChatColor.DARK_RED + "Erreur de syntaxe: " + getSyntax());
            }
        } else {
            sender.sendMessage(Messages.PREFIX.getMessage() + ChatColor.DARK_RED + "Erreur de syntaxe: " + getSyntax());
        }
    }

    @Override
    public List<String> getSubcommandArgs(CommandSender sender, String[] args) {
        return null;
    }
}
