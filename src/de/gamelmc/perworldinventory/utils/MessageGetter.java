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

public class MessageGetter {

    private static MessageGetter messageGetter;


    public static MessageGetter getMessageGetter() {
        return messageGetter;
    }

    public static void setMessageGetter(MessageGetter messageGetter) {
        MessageGetter.messageGetter = messageGetter;
    }

    public String getMessage(String name) {
        PerWorldInventory.getPlugin().getConfig();
        FileConfiguration configuration = PerWorldInventory.getPlugin().getConfig();
        if(configuration.getString("language").equalsIgnoreCase("de")) {
            switch (name) {
                case "prefix":
                    return "§r[§aSwitcher§r] ";
                case "noplayer":
                    return "§cDieser Befehl kann nur von einem Spieler ausgeführt werden!";
                case "noperms":
                    return "§cDazu hast du keine Rechte!";
                case "commandinvusage":
                    return "§cVerwendung: /inv <Spieler> <Welt>";
                case "filenotfound":
                    return "§cDie angeforderte Datei wurde nicht gefunden.";
                case "internalerror":
                    return "Interner Server Fehler.";
                case "waituntilloaded":
                    return "Bitte warte, bis deine Daten geladen wurden.";
                case "saving":
                    return "Daten werden gespeichert...";
                case "loading":
                    return "Lade Daten...";
                case "neverthere":
                    return "Es sieht so aus als ob du diese Welt noch nie betreten hättest, dein Inventar ist leer.";
                case "success":
                    return "Daten erfolgreich geladen!";
                case "loaded":
                    return "§aGamelInvPerWorld wurde geladen!";
                case "unloaded":
                    return "§aGamelInvPerWorld wurde entladen.";
                default:
                    return "";
            }
        } else {
            switch (name) {
                case "prefix":
                    return "§r[§aSwitcher§r] ";
                case "noplayer":
                    return "§cThis command can only be executed ba a Player!";
                case "noperms":
                    return "Missing permissions.";
                case "commandinvusage":
                    return "§cUsage: /inv <player> <world>";
                case "filenotfound":
                    return "§cNo such file.";
                case "internalerror":
                    return "§cInternal server error.";
                case "waituntilloaded":
                    return "Please wait until your data is loaded.";
                case "saving":
                    return "Saving...";
                case "loading":
                    return "Loading...";
                case "neverthere":
                    return "It seems like you've never been here, your inventory is empty.";
                case "success":
                    return "Success!";
                case "loaded":
                    return "§aGamelInvPerWorld is loaded!";
                case "unloaded":
                    return "§aGamelInvPerWorld is unloaded.";
                default:
                    return "";
            }
        }
    }
}
