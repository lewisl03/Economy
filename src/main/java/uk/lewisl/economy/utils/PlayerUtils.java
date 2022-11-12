package uk.lewisl.economy.utils;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import uk.lewisl.economy.Economy;

import java.util.UUID;

/**
 * @author lewis
 * @since 12/11/2022
 */
public class PlayerUtils {

    public static boolean playerHasSpace(Player p){
        return p.getInventory().firstEmpty() != -1;
    }

    public static String getPlayerName(UUID uuid){
        if(Bukkit.getPlayer(uuid) != null){
            return Bukkit.getPlayer(uuid).getDisplayName();
        }else if(Bukkit.getOfflinePlayer(uuid) != null){
            return Bukkit.getOfflinePlayer(uuid).getName();
        }
        return null;
    }




}
