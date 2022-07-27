package craftplugins.survivalmods.Challenges;

import craftplugins.survivalmods.SurvivalMods;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class TwinkleToes implements Challenge, Listener {

    SurvivalMods plugin;
    private final Player player;
    private boolean running;

    public TwinkleToes(SurvivalMods plugin, Player player) {
        this.plugin = plugin;
        this.player = player;

        Bukkit.getServer().getPluginManager().registerEvents(this,plugin);

    }

    @Override
    public void run() {
        running = true;
    }

    @Override
    public void stop() {
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @EventHandler
    public void onFallDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof LivingEntity)) {
            return;
        }

        LivingEntity entity = (LivingEntity) event.getEntity();

        if (entity != player) {
            return;
        }

        if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            entity.setHealth(0);
        }
    }
}
