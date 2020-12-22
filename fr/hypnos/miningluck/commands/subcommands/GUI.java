package fr.hypnos.miningluck.commands.subcommands;

import fr.hypnos.miningluck.Main;
import fr.hypnos.miningluck.commands.SubCommand;
import fr.hypnos.miningluck.utils.GUIManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class GUI extends SubCommand {

    private final Main mainInstance;
    private GUIManager guiManager;


    public GUI(Main main) {
        this.mainInstance = main;
        guiManager = new GUIManager(mainInstance);
    }


    @Override
    public String getName() {
        return "gui";
    }

    @Override
    public String getDescription() {
        return "Open the GUI";
    }

    @Override
    public String getSyntax() {
        return "/ml gui";
    }

    @Override
    public void runCommand(CommandSender sender, String[] args) {
        if (args.length == 1){
            guiManager.createGUI((Player) sender);
        }
    }

    @Override
    public List<String> getSubcommandArgs(CommandSender sender, String[] args) {
        return null;
    }
}
