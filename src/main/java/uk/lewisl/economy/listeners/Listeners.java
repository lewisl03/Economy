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

public class Listeners implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack is = event.getItem();




        if(is != null) {
            if (is.getType() == Material.PAPER) {

                String withdrawNote = NMS.getItemNms(is, "withdrawNote");
                System.out.println(withdrawNote+" WiThDRAW");

                if (withdrawNote != null) {

                    long amount = Long.parseLong(withdrawNote);

                    if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        Economy.balanceCache.getPlayer(player).add(amount);
                        player.sendMessage("You have redeemed the note "+amount+" has been added to your balance");


                            player.getInventory().getItemInHand().setAmount(event.getItem().getAmount() -1);




                        event.setCancelled(true);
                        return;
                    }
                }
            }
            }
        }






}
