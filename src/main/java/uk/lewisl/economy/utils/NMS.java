package uk.lewisl.economy.utils;


import org.bukkit.craftbukkit.v1_19_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class NMS {

    public static ItemStack setItemNms(ItemStack itemStack, String key, String value) {
        net.minecraft.world.item.ItemStack nms = CraftItemStack.asNMSCopy(itemStack);
        if (nms.u() != null) {
            nms.u().a(key, value);
            return CraftItemStack.asBukkitCopy(nms);
        }
        return null;
    }


    public static String getItemNms(ItemStack itemStack, String key) {
        net.minecraft.world.item.ItemStack nms = CraftItemStack.asNMSCopy(itemStack);
        if (nms.u() != null) {
            return nms.u().l(key);
        }
        return null;
    }

}