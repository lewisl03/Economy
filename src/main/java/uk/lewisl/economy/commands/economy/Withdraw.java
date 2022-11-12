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

import java.util.ArrayList;

public class Withdraw implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player) {
            Player p = ((Player) commandSender);
            PlayerBalance pb = Economy.balanceCache.getPlayer(p);

            if(strings.length <= 0){
                p.sendMessage("You have not provided an amount");
                return true;
            }


            if(!Maths.isNumeric(strings[0])){
                p.sendMessage("You have given letters for amount, this can only be numbers");
                return true;
            }



            long amount = Long.parseLong(strings[0]);


            if(amount < Economy.configManager.getConfig().getLong("moneySettings.minimumPay")){
                p.sendMessage("Your minimum withdraw has to be over "+ Economy.configManager.getConfig().getLong("moneySettings.minimumPay"));
                return true;
            }

            if(pb.getPlayerBalance() < amount){
                p.sendMessage("You do not have enough money");
                return true;
            }


            if(!PlayerUtils.playerHasSpace(p)){
                p.sendMessage("You do not have enough room in your inventory");
                return true;
            }

            if(pb.subtract(amount)){
                ItemStack is = new ItemStack(Material.PAPER);
                ItemMeta im = is.getItemMeta();
                im.setDisplayName(ChatColor.GOLD+"Withdraw Note");
                ArrayList<String> lore =  new ArrayList<>();
                lore.add(ChatColor.WHITE+"Player: "+ p.getDisplayName());
                lore.add(ChatColor.WHITE+"Amount: "+ amount);
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