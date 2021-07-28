package io.github.jaronz.mwpositions.listener;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerTeleportEvent;
import cn.nukkit.level.Level;
import cn.nukkit.utils.TextFormat;
import io.github.jaronz.mwpositions.MWPositions;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        MWPositions plugin = MWPositions.instance;
        String startWorld = plugin.getConfig().getString("startWorld", "");
        Level world = plugin.getServer().getLevelByName(startWorld);
        if(world == null) {
            if(!startWorld.isEmpty()) plugin.getLogger().warning(TextFormat.YELLOW + "Invalid starting world!");
            return;
        }
        event.getPlayer().teleport(world.getSpawnLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
    }
}
