package uk.lewisl.economy.commands.economy;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.lewisl.economy.Economy;
import uk.lewisl.economy.data.PlayerBalance;
import uk.lewisl.economy.utils.Maths;

public class Pay implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        //if theyre a player
        if(commandSender instanceof Player){
            Player p = ((Player) commandSender);
            PlayerBalance pb = Economy.balanceCache.getPlayer(p);
            if(strings.length <= 0){
                p.sendMessage("You have not provided a targeted player");
                return true;
            }
            else if(strings.length <= 1){
                p.sendMessage("You have not provided an amount");
                return true;
            }

            if(!Maths.isNumeric(strings[1])){
                p.sendMessage("You have given letters for amount, this can only be numbers");
                return true;
            }

            Player targetPlayer = Bukkit.getPlayer(strings[0]);
            if(targetPlayer == null){
                p.sendMessage("The target does not exist");
                return true;
            }

            long amount = Long.parseLong(strings[1]);


            if(amount < Economy.configManager.getConfig().getLong("moneySettings.minimumPay")){
                p.sendMessage("Your payment is too small, payment must be over "+ Economy.configManager.getConfig().getLong("moneySettings.minimumPay"));
                return true;
            }

            if(pb.getPlayerBalance() < amount){
                p.sendMessage("You do not have enough money");
                return true;
            }


            PlayerBalance targetPb = Economy.balanceCache.getPlayer(targetPlayer);

            if(pb.subtract(amount)){
                targetPb.add(amount);
                targetPlayer.sendMessage(p.getDisplayName()+" Has sent you "+ amount);
                p.sendMessage("You have paid, "+ targetPlayer.getDisplayName()+" "+ amount);
                return true;

            }


            return true;
        }


        return false;
    }
}