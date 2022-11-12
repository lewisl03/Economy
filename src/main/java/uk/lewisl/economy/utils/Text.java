package uk.lewisl.economy.utils;

import org.bukkit.ChatColor;

public class Text {

    public static String convertString(String message){
        return ChatColor.translateAlternateColorCodes('&', message);

    }
}
