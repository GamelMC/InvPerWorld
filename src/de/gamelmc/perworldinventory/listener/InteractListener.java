package de.gamelmc.perworldinventory.listener;

/*
 * GamelInvPerWorld
 * Copyright (C) 2019 GamelMC Developers
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import de.gamelmc.perworldinventory.main.PerWorldInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class InteractListener implements Listener {

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e) {
        Player p = (Player) e.getPlayer();
        if(p.hasMetadata("switching")) {
            e.setCancelled(true);
            p.sendMessage(PerWorldInventory.prefix + "Bitte warte, bis deine Daten geladen wurden.");
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Player p = (Player) e.getPlayer();
        if(p.hasMetadata("switching")) {
            e.setCancelled(true);
            p.sendMessage(PerWorldInventory.prefix + "Bitte warte, bis deine Daten geladen wurden.");
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e) {
        Player p = (Player) e.getPlayer();
        if(p.hasMetadata("switching")) {
            e.setCancelled(true);
            p.sendMessage(PerWorldInventory.prefix + "Bitte warte, bis deine Daten geladen wurden.");
        } else {
            e.setCancelled(false);
        }
    }
}
