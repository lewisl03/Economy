package uk.lewisl.economy.hooks;

import me.clip.placeholderapi.PlaceholderHook;
import me.clip.placeholderapi.external.EZPlaceholderHook;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import uk.lewisl.economy.Economy;
import uk.lewisl.economy.api.Api;
import uk.lewisl.economy.utils.Maths;

public class Placeholder extends EZPlaceholderHook {

    public Placeholder(JavaPlugin plugin) {
        super(plugin, "EconomyPlaceholder");

    }

    //placeholder for %player_balance%
    @Override
    public String onPlaceholderRequest(Player p, String identifier){

        //check if player is null before using player
        if(p == null){
            return "";
        }

        if(identifier.equals("player_balance_raw")){
            return String.valueOf(Api.getPlayer(p).getPlayerBalance());
        }
        //with suffix
        if(identifier.equals("player_balance")){
            return Maths.withSuffix(Api.getPlayer(p).getPlayerBalance());
        }
        if(identifier.equals("player_balance_comma")){
            return Maths.longComma(Api.getPlayer(p).getPlayerBalance());
        }




        return null;
    }







}
