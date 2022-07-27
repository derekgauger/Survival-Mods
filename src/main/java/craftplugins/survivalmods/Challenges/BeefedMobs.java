package craftplugins.survivalmods.Challenges;

import craftplugins.survivalmods.SurvivalMods;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BeefedMobs implements Listener, Challenge {

    SurvivalMods plugin;
    List<EntityType> armorMobs = new ArrayList<>();
    private boolean running;

    public BeefedMobs(SurvivalMods plugin) {
        this.plugin = plugin;

        instantiateArmorMobList();
        Bukkit.getPluginManager().registerEvents(this, plugin);
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

    public void instantiateArmorMobList() {
        armorMobs.add(EntityType.ZOMBIE);
        armorMobs.add(EntityType.ZOMBIE_VILLAGER);
        armorMobs.add(EntityType.HUSK);
        armorMobs.add(EntityType.SKELETON);
        armorMobs.add(EntityType.PIGLIN);
        armorMobs.add(EntityType.ZOMBIFIED_PIGLIN);
        armorMobs.add(EntityType.PIGLIN_BRUTE);
        armorMobs.add(EntityType.DROWNED);
        armorMobs.add(EntityType.WITHER_SKELETON);
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {

        if (!isRunning()) {
            return;
        }

        if (event.getEntity() instanceof Player) {
            return;
        }

        Entity entity = event.getEntity();

        if (!armorMobs.contains(entity.getType())) {
            return;
        }

        ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS, 1);
        ItemStack pants = new ItemStack(Material.NETHERITE_LEGGINGS, 1);
        ItemStack chestplate = new ItemStack(Material.NETHERITE_CHESTPLATE, 1);
        ItemStack helmet = new ItemStack(Material.NETHERITE_HELMET, 1);

        ((Mob) entity).getEquipment().setHelmet(helmet);
        ((Mob) entity).getEquipment().setChestplate(chestplate);
        ((Mob) entity).getEquipment().setLeggings(pants);
        ((Mob) entity).getEquipment().setBoots(boots);


    }
}
