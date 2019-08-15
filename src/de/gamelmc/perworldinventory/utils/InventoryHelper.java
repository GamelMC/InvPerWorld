package de.gamelmc.perworldinventory.utils;

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
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class InventoryHelper {

    private static InventoryHelper inventoryHelper;

    public static InventoryHelper getInventoryHelper() {
        return inventoryHelper;
    }

    public static void setInventoryHelper(InventoryHelper inventoryHelper) {
        InventoryHelper.inventoryHelper = inventoryHelper;
    }

    private PerWorldInventory plugin = PerWorldInventory.getPlugin();

    // Thanks to https://www.spigotmc.org/threads/save-inventory-and-then-load-it.43907/
    public void saveInventory(Player p, World world) throws IOException {
        String filename = "Inventory_" + p.getName() + "_" + world.getName() + ".yml";
        File f = new File(plugin.getDataFolder().getAbsolutePath(), filename);
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        c.set("inventory.armor", p.getInventory().getArmorContents());
        c.set("inventory.content", p.getInventory().getContents());
        c.save(f);
    }

    @SuppressWarnings("unchecked")
    public void restoreInventory(Player p, World world) throws IOException {
        String filename = "Inventory_" + p.getName() + "_" + world.getName() + ".yml";
        File f = new File(plugin.getDataFolder().getAbsolutePath(), filename);
        if(!(f.exists())) {
            throw new IOException();
        }
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        ItemStack[] content = ((List<ItemStack>) c.get("inventory.armor")).toArray(new ItemStack[0]);
        p.getInventory().setArmorContents(content);
        content = ((List<ItemStack>) c.get("inventory.content")).toArray(new ItemStack[0]);
        p.getInventory().setContents(content);
    }

    @SuppressWarnings("unchecked")
    public void openInventory(Player p, String target, String world) throws IOException {
        String filename = "Inventory_" + target +  "_" + world + ".yml";
        File f = new File(plugin.getDataFolder().getAbsolutePath(), filename);
        if(!(f.exists())) {
            throw new IOException();
        }
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        ItemStack[] content;
        content = ((List<ItemStack>) c.get("inventory.content")).toArray(new ItemStack[0]);
        Inventory inv = Bukkit.createInventory(p, 4*9, "§6" + target + "§c/§6" + world);
        inv.setContents(content);
        p.setMetadata("inv_view", new FixedMetadataValue(PerWorldInventory.getPlugin(), 1));
        p.openInventory(inv);
    }

    public void savefrominv(String target, String world, Inventory inventory) throws IOException {
        String filename = "Inventory_" + target + "_" + world + ".yml";
        File f = new File(plugin.getDataFolder().getAbsolutePath(), filename);
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        c.set("inventory.content", inventory.getContents());
        c.save(f);
    }


}
