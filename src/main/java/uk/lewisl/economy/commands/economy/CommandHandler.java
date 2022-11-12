package uk.lewisl.economy.commands.economy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class CommandHandler {
    public abstract String command();
    public abstract void onCommand(CommandSender sender, Command command, String s, String[] strings);
}
