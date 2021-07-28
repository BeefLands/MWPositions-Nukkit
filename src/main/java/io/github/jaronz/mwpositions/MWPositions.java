package io.github.jaronz.mwpositions;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginLogger;
import cn.nukkit.plugin.PluginManager;
import cn.nukkit.utils.TextFormat;
import io.github.jaronz.mwpositions.listener.EntityLevelChangeListener;
import io.github.jaronz.mwpositions.listener.PlayerJoinListener;
import io.github.jaronz.mwpositions.listener.PlayerQuitListener;

public class MWPositions extends PluginBase {
    private PluginLogger logger;
    public static MWPositions instance;

    @Override
    public void onLoad() {
        logger = this.getLogger();
        logger.info(TextFormat.WHITE + this.getName() + " has been loaded!");
    }

    @Override
    public void onEnable() {
        logger.info(TextFormat.DARK_GREEN + this.getName() + " has been enabled!");
        instance = this;

        saveDefaultConfig();

        PluginManager pluginManager = this.getServer().getPluginManager();
        pluginManager.registerEvents(new EntityLevelChangeListener(), this);
        pluginManager.registerEvents(new PlayerQuitListener(), this);
        pluginManager.registerEvents(new PlayerJoinListener(), this);
    }

    @Override
    public void onDisable() {
        logger.info(TextFormat.DARK_RED + this.getName() + " has been disabled!");
    }
}
