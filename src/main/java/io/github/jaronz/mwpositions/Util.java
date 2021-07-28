package io.github.jaronz.mwpositions;

import cn.nukkit.Player;
import cn.nukkit.level.Location;
import cn.nukkit.level.Position;
import cn.nukkit.utils.Config;

import java.util.HashMap;
import java.util.Map;

public class Util {
    public static void setPlayerPosition(Player player){
        Config config = MWPositions.instance.getConfig();
        String uuid = player.getUniqueId().toString();
        Location location = player.getLocation();
        Map<String, Map<String, Map<String, Double>>> players =
                (Map<String, Map<String, Map<String, Double>>>) config.get("players");
        Map<String, Map<String, Double>> playerMap = players.getOrDefault(uuid, new HashMap());
        playerMap.put(player.getLevel().getName(), new HashMap<String, Double>(){{
            put("x", location.getX());
            put("y", location.getY());
            put("z", location.getZ());
            put("yaw", location.getYaw());
            put("pitch", location.getPitch());
        }});
        players.put(uuid, playerMap);
        config.set("players", players);
        config.save();
    }
}
