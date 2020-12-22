package fr.hypnos.miningluck.commands.subcommands;

import fr.hypnos.miningluck.Main;
import fr.hypnos.miningluck.commands.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ListListened extends SubCommand {

    private final Main main;

    public ListListened(Main main){
        this.main = main;
    }

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getDescription() {
        return "Display all listened blocks";
    }

    @Override
    public String getSyntax() {
        return "/ml list";
    }

    @Override
    public void runCommand(CommandSender sender, String[] args) {

        if (sender.hasPermission("miningluck.admin")){
            FileConfiguration cfg = main.getConfig();
            List<String> list = cfg.getStringList("listened-blocks");

            for (String value : list) {
                sender.sendMessage(value);
            }
        }
    }

    @Override
    public List<String> getSubcommandArgs(CommandSender sender, String[] args) {
        return null;
    }
}
