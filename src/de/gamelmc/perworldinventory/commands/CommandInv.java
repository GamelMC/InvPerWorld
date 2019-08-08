package de.gamelmc.perworldinventory.commands;

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
import de.gamelmc.perworldinventory.utils.MessageGetter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.io.IOException;

public class CommandInv implements CommandExecutor {

    private InventoryHelper inventoryHelper = new InventoryHelper();
    private MessageGetter messageGetter = new MessageGetter();

    @SuppressWarnings("unchecked")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(messageGetter.getMessage("noplayer"));
            return false;
        }
        Player p = (Player) sender;
        if(!p.hasPermission("invperworld.admin")) {
            p.sendMessage(messageGetter.getMessage("noperms"));
            return false;
        }
        if(args.length != 2) {
            p.sendMessage(messageGetter.getMessage("commandinvusage"));
            return false;
        }
        try {
            inventoryHelper.openInventory(p, args[0], args[1]);
        } catch (IOException e) {
            p.sendMessage(messageGetter.getMessage("filenotfound"));
        }

        return false;
    }
}
