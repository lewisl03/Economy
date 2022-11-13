package uk.lewisl.economy.commands.economy;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.lewisl.economy.Economy;
import uk.lewisl.economy.data.PlayerBalance;
import uk.lewisl.economy.utils.Maths;
import uk.lewisl.economy.utils.Sound;
import uk.lewisl.economy.utils.Text;

/**
 * @author lewis
 * @since 12/11/2022
 */
public class Pay implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        //if theyre a player
        if(commandSender instanceof Player){
            Player p = ((Player) commandSender);
            PlayerBalance pb = Economy.balanceCache.getPlayer(p);
            if(strings.length <= 0){
                p.sendMessage(Text.convertString(Economy.configManager.getConfig().getString("messages.targetNotProvided")));
                return true;
            }
            else if(strings.length <= 1){
                p.sendMessage(Text.convertString(Economy.configManager.getConfig().getString("messages.notProvidedAmount")));
                return true;
            }

            if(!Maths.isNumeric(strings[1])){
                p.sendMessage(Text.convertString(Economy.configManager.getConfig().getString("messages.invalidNumbers")));
                return true;
            }

            Player targetPlayer = Bukkit.getPlayer(strings[0]);
            if(targetPlayer == null){
                p.sendMessage(Text.convertString(Economy.configManager.getConfig().getString("messages.targetNotExist")));
                return true;
            }

            if(p.getUniqueId().equals(targetPlayer.getUniqueId())){
                p.sendMessage(Text.convertString(Economy.configManager.getConfig().getString("messages.noPayYourself")));
                return true;
            }


            long amount = Long.parseLong(strings[1]);

            if(amount < Economy.configManager.getConfig().getLong("moneySettings.minimumPay")){
                p.sendMessage(Text.convertString(Economy.configManager.getConfig().getString("messages.paymentTooSmall").replaceAll("%amount%", ""+Maths.longComma(Economy.configManager.getConfig().getLong("moneySettings.minimumPay")))));
                return true;
            }

            if(pb.getPlayerBalance() < amount){
                p.sendMessage(Text.convertString(Economy.configManager.getConfig().getString("messages.notEnoughMoney")));
                return true;
            }


            PlayerBalance targetPb = Economy.balanceCache.getPlayer(targetPlayer);

            if(pb.subtract(amount)){
                targetPb.add(amount);
                //receivedMoney


                targetPlayer.sendMessage(Text.convertString(Economy.configManager.getConfig().getString("messages.receivedMoney").replaceAll("%fromPlayer%", p.getDisplayName()).replaceAll("%amount%", Maths.longComma(amount)+"")));
                p.sendMessage(Text.convertString(Economy.configManager.getConfig().getString("messages.payedPlayer").replaceAll("%toPlayer%", p.getDisplayName()).replaceAll("%amount%", Maths.longComma(amount)+"")));
                Sound.playSuccessSound(p);
                return true;

            }


            return true;
        }


        return false;
    }
}