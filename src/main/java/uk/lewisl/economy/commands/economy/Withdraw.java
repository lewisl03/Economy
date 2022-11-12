package uk.lewisl.economy.commands.economy;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import uk.lewisl.economy.Economy;
import uk.lewisl.economy.data.PlayerBalance;
import uk.lewisl.economy.utils.Maths;
import uk.lewisl.economy.utils.NMS;
import uk.lewisl.economy.utils.PlayerUtils;
import uk.lewisl.economy.utils.Text;

import java.util.ArrayList;


/**
 * @author lewis
 * @since 12/11/2022
 */
public class Withdraw implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player) {
            Player p = ((Player) commandSender);
            PlayerBalance pb = Economy.balanceCache.getPlayer(p);

            if(strings.length <= 0){
                p.sendMessage(Text.convertString(Economy.configManager.getConfig().getString("messages.notProvidedAmount")));
                return true;
            }


            if(!Maths.isNumeric(strings[0])){
                p.sendMessage(Text.convertString(Economy.configManager.getConfig().getString("messages.invalidNumbers")));
                return true;
            }



            long amount = Long.parseLong(strings[0]);


            if(amount < Economy.configManager.getConfig().getLong("moneySettings.minimumPay")){
                p.sendMessage(Economy.configManager.getConfig().getString("messages.minimumWithdrawOver").replaceAll("%amount%", ""+Maths.longComma(Economy.configManager.getConfig().getLong("moneySettings.minimumPay"))));
                return true;
            }

            if(pb.getPlayerBalance() < amount){
                p.sendMessage(Text.convertString(Economy.configManager.getConfig().getString("messages.notEnoughMoney")));
                return true;
            }


            if(!PlayerUtils.playerHasSpace(p)){
                p.sendMessage(Text.convertString(Economy.configManager.getConfig().getString("messages.noInventorySpace")));
                return true;
            }

            if(pb.subtract(amount)){
                ItemStack is = new ItemStack(Material.PAPER);
                ItemMeta im = is.getItemMeta();
                im.setDisplayName(ChatColor.GOLD+"Withdraw Note");
                ArrayList<String> lore =  new ArrayList<>();
                lore.add(ChatColor.WHITE+"Player: "+ p.getDisplayName());
                lore.add(ChatColor.WHITE+"Amount: "+ Maths.longComma(amount));
                lore.add(ChatColor.GRAY+"Right click to redeem");
                im.setLore(lore);
                is.setItemMeta(im);

                is = NMS.setItemNms(is, "withdrawNote", String.valueOf(amount));
                p.getInventory().addItem(is);

                p.sendMessage("You have withdrew "+ amount);

                return true;

            }




        }







        return false;
    }
}