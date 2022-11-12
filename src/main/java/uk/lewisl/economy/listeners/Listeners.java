package uk.lewisl.economy.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import uk.lewisl.economy.Economy;
import uk.lewisl.economy.utils.NMS;


/**
 * @author lewis
 * @since 12/11/2022
 */
public class Listeners implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack is = event.getItem();


        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (is != null) {
                if (is.getType() == Material.PAPER) {

                    String withdrawNote = NMS.getItemNms(is, "withdrawNote");
                    System.out.println(withdrawNote + " WiThDRAW");

                    if (withdrawNote != null) {

                        long amount = Long.parseLong(withdrawNote);


                        Economy.balanceCache.getPlayer(player).add(amount);
                        player.sendMessage("You have redeemed the note " + amount + " has been added to your balance");


                        player.getInventory().getItemInHand().setAmount(event.getItem().getAmount() - 1);


                        event.setCancelled(true);
                        return;
                    }
                }
            }
        }
    }
}





