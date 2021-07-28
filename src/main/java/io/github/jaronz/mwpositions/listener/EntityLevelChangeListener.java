package io.github.jaronz.mwpositions.listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityLevelChangeEvent;
import cn.nukkit.event.player.PlayerTeleportEvent;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.scheduler.NukkitRunnable;
import cn.nukkit.utils.Config;
import io.github.jaronz.mwpositions.MWPositions;
import io.github.jaronz.mwpositions.Util;

import java.util.HashMap;
import java.util.Map;

public class EntityLevelChangeListener implements Listener {
    @EventHandler
    public void onEntityLevelChange(EntityLevelChangeEvent event){
        if(!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        Util.setPlayerPosition(player);
        MWPositions plugin = MWPositions.instance;
        Config config = plugin.getConfig();
        Level target = event.getTarget();
        if(config.getStringList("ignoredWorlds").contains(target.getName())) return;
        String uuid = player.getUniqueId().toString();
        Map<String, Map<String, Map<String, Double>>> players =
                (Map<String, Map<String, Map<String, Double>>>) config.get("players");
        Map<String, Map<String, Double>> playerMap = players.getOrDefault(uuid, new HashMap());
        if(!playerMap.containsKey(target.getName())) return;
        Map<String, Double> location = playerMap.get(target.getName());
        if(!location.containsKey("x") || !location.containsKey("y") || !location.containsKey("z") ||
            !location.containsKey("yaw") || !location.containsKey("pitch")) return;
        double x = location.get("x"),
            y = location.get("y"),
            z = location.get("z"),
            yaw = location.get("yaw"),
            pitch = location.get("pitch");
        new NukkitRunnable() {
            @Override
            public void run() {
                player.teleport(new Location(x, y, z, yaw, pitch, target), PlayerTeleportEvent.TeleportCause.PLUGIN);
            }
        }.runTaskLater(plugin, 1);
    }
}
