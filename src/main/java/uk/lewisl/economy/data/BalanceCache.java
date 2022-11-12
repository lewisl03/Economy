package uk.lewisl.economy.data;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import uk.lewisl.economy.Economy;

import java.util.HashSet;
import java.util.UUID;
import java.util.logging.Level;

public class BalanceCache implements Listener {
    public HashSet<PlayerBalance> playerBalances = new HashSet<>();


    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e){

        Player p = e.getPlayer();
       PlayerBalance playerBalance = Economy.mysql.loadPlayer(p.getUniqueId());

       //if player doesn't exist in the db
       if(playerBalance == null){
           playerBalance = new PlayerBalance(e.getPlayer().getUniqueId(), Economy.configManager.getConfig().getLong("moneySettings.startingBalance"));
        //   Economy.getInstance().getLogger().log(Level.FINE,"Created new player "+ p.getDisplayName());
       }
     //  Economy.getInstance().getLogger().log(Level.FINE,"Loaded player "+ e.getPlayer().getDisplayName());
       playerBalances.add(playerBalance);
    }

    public void loadAll(){
        for(Player player : Bukkit.getOnlinePlayers()){

            PlayerBalance playerBalance = Economy.mysql.loadPlayer(player.getUniqueId());

            if(playerBalance == null){
                playerBalance = new PlayerBalance(player.getUniqueId(), Economy.configManager.getConfig().getLong("moneySettings.startingBalance"));
                Economy.getInstance().getLogger().log(Level.FINE,"Created new player "+ player.getDisplayName());
            }

            playerBalances.add(playerBalance);

        }

    }




    @EventHandler
    public void playerQuitEvent(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if(cacheContain(p.getUniqueId())){
            PlayerBalance playerBalance = getPlayer(p);
            Economy.mysql.savePlayer(playerBalance);
            removePlayer(playerBalance);
        }else{
            Economy.getInstance().getLogger().log(Level.WARNING, "Player "+ e.getPlayer().getDisplayName()+"'s data was not cached");

        }

    }

    public void removePlayer(PlayerBalance playerBalance){
        playerBalances.remove(playerBalance);
    }


    public PlayerBalance getPlayer(Player player){

        for(PlayerBalance p : playerBalances){
            if(p.getPlayerUUID().equals(player.getUniqueId())) return p;
        }

       return Economy.mysql.loadPlayer(player.getUniqueId());
    }

    public long getPlayersBalance(Player player){
        return getPlayer(player).playerBalance;
    }
    public String getPlayersBalanceString(Player player){
        return String.valueOf(getPlayer(player).playerBalance);
    }




    public void saveAll(){
        for(PlayerBalance player : playerBalances){
            Economy.mysql.savePlayer(player);
        }
    }

    public void savePlayer(PlayerBalance playerBalance){
        Economy.mysql.savePlayer(playerBalance);
    }





    private  boolean cacheContain(UUID player){
        for(PlayerBalance p : playerBalances){
            if(p.getPlayerUUID().equals(player)) return true;

        }
        return false;
    }





    public void cachePlayersBalances(){


    }




}
