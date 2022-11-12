package uk.lewisl.economy.api;

import org.bukkit.entity.Player;
import uk.lewisl.economy.Economy;
import uk.lewisl.economy.data.PlayerBalance;

import java.util.HashSet;

public class Api {

    public static HashSet<PlayerBalance> getEconomy(){
        return Economy.balanceCache.playerBalances;
    }

    public static PlayerBalance  getPlayer(Player player){
        return Economy.balanceCache.getPlayer(player);
    }

}
