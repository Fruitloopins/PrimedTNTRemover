package com.fwloopins.explosionremover;

import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public final class ExplosionRemover extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        event.setCancelled(true); // Just cancel them all since I can't be bothered babysitting

        createEffects(event.getLocation());
    }

    private void createEffects(Location location) {
        World world = location.getWorld();

        Random random = new Random();
        world.playSound(location, Sound.ENTITY_CREEPER_DEATH, 1.0F, random.nextFloat(1.5F, 2.0F));
        world.spawnParticle(Particle.REDSTONE, location, 1, new Particle.DustOptions(Color.BLACK, 1.5F));
    }
}
