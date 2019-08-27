package de.gamelmc.perworldinventory.utils;

import de.gamelmc.perworldinventory.main.PerWorldInventory;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileUpdater {

    public static void search(final String pattern, final File folder, List<String> result) {
        for (final File f : folder.listFiles()) {

            if (f.isDirectory()) {
                search(pattern, f, result);
            }

            if (f.isFile()) {
                if (f.getName().matches(pattern)) {
                    result.add(f.getAbsolutePath());
                }
            }

        }
    }

    private static void copyFileUsingJava7Files(File source, File dest) throws IOException {
        Files.copy(source.toPath(), dest.toPath());
    }

    public void update() {
        System.out.println(MessageGetter.getMessageGetter().getMessage("updating_file"));
        File dic = new File(PerWorldInventory.getPlugin().getDataFolder().getAbsolutePath() + "/inventorys");
        if (!dic.exists()) {
            dic.mkdir();
        }
        final File dir = new File(System.getProperty("user.dir"));
        List<String> res = new ArrayList<>();
        search(".*\\.yml", dir, res);
        String[] splited1;
        String[] splited2;
        String UUID;
        String p;
        String world;
        for (String s : res) {
            splited1 = s.split("/");
            splited2 = splited1[splited1.length-1].split("_");
            world = splited2[splited2.length-1].replace(".yml", "");
            p = splited2[splited2.length-2];
            System.out.println("Player: " + p + " World: " + world);
            OfflinePlayer op = Bukkit.getOfflinePlayer("p");
            UUID = op.getUniqueId().toString();
            File playerfolder = new File(PerWorldInventory.getPlugin().getDataFolder().getAbsolutePath() + "/inventorys/" + UUID);
            if(!playerfolder.exists()) {
                playerfolder.mkdir();
            }
            File old = new File(PerWorldInventory.getPlugin().getDataFolder().getAbsolutePath() + "/inventorys/" + UUID + "/" + world + ".yml");
            File neu = new File(PerWorldInventory.getPlugin().getDataFolder().getAbsolutePath() + "/Inventory_" + p + "_" + world + ".yml");
            try {
                copyFileUsingJava7Files(old, neu);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
