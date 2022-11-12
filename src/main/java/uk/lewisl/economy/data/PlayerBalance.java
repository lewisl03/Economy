package uk.lewisl.economy.data;

import org.bukkit.entity.Player;
import uk.lewisl.economy.Economy;

import java.util.UUID;

public class PlayerBalance {
    UUID playerUUID;
    long playerBalance;

    public PlayerBalance(UUID playerUUID, long playerBalance) {
        this.playerUUID = playerUUID;
        this.playerBalance = playerBalance;
    }


    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public long getPlayerBalance() {
        return playerBalance;
    }


    public void setPlayerBalance(long playerBalance) {
        this.playerBalance = playerBalance;
        savePlayer();
    }

    public boolean playerHasBalance(long amount){
        return playerBalance >= amount;
    }

    public boolean subtract(long amount){
        if(getPlayerBalance() >= amount ){
            playerBalance -= amount;
            savePlayer();
            return  true;
        }
        return false;
    }

    public boolean add(long amount){
        if(playerBalance + amount <= Economy.configManager.getConfig().getLong("moneySettings.maxBalance")){
            playerBalance += amount;
            savePlayer();
            return true;
        }
        return false;
    }

    public void savePlayer(){
        Economy.balanceCache.savePlayer(this);
    }





}
