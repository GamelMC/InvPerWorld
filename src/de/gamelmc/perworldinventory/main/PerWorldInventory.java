package de.gamelmc.perworldinventory.main;

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

import de.gamelmc.perworldinventory.commands.CommandInv;
import de.gamelmc.perworldinventory.listener.*;
import de.gamelmc.perworldinventory.utils.InventoryHelper;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PerWorldInventory extends JavaPlugin {

    private static PerWorldInventory plugin;

    public static PerWorldInventory getPlugin() {
        return plugin;
    }

    public static String prefix = "§r[§aSwitcher§r] ";

    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getConsoleSender().sendMessage("§aGamelInvPerWorld wurde geladen.");
        InventoryHelper.setInventoryHelper(new InventoryHelper());
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new WorldSwitchListener(), this);
        pm.registerEvents(new InteractListener(), this);
        pm.registerEvents(new QuitListener(), this);
        pm.registerEvents(new JoinListener(), this);
        pm.registerEvents(new MoveListener(), this);
        this.getCommand("inv").setExecutor(new CommandInv());

    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§aGamelInvPerWorld wurde entladen.");
    }
}
