package me.serbob.individualmobremover.MobRemover;

import me.serbob.individualmobremover.IndividualMobRemover;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.List;

public class IndividualMobRemoverCommand implements CommandExecutor {
    private final IndividualMobRemover plugin;
    private TabCompleter tabCompleter;
    public IndividualMobRemoverCommand(IndividualMobRemover plugin) {
        this.plugin=plugin;
    }

    public void setTabCompleter(TabCompleter tabCompleter) {
        this.tabCompleter = tabCompleter;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("individualmobremover.restrictmob")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /restrictmob <mob>");
            return true;
        }

        Player player = (Player) sender;

        String mobName = args[0];
        EntityType entityType = EntityType.fromName(mobName);

        if(entityType!=null) {
            if(entityType.isAlive()) {
                Configuration config = plugin.getConfig();
                List<String> restrictedMobs = config.getStringList("restricted-mobs");
                if(!restrictedMobs.contains(mobName)) {
                    restrictedMobs.add(mobName);
                    config.set("restricted-mobs", restrictedMobs);
                    plugin.saveConfig();
                    sender.sendMessage(ChatColor.GREEN + "The mob '" + ChatColor.WHITE + mobName + ChatColor.GREEN + "' was added to the list of restricted mobs.");
                } else {
                    player.sendMessage(ChatColor.RED + "You cannot add duplicates.");
                }
            } else {
                player.sendMessage(ChatColor.RED + "You have entered an unalive entity.");
            }
        } else {
            player.sendMessage(ChatColor.RED + "That mob's name doesn't exist.");
        }
        return true;
    }
}
