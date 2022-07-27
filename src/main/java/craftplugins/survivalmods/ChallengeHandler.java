package craftplugins.survivalmods;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ChallengeHandler implements Listener, CommandExecutor {

    SurvivalMods plugin;
    List<PlayerChallengesHandler> players = new ArrayList<>();

    public ChallengeHandler(SurvivalMods plugin) {
        this.plugin = plugin;

        Bukkit.getPluginCommand("challenges").setExecutor(this);
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println("Only players in game can do this!");
            return false;
        }

        Player p = (Player) sender;

        PlayerChallengesHandler ps = getPlayerStructure(p);

        if (ps != null) {
            ps.openInventory();
        } else {
            players.add(new PlayerChallengesHandler(plugin, p));
            ps = getPlayerStructure(p);
            ps.openInventory();
        }

        return false;
    }

    private PlayerChallengesHandler getPlayerStructure(Player player) {
        for (PlayerChallengesHandler ps : players) {
            if (ps.player == player) {
                return ps;
            }
        }
        return null;
    }
}
