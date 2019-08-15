package de.gamelmc.perworldinventory.listener;

import de.gamelmc.perworldinventory.main.PerWorldInventory;
import de.gamelmc.perworldinventory.utils.InventoryHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.io.IOException;

public class InventoryCloseListener implements Listener {

    private InventoryHelper inventoryHelper = new InventoryHelper();

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        if(p.hasMetadata("inv_view")) {
            String title = e.getInventory().getName();
            p.sendMessage("Debug: " + title);
            String[] strings = title.split("/");
            String name = strings[0];
            name = name.replace("§6", "");
            p.sendMessage("Debug: " + name);
            String world = strings[1];
            world = world.replace("§6", "");
            p.sendMessage("Debug: " + world);
            p.removeMetadata("inv_view", PerWorldInventory.getPlugin());
            try {
                inventoryHelper.savefrominv(name, world, e.getInventory());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Player target = Bukkit.getPlayer(name);
            if (target != null) {
                try {
                    inventoryHelper.restoreInventory(target, Bukkit.getWorld(world));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
