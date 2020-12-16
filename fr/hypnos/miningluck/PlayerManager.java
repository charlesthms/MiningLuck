package fr.hypnos.miningluck;


import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerManager {

    private HashMap<Player, HashMap<String, Integer>> globalStats = new HashMap<>();
    private Map<Double, Player> playersPercent = new TreeMap<>(Collections.reverseOrder());
    private Main mainInstance;

    public PlayerManager(Main main) {
        this.mainInstance = main;
    }

    /**
     * Ajoute chaque block miné dans la HashMap du joueur et si le block miné fait parti des blocks présents dans la
     * config, alors celui-ci est ajouté indépendamment dans la HashMap.
     *
     * @param m Block miné
     * @param p Joueur concerné
     */
    public void addValue(Material m, Player p) {

        HashMap<String, Integer> playerMinedBlocks;
        if (globalStats.containsKey(p)) {
            // Si la HashMap contient déjà la clé p
            playerMinedBlocks = globalStats.get(p);
        } else {
            // Sinon on initialise la HashMap
            playerMinedBlocks = new HashMap<>();
        }
        updateGlobalStats(p, playerMinedBlocks, m);
        updatePlayersPercent(p);
    }

    private void updateGlobalStats(Player player, HashMap<String, Integer> playerMinedBlocks, Material m) {
        globalStats.put(player, playerMinedBlocks);
        if (m.equals(Material.getMaterial("DIAMOND_ORE"))) {
            playerMinedBlocks.merge(m.name(), 1, Integer::sum);
        }
        playerMinedBlocks.merge("all", 1, Integer::sum);
        globalStats.put(player, playerMinedBlocks);
    }

    /**
     * Actualise la TreeMap en supprimant la valeur précédent de ce joueur et en ajoutant la nouvelle valeur.
     * @param p Joueur
     */
    private void updatePlayersPercent(Player p) {

        if (getPlayersPercent().containsValue(p)){
            // Supprime le couple dans lequel le joueur p figure
            getPlayersPercent().values().remove(p);
        }
        playersPercent.put(calcPercent(getGlobalStats(), "DIAMOND_ORE", p), p);
    }

    public void removePlayer(Player p ){
        getPlayersPercent().values().remove(p);
    }

    /**
     * Calcul le taux de minage pour un block donné.
     *
     * @param globalStats  HashMap contenant le joueur en clé et sa HashMap en valeur
     * @param materialName Nom du block dont on veut connaître le pourcentage
     * @param p            Joueur
     * @return Le rapport en pourcentage Block / Blocks au total
     */
    public double calcPercent(HashMap<Player, HashMap<String, Integer>> globalStats, String materialName, Player p) {

        if (globalStats.containsKey(p)) {
            // On récupère la HashMap associée au joueur p
            HashMap<String, Integer> m = globalStats.get(p);

            if (m.containsKey(materialName)) {
                if (getTotalBlocksMined(p) > 0) {
                    return (double) m.get(materialName) / getTotalBlocksMined(p) * 100;
                }
            }
        }
        return 0;
    }

    // Getters
    public HashMap<Player, HashMap<String, Integer>> getGlobalStats() {
        return globalStats;
    }

    public int getTotalBlocksMined(Player p) {
        return globalStats.get(p).get("all");
    }

    public Map<Double, Player> getPlayersPercent() {
        return playersPercent;
    }
}
