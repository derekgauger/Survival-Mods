package craftplugins.survivalmods.Challenges;

import craftplugins.survivalmods.SurvivalMods;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ExplosiveProjectiles implements Listener, Challenge {

    SurvivalMods plugin;
    private boolean running;
    private Player player;

    public ExplosiveProjectiles(SurvivalMods plugin, Player player) {
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
    public void onProjectileImpact(ProjectileHitEvent event) {

        if (!isRunning()) {
            return;
        }

        Entity hitEntity = event.getHitEntity();

        if (hitEntity != player) {
            return;
        }

        if (hitEntity != null) {
            hitEntity.getLocation().createExplosion(2);

        }
    }
}
