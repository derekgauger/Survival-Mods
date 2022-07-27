package craftplugins.survivalmods;

import org.bukkit.plugin.java.JavaPlugin;

public final class SurvivalMods extends JavaPlugin {

    ChallengeHandler ch;

    @Override
    public void onEnable() {
        // Plugin startup logic
        new Utils(this);
        ch = new ChallengeHandler(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
