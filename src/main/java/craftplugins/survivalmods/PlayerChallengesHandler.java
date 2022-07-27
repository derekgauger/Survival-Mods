package craftplugins.survivalmods;

import craftplugins.survivalmods.Challenges.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerChallengesHandler implements Listener {

    public Player player;
    public Inventory challengesInv = Bukkit.createInventory(null,27, "Challenges");
    SurvivalMods plugin;
    List<Challenge> challenges = new ArrayList<>();

    public PlayerChallengesHandler(SurvivalMods plugin, Player player) {
        this.plugin = plugin;
        this.player = player;

        initializeChallenges();

        Bukkit.getPluginManager().registerEvents(this, plugin);

    }

    private void initializeChallenges() {
        int emptyCount = 1;
        challengesInv.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        challengesInv.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        challengesInv.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        challengesInv.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        challengesInv.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        challengesInv.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        challengesInv.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        challengesInv.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        challengesInv.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        challengesInv.addItem(createGuiItem(Material.TNT, Utils.chat("&b&lPeriodic TNT"), Utils.chat("&aSpawns TNT every 30 seconds"), Utils.chat("&cDisabled")));
//        challengesInv.addItem(createGuiItem(Material.COOKED_BEEF, Utils.chat("&b&lBeefed Mobs"), Utils.chat("&aAdds armor and other upgrades to mobs"), Utils.chat("&cDisabled")));
        challengesInv.addItem(createGuiItem(Material.PISTON, Utils.chat("&b&lPeriodic Boost"), Utils.chat("&aBoosts you in the air periodically"), Utils.chat("&cDisabled")));
        challengesInv.addItem(createGuiItem(Material.WATER_BUCKET, Utils.chat("&b&lPainful Water"), Utils.chat("&aTouching water will hurt you"), Utils.chat("&cDisabled")));
        challengesInv.addItem(createGuiItem(Material.SNOWBALL, Utils.chat("&b&lExplosive Projectiles"), Utils.chat("&aProjectiles that hit you will explode"), Utils.chat("&cDisabled")));
        challengesInv.addItem(createGuiItem(Material.DROPPER, Utils.chat("&b&lPeriodic Drop"), Utils.chat("&aPeriodically trip and drop the item you are holding"), Utils.chat("&cDisabled")));
        challengesInv.addItem(createGuiItem(Material.BAT_SPAWN_EGG, Utils.chat("&b&lVampirism"), Utils.chat("&aDon't go in sunlight, only eat raw meat"), Utils.chat("&cDisabled")));
        challengesInv.addItem(createGuiItem(Material.FEATHER, Utils.chat("&b&lTwinkle Toes"), Utils.chat("&aAny fall damage will kill you"), Utils.chat("&cDisabled")));
        challengesInv.addItem(createGuiItem(Material.PORKCHOP, Utils.chat("&b&lFriends Everywhere"), Utils.chat("&aDon't hit friendly animals"), Utils.chat("&cDisabled")));
        challengesInv.addItem(createGuiItem(Material.CREEPER_SPAWN_EGG, Utils.chat("&b&lAwwww Man..."), Utils.chat("&aAfter killing a creeper, another creeper will spawn"), Utils.chat("&cDisabled")));
        challengesInv.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        challengesInv.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        challengesInv.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        challengesInv.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        challengesInv.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        challengesInv.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        challengesInv.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        challengesInv.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
        challengesInv.addItem(createGuiItem(Material.GRAY_STAINED_GLASS_PANE,Utils.chat("&4&lEmpty " + emptyCount++),""));
    }

    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);

        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }

    public void openInventory() {
        player.openInventory(challengesInv);
    }

    @EventHandler
    public void onInventoryDrag(final InventoryDragEvent e) {
        if (e.getInventory() == challengesInv) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory() != challengesInv) {
            return;
        }

        event.setCancelled(true);

        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem == null || clickedItem.getType() == Material.AIR) {
            return;
        }

        if (clickedItem.getType() == Material.TNT) {
            Challenge c = getChallenge(PeriodicTnt.class);
            toggleChallenge(clickedItem, c);

//        } else if (clickedItem.getType() == Material.COOKED_BEEF) {
//            Challenge c = getChallenge(BeefedMobs.class);
//            toggleChallenge(clickedItem,c);

        } else if (clickedItem.getType() == Material.PISTON) {
            Challenge c = getChallenge(PeriodicBoost.class);
            toggleChallenge(clickedItem,c);

        } else if (clickedItem.getType() == Material.WATER_BUCKET) {
            Challenge c = getChallenge(PainfulWater.class);
            toggleChallenge(clickedItem,c);

        } else if (clickedItem.getType() == Material.SNOWBALL) {
            Challenge c = getChallenge(ExplosiveProjectiles.class);
            toggleChallenge(clickedItem,c);

        } else if (clickedItem.getType() == Material.DROPPER) {
            Challenge c = getChallenge(PeriodicDrop.class);
            toggleChallenge(clickedItem,c);

        } else if (clickedItem.getType() == Material.BAT_SPAWN_EGG) {
            Challenge c = getChallenge(Vampirism.class);
            toggleChallenge(clickedItem,c);

        } else if (clickedItem.getType() == Material.FEATHER) {
            Challenge c = getChallenge(TwinkleToes.class);
            toggleChallenge(clickedItem,c);

        } else if (clickedItem.getType() == Material.PORKCHOP) {
            Challenge c = getChallenge(FriendsEverywhere.class);
            toggleChallenge(clickedItem,c);

        } else if (clickedItem.getType() == Material.CREEPER_SPAWN_EGG) {
            Challenge c = getChallenge(AwMan.class);
            toggleChallenge(clickedItem,c);

        }
    }

    private Challenge createNewChallenge(Class cls) {
        Challenge c = null;

        if (PeriodicTnt.class == cls) {
            c = new PeriodicTnt(plugin,player, 5);
            challenges.add(c);

//        } else if (BeefedMobs.class == cls) {
//            c = new BeefedMobs(plugin);
//            challenges.add(c);

        } else if (PeriodicBoost.class == cls) {
            c = new PeriodicBoost(plugin,player,5);
            challenges.add(c);

        } else if (PainfulWater.class == cls) {
            c = new PainfulWater(plugin,player);
            challenges.add(c);

        } else if (ExplosiveProjectiles.class == cls) {
            c = new ExplosiveProjectiles(plugin, player);
            challenges.add(c);

        } else if (PeriodicDrop.class == cls) {
            c = new PeriodicDrop(plugin,player,5);
            challenges.add(c);

        } else if (Vampirism.class == cls) {
            c = new Vampirism(plugin,player);
            challenges.add(c);

        } else if (TwinkleToes.class == cls) {
            c = new TwinkleToes(plugin, player);
            challenges.add(c);

        } else if (FriendsEverywhere.class == cls) {
            c = new FriendsEverywhere(plugin,player);
            challenges.add(c);

        } else if (AwMan.class == cls) {
            c = new AwMan(plugin,player);
            challenges.add(c);

        }

        return c;
    }

    private Challenge getChallenge(Class cls) {

        for (Challenge challenge : challenges) {
            if (challenge.getClass() == cls) {
                return challenge;
            }
        }

        return createNewChallenge(cls);
    }

    private void toggleChallenge(ItemStack item, Challenge challenge) {

        if (challenge.isRunning()) {
            challenge.stop();
        } else {
            challenge.run();
        }

        toggleLoreTag(item);

    }

    private void toggleLoreTag(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();

        String statusTag = lore.get(1);
        if (statusTag.contains("Disabled")) {
            lore.set(1, Utils.chat("&aEnabled"));
            meta.addEnchant(Enchantment.MENDING,1,true);

        } else {
            lore.set(1, Utils.chat("&cDisabled"));
            meta.removeEnchant(Enchantment.MENDING);

        }

        meta.setLore(lore);

        item.setItemMeta(meta);
    }
}
