package me.serbob.individualmobremover;

import me.serbob.individualmobremover.MobRemover.IndividualMobRemoverCommand;
import me.serbob.individualmobremover.MobRemover.IndividualMobRemoverTabCompleter;
import me.serbob.individualmobremover.MobRemover.NaturalSpawnCreatureRemover;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.Configuration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class IndividualMobRemover extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Configuration config = getConfig();

        getServer().getPluginManager().registerEvents(new NaturalSpawnCreatureRemover(this), this);
        TabCompleter tabCompleter = new IndividualMobRemoverTabCompleter();
        IndividualMobRemoverCommand command = new IndividualMobRemoverCommand(this);
        command.setTabCompleter(tabCompleter);
        PluginCommand pluginCommand = getCommand("restrictmob");
        pluginCommand.setExecutor(command);
        pluginCommand.setTabCompleter(tabCompleter);
        //getCommand("restrictmob").setExecutor(new IndividualMobRemoverCommand(this));
        //getCommand("restrictmob").setTabCompleter(new IndividualMobRemoverTabCompleter());
    }
}
