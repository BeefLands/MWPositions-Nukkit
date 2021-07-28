package io.github.jaronz.mwpositions.listener;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerQuitEvent;
import io.github.jaronz.mwpositions.Util;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Util.setPlayerPosition(event.getPlayer());
    }
}
