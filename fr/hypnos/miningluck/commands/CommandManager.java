package fr.hypnos.miningluck.commands;


import fr.hypnos.miningluck.Main;
import fr.hypnos.miningluck.commands.subcommands.*;
import fr.hypnos.miningluck.utils.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor, TabCompleter {

    private ArrayList<SubCommand> subCommands = new ArrayList<>();
    private ArrayList<String> subArgs = new ArrayList<>();

    private final Main plugin;

    public CommandManager(Main mainInstance) {
        this.plugin = mainInstance;
        subCommands.add(new GUI(plugin));
        subCommands.add(new Listen(plugin));
        subCommands.add(new Unlisten(plugin));
        subCommands.add(new Freeze());
        subCommands.add(new Unfreeze());
        subCommands.add(new ListListened(plugin));
        subCommands.add(new Reload(plugin));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String name, String[] args) {

        if (sender instanceof Player) {
            if (args.length > 0) {

                for (SubCommand subCommand : subCommands) {
                    if (args[0].equalsIgnoreCase(subCommand.getName())) {
                        subCommand.runCommand(sender, args);
                    }
                }
            } else {
                sender.sendMessage(ChatColor.GOLD + "---------- " + Messages.PREFIX.getMessage() + "----------");

                // Display all the commands syntax + description
                for (SubCommand subCommand : subCommands) {
                    sender.sendMessage(ChatColor.AQUA + subCommand.getSyntax() + " - " + subCommand.getDescription());
                }
                sender.sendMessage("-----------------------------");
            }
        }
        return true;
    }

    @Override
    public java.util.List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        for (SubCommand subCommand : subCommands) {
            subArgs.add(subCommand.getName());
        }

        if (sender.hasPermission("miningluck.admin")) {

            if (args.length == 1) {
                return subArgs;
            } else if (args.length >= 2) {
                for (SubCommand subCommand : subCommands) {
                    if (args[0].equalsIgnoreCase(subCommand.getName())) {
                        return subCommand.getSubcommandArgs(sender, args);
                    }
                }
            }
        }
        return null;
    }
}

