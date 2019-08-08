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
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigCreator {

    private static ConfigCreator configCreator;

    public static ConfigCreator getConfigCreator() {
        return configCreator;
    }

    public static void setConfigCreator(ConfigCreator configCreator) {
        ConfigCreator.configCreator = configCreator;
    }

    public void setup() {
        PerWorldInventory.getPlugin().getConfig();
        FileConfiguration configuration = PerWorldInventory.getPlugin().getConfig();
        configuration.addDefault("language", "en");
        configuration.options().copyDefaults(true);

    }
}
