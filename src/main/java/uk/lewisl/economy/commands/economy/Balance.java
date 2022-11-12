package uk.lewisl.economy.commands.economy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.lewisl.economy.Economy;
import uk.lewisl.economy.data.PlayerBalance;

public class Balance implements CommandExecutor  {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player){
            Player p = (Player) commandSender;
            PlayerBalance playerBalance = Economy.balanceCache.getPlayer(p);
            p.sendMessage("BALANCE: "+ playerBalance.getPlayerBalance());


            return true;
        }





        return false;
    }
}