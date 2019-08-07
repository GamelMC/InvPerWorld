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

import de.gamelmc.perworldinventory.utils.InventoryHelper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.IOException;

public class QuitListener implements Listener {

    private InventoryHelper inventoryHelper = new InventoryHelper();

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        try {
            inventoryHelper.saveInventory(p, p.getWorld());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void onKick(PlayerKickEvent e) {
        Player p = e.getPlayer();
        try {
            inventoryHelper.saveInventory(p, p.getWorld());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
