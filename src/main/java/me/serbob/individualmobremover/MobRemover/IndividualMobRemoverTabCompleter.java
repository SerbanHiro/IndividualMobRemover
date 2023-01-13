package me.serbob.individualmobremover.MobRemover;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class IndividualMobRemoverTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("restrictmob")) {
            return Collections.emptyList();
        }

        if (!sender.hasPermission("individualmobremover.restrictmob")) {
            return Collections.emptyList();
        }
        List<String> mobs = new ArrayList<>();
        for (EntityType entityType : EntityType.values()) {
            if (entityType.isAlive()) {
                mobs.add(entityType.name());
            }
        }
        if (args.length > 0) {
            String letters = args[0].toLowerCase();
            mobs = mobs.stream()
                    .filter(mob -> mob.toLowerCase().startsWith(letters))
                    .collect(Collectors.toList());
        }

        return mobs;
    }
}
