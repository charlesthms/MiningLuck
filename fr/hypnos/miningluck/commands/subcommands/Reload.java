package fr.hypnos.miningluck.commands.subcommands;


import fr.hypnos.miningluck.Main;
import fr.hypnos.miningluck.commands.SubCommand;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Reload extends SubCommand {

    private final Main main;

    public Reload(Main main){
        this.main = main;
    }

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reload the configuration";
    }

    @Override
    public String getSyntax() {
        return "/ml reload";
    }

    @Override
    public void runCommand(CommandSender sender, String[] args) {
        if (sender.hasPermission("miningluck.admin")){
            main.reloadConfig();
        }
    }

    @Override
    public List<String> getSubcommandArgs(CommandSender sender, String[] args) {
        return null;
    }
}
