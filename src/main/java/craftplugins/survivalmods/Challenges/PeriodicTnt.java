package craftplugins.survivalmods.Challenges;

import craftplugins.survivalmods.SurvivalMods;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class PeriodicTnt implements Challenge {

    long period;
    SurvivalMods plugin;
    Player player;
    private int timer;
    private boolean running;

    public PeriodicTnt(SurvivalMods plugin, Player player, int seconds) {
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
                player.getWorld().spawnEntity(player.getLocation(), EntityType.PRIMED_TNT);
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
