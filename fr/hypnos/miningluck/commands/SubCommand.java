package fr.hypnos.miningluck.commands;

import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class SubCommand {

    public abstract String getName();

    public abstract String getDescription();

    public abstract String getSyntax();

    public abstract void runCommand(CommandSender sender, String[] args);

    public abstract List<String> getSubcommandArgs(CommandSender sender, String[] args);

}
