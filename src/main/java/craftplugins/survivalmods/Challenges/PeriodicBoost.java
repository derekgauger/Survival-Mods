package craftplugins.survivalmods.Challenges;

import craftplugins.survivalmods.SurvivalMods;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class PeriodicBoost implements Challenge {

    long period;
    SurvivalMods plugin;
    Player player;
    private int timer;
    private boolean running;

    public PeriodicBoost(SurvivalMods plugin, Player player, int seconds) {
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
                player.setVelocity(new Vector(0,10,0));
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
