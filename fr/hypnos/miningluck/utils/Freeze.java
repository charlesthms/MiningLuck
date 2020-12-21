package fr.hypnos.miningluck.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Freeze {

    public static ArrayList<UUID> frozenPlayers = new ArrayList<>();

    public static List<UUID> getFrozenPlayers(){
        return frozenPlayers;
    }

}
