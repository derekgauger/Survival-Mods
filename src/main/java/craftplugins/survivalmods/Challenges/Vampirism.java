package craftplugins.survivalmods.Challenges;

import craftplugins.survivalmods.SurvivalMods;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class Vampirism implements Listener, Challenge {

    SurvivalMods plugin;
    private boolean running;
    private Player player;
    private int timer;
    private long period;
    List<Material> edibleFoods = new ArrayList<>();

    public Vampirism (SurvivalMods plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
        this.period = 20;

        instantiateEdibleFoods();
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private void instantiateEdibleFoods() {
        edibleFoods.add(Material.BEEF);
        edibleFoods.add(Material.PORKCHOP);
        edibleFoods.add(Material.CHICKEN);
        edibleFoods.add(Material.MUTTON);
        edibleFoods.add(Material.SALMON);
        edibleFoods.add(Material.COD);
        edibleFoods.add(Material.ROTTEN_FLESH);
    }


    @Override
    public void run() {
        running = true;
        timer = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                World world = player.getWorld();
                if (world.getEnvironment() == World.Environment.NORMAL && world.isDayTime()) {
                    Block b = player.getWorld().getHighestBlockAt(player.getLocation());
                    if (b.getType() == Material.AIR || b.getLocation().getY() <= player.getLocation().getY()) {
                        player.setFireTicks(20 * 5);
                    }
                }
            }
        }, period, period);
    }

    @Override
    public void stop() {
        Bukkit.getServer().getScheduler().cancelTask(timer);
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @EventHandler
    public void checkFood(PlayerItemConsumeEvent event) {

        if (!isRunning()) {
            return;
        }

        if (event.getPlayer() != player) {
            return;
        }

        if (!edibleFoods.contains(event.getItem().getType())) {
            player.getWorld().spawnEntity(player.getLocation(), EntityType.LIGHTNING);
            event.setCancelled(true);
        }

    }
}
