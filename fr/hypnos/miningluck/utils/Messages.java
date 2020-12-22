package fr.hypnos.miningluck.utils;

import org.bukkit.ChatColor;

public enum Messages {

    PREFIX(ChatColor.GOLD + "[" + ChatColor.DARK_GRAY + "MiningLuck" + ChatColor.GOLD + "] "),
    NO_PERM(ChatColor.DARK_RED + "Vous n'avez pas la permission de faire cela."),
    ;

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
