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
import de.gamelmc.perworldinventory.utils.InventoryHelper;
import de.gamelmc.perworldinventory.utils.MessageGetter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.metadata.FixedMetadataValue;

import java.io.IOException;

public class WorldSwitchListener implements Listener {

    private MessageGetter messageGetter = new MessageGetter();
    private InventoryHelper inventoryHelper = new InventoryHelper();

    @EventHandler
    public void onWorldSwitch(PlayerChangedWorldEvent e) {
        Player p = e.getPlayer();
        p.setMetadata("switching", new FixedMetadataValue(PerWorldInventory.getPlugin(), 1));
        p.sendMessage(messageGetter.getMessage("prefix") + messageGetter.getMessage("saving"));
        try {
            inventoryHelper.saveInventory(p, e.getFrom());
        } catch (IOException ex) {
            p.kickPlayer(messageGetter.getMessage("internalerror"));
        }
        p.sendMessage(messageGetter.getMessage("prefix") + messageGetter.getMessage("loading"));
        try {
            inventoryHelper.restoreInventory(p, p.getWorld());
        } catch (IOException ex) {
            p.sendMessage(messageGetter.getMessage("prefix") + messageGetter.getMessage("neverthere"));
            p.getInventory().clear();
            p.removeMetadata("switching", PerWorldInventory.getPlugin());
        }
        p.sendMessage(messageGetter.getMessage("prefix") + messageGetter.getMessage("success"));
        p.removeMetadata("switching", PerWorldInventory.getPlugin());
    }
}
