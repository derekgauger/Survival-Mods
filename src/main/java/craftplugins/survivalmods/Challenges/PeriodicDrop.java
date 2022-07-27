package craftplugins.survivalmods.Challenges;

import craftplugins.survivalmods.SurvivalMods;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Random;

public class PeriodicDrop implements Challenge{

    long period;
    SurvivalMods plugin;
    Player player;
    private int timer;
    private boolean running;

    public PeriodicDrop(SurvivalMods plugin, Player player, int seconds) {
        this.plugin = plugin;
        this.player = player;
        this.period = seconds * 20L;

    }

    @Override
    public void run() {
        running = true;
        timer = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                ItemStack handItem = player.getItemInHand();
                player.getInventory().remove(handItem);

                player.getWorld().dropItem(player.getLocation().setDirection(player.getVelocity()), handItem);
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
}