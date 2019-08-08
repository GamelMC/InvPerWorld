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
import de.gamelmc.perworldinventory.utils.ConfigCreator;
import de.gamelmc.perworldinventory.utils.InventoryHelper;
import de.gamelmc.perworldinventory.utils.MessageGetter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PerWorldInventory extends JavaPlugin {

    private static PerWorldInventory plugin;

    public static PerWorldInventory getPlugin() {
        return plugin;
    }
    private static MessageGetter messageGetter = new MessageGetter();
    private ConfigCreator configCreator = new ConfigCreator();



    @Override
    public void onEnable() {

        plugin = this;
        plugin.getConfig();
        configCreator.setup();
        InventoryHelper.setInventoryHelper(new InventoryHelper());
        MessageGetter.setMessageGetter(new MessageGetter());

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new WorldSwitchListener(), plugin);
        pm.registerEvents(new InteractListener(), plugin);
        pm.registerEvents(new QuitListener(), plugin);
        pm.registerEvents(new JoinListener(), plugin);
        pm.registerEvents(new MoveListener(), plugin);
        this.getCommand("inv").setExecutor(new CommandInv());
        saveConfig();

    }

}
