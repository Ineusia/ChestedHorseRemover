package gq.ineusia;

import org.bukkit.ChatColor;
import org.bukkit.entity.ChestedHorse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {

    }

    @EventHandler
    public void onOpen(InventoryOpenEvent event) {
        if (event.getInventory().getHolder() instanceof ChestedHorse) {
            ChestedHorse entity = ((ChestedHorse) event.getInventory().getHolder());

            entity.remove();

            int X = entity.getLocation().getBlockX();
            int Y = entity.getLocation().getBlockY();
            int Z = entity.getLocation().getBlockZ();

            String worldName = entity.getLocation().getWorld().getName();

            String playerName = event.getPlayer().getName();

            getLogger().log(Level.INFO, String.format("%s opened the inventory of a(n) %s", playerName, entity.getType().toString()));
            getLogger().log(Level.INFO, String.format("Removing %s at (%d, %d, %d, %s)", entity.getType().toString(), X, Y, Z, worldName));

            getServer().broadcast(String.format(ChatColor.DARK_RED + "%s opened the inventory of a(n) %s", playerName, entity.getType().toString()), "dupe.notify");
            getServer().broadcast(String.format(ChatColor.DARK_RED + "Removing %s at (%d, %d, %d, %s)", entity.getType().toString(), X, Y, Z, worldName), "dupe.notify");
        }
    }
}
