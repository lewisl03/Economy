package uk.lewisl.economy.utils;

import org.bukkit.entity.Player;

public class PlayerUtils {

    public static boolean playerHasSpace(Player p){
        return p.getInventory().firstEmpty() != -1;
    }


}
