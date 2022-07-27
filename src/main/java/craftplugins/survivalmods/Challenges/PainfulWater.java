package craftplugins.survivalmods.Challenges;

import craftplugins.survivalmods.SurvivalMods;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PainfulWater implements Listener, Challenge {

    SurvivalMods plugin;
    private final Player player;
    private boolean running;
    private boolean takeDamage;
    private final long period;
    private int timer;

    public PainfulWater(SurvivalMods plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
        this.period = 20;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void run() {
        running = true;
        timer = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                if (takeDamage) {
                    player.damage(2.0D);
                }
            }
        }, 0, period);
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
    public void inLiquid(PlayerMoveEvent event) {
        if (!isRunning()) {
            return;
        }

        if (event.getPlayer() != player) {
            return;
        }

        Material m = event.getPlayer().getLocation().getBlock().getType();

        takeDamage = m == Material.WATER;
    }
}
