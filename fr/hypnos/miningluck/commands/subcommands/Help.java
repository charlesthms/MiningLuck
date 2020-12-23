package fr.hypnos.miningluck.commands.subcommands;

import fr.hypnos.miningluck.commands.CommandManager;
import fr.hypnos.miningluck.commands.SubCommand;
import fr.hypnos.miningluck.utils.Messages;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Help extends SubCommand {

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Display the help center";
    }

    @Override
    public String getSyntax() {
        return "/ml help";
    }

    @Override
    public void runCommand(CommandSender sender, String[] args) {
        sender.sendMessage(ChatColor.GOLD + "-------------- " + Messages.PREFIX.getMessage() + "--------------");
        sender.sendMessage("");

        // Display all the commands syntax + description
        for (SubCommand subCommand : CommandManager.getSubCommands()) {
            //sender.sendMessage(ChatColor.AQUA + subCommand.getSyntax() + " - " + subCommand.getDescription());
            sender.spigot().sendMessage(new ComponentBuilder(subCommand.getSyntax())
                    .color(net.md_5.bungee.api.ChatColor.AQUA)
                    .event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, subCommand.getSyntax()))
                    .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(String.join("\n", subCommand.getDescription()))))
                    .create());
        }
        sender.sendMessage("");
        sender.sendMessage(ChatColor.GOLD+"---------------------------------------");
    }

    @Override
    public List<String> getSubcommandArgs(CommandSender sender, String[] args) {
        return null;
    }
}
