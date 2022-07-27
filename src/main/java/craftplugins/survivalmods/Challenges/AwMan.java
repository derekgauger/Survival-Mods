package craftplugins.survivalmods.Challenges;

import craftplugins.survivalmods.SurvivalMods;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class AwMan implements Challenge, Listener {

    SurvivalMods plugin;
    private boolean running;
    private Player player;

    public AwMan(SurvivalMods plugin, Player player) {
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
    public void onCreeperDeath(EntityDeathEvent event) {
        Entity killer = event.getEntity().getKiller();

        if (killer != player) {
            return;
        }

        if (event.getEntity().getType() != EntityType.CREEPER) {
            return;
        }

        player.getWorld().spawnEntity(player.getLocation(),EntityType.CREEPER);

    }

}
