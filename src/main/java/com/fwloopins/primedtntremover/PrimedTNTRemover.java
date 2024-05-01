package com.fwloopins.primedtntremover;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.TNTPrimeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public final class PrimedTNTRemover extends JavaPlugin implements Listener {

    public static final List<UUID> WARNED_PLAYERS = new ArrayList<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onTNTPrime(TNTPrimeEvent event) {
        event.setCancelled(true);

        Block block = event.getBlock();
        block.setType(Material.AIR);

        World world = block.getWorld();
        Location location = block.getLocation();

        Random random = new Random();
        world.playSound(location, Sound.ENTITY_CREEPER_DEATH, 1.0F, random.nextFloat(1.5F, 2.0F));
        world.spawnParticle(Particle.REDSTONE, location.add(0.5D, 0.5D, 0.5D), 1, new Particle.DustOptions(Color.BLACK, 1F));

        if (!(event.getPrimingEntity() instanceof Player player)) return;
        if (WARNED_PLAYERS.contains(player.getUniqueId())) return;

        player.sendMessage(Component.text("You are not allowed to prime TNT on this server", NamedTextColor.RED));
        WARNED_PLAYERS.add(player.getUniqueId());
    }
}
