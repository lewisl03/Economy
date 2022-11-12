package uk.lewisl.economy.utils;

import org.bukkit.entity.Player;
import uk.lewisl.economy.Economy;


/**
 * @author lewis
 * @since 12/11/2022
 */
public class Sound {
    //plays a success sound to the player
    public static void playSuccessSound(Player p) {

        if (Economy.configManager.getConfig().getBoolean("soundEnabled")) {
            //gets settings from the config
            p.getWorld().playSound(p.getLocation(), org.bukkit.Sound.valueOf((String) Economy.configManager.getConfig().getString("sound.successSound")), Economy.configManager.getConfig().getInt("sound.volume"), Economy.configManager.getConfig().getInt("sound.pitch"));
        }
    }
    //plays a deny sound to the player
    public static void playDenySound(Player p) {
        if (Economy.configManager.getConfig().getBoolean("soundEnabled")) {
            //gets settings from the config
            p.getWorld().playSound(p.getLocation(), org.bukkit.Sound.valueOf((String) Economy.configManager.getConfig().getString("sound.denySound")),  Economy.configManager.getConfig().getInt("sound.volume"), Economy.configManager.getConfig().getInt("sound.pitch"));
        }
    }

}