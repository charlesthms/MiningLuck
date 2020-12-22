package fr.hypnos.miningluck.commands.subcommands;

import fr.hypnos.miningluck.Main;
import fr.hypnos.miningluck.commands.SubCommand;
import fr.hypnos.miningluck.utils.Messages;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Listen extends SubCommand {

    private final Main main;

    public Listen(Main main) {
        this.main = main;
    }

    @Override
    public String getName() {
        return "listen";
    }

    @Override
    public String getDescription() {
        return "Add a block to be listened";
    }

    @Override
    public String getSyntax() {
        return "/ml listen <block>";
    }

    @Override
    public void runCommand(CommandSender sender, String[] args) {

        Player player = (Player) sender;
        FileConfiguration cfg = main.getConfig();

        if (player.hasPermission("miningluck.admin")) {
            if (args.length == 2) {
                if (Material.getMaterial(args[1].toUpperCase()) != null) {
                    List<String> updated = cfg.getStringList("listened-blocks");

                    if (updated.contains(args[1].toUpperCase())) {
                        player.sendMessage(Messages.PREFIX.getMessage() + ChatColor.DARK_RED + "Erreur: " + args[1].toUpperCase() + " est déjà un block écouté.");
                    } else {
                        updated.add(args[1].toUpperCase());
                        cfg.set("listened-blocks", updated);
                        main.saveConfig();

                        player.sendMessage(Messages.PREFIX.getMessage() + ChatColor.GREEN + args[1].toUpperCase() + " ajouté aux blocks écoutés");
                    }

                } else {
                    player.sendMessage(Messages.PREFIX.getMessage() + ChatColor.DARK_RED + "Erreur: " + args[1].toUpperCase() + " n'existe pas.");
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
        return new ArrayList<>(Arrays.stream(Material.values()).map(m -> m.name().toLowerCase()).collect(Collectors.toList()));
    }
}
