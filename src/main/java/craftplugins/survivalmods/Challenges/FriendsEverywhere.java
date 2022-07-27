package craftplugins.survivalmods.Challenges;

import craftplugins.survivalmods.SurvivalMods;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;
import java.util.List;

public class FriendsEverywhere implements Challenge, Listener {

    SurvivalMods plugin;
    private final Player player;
    private boolean running;
    private List<EntityType> friends = new ArrayList<>();

    public FriendsEverywhere(SurvivalMods plugin, Player player) {
        this.plugin = plugin;
        this.player = player;

        instantiateFriends();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void instantiateFriends() {
        friends.add(EntityType.PIG);
        friends.add(EntityType.COW);
        friends.add(EntityType.MUSHROOM_COW);
        friends.add(EntityType.WOLF);
        friends.add(EntityType.RABBIT);
        friends.add(EntityType.HORSE);
        friends.add(EntityType.DONKEY);
        friends.add(EntityType.SHEEP);
        friends.add(EntityType.SALMON);
        friends.add(EntityType.TROPICAL_FISH);
        friends.add(EntityType.AXOLOTL);
        friends.add(EntityType.CAT);
        friends.add(EntityType.CHICKEN);
        friends.add(EntityType.FOX);
        friends.add(EntityType.FROG);
        friends.add(EntityType.GOAT);
        friends.add(EntityType.LLAMA);
        friends.add(EntityType.TRADER_LLAMA);
        friends.add(EntityType.PANDA);
        friends.add(EntityType.MULE);
        friends.add(EntityType.PARROT);
        friends.add(EntityType.BAT);
        friends.add(EntityType.COD);
        friends.add(EntityType.DOLPHIN);
        friends.add(EntityType.SQUID);
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
    public void onFriendHit(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }

        Player damager = (Player) event.getDamager();

        if (damager != player) {
            return;
        }

        if (friends.contains(event.getEntity().getType())) {
            player.damage(3.0D);
        }
    }
}
