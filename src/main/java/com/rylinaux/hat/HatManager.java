package com.rylinaux.hat;

/*
 * #%L
 * Hat
 * %%
 * Copyright (C) 2014 Hat
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Manage hats for players.
 *
 * @author rylinaux
 */
public class HatManager {

    /**
     * Map of players who have hats and their original helmets.
     */
    private final Map<UUID, ItemStack> players = new HashMap<>();

    /**
     * Add a hat for a user who doesn't already have one.
     *
     * @param player the player.
     * @param item the hat item.
     */
    public void addHat(Player player, ItemStack item) {
        addHat(player.getUniqueId(), item);
    }

    /**
     * Add a hat for a user who doesn't already have one.
     *
     * @param uuid the player's uuid.
     * @param item the old helmet item.
     */
    public void addHat(UUID uuid, ItemStack item) {

        Player player = Bukkit.getPlayer(uuid);

        // Store the old helmet item before the new one is put on.
        players.put(uuid, player.getInventory().getHelmet());

        player.getInventory().setHelmet(item);

    }

    /**
     * Update the player's current helmet without altering the original helmet item.
     *
     * @param player the player.
     * @param item the new hat item.
     */
    public void updateHat(Player player, ItemStack item) {
        updateHat(player.getUniqueId(), item);
    }

    /**
     * Update the player's current helmet without altering the original helmet item.
     *
     * @param uuid the player's uuid.
     * @param item the new hat item.
     */
    public void updateHat(UUID uuid, ItemStack item) {
        Bukkit.getPlayer(uuid).getInventory().setHelmet(item);
    }

    /**
     * Remove a hat from a player and restore the original helmet.
     *
     * @param player the player.
     */
    public void removeHat(Player player) {
        removeHat(player.getUniqueId());
    }

    /**
     * Remove a hat from a player and restore the original helmet.
     *
     * @param uuid the player's uuid.
     */
    public void removeHat(UUID uuid) {

        // Set the item back to the original.
        Bukkit.getPlayer(uuid).getInventory().setHelmet(players.get(uuid));

        // Remove them from our tracked list.
        players.remove(uuid);

    }

    /**
     * Remove hats from all players.
     */
    public void removeAllHats() {
        for(UUID uuid : players.keySet())
            removeHat(uuid);
    }

    /**
     * Check if the player has a hat on.
     *
     * @param player the player.
     * @return true, if the player has a hat.
     */
    public boolean hasHat(Player player) {
        return hasHat(player.getUniqueId());
    }

    /**
     * Check if the player has a hat on.
     *
     * @param uuid the player's uuid.
     * @return true, if the player has a hat.
     */
    public boolean hasHat(UUID uuid) {
        return players.containsKey(uuid);
    }

    /**
     * Check if the player has a helmet on.
     *
     * @param player the player.
     * @return true, if the player has a helmet.
     */
    public boolean hasHelmet(Player player) {
        return hasHelmet(player.getUniqueId());
    }

    /**
     * Check if the player has a helmet on.
     *
     * @param uuid the player's uuid.
     * @return true, if the player has a helmet.
     */
    public boolean hasHelmet(UUID uuid) {
        return Bukkit.getPlayer(uuid).getInventory().getHelmet() != null;
    }

    public Map<UUID, ItemStack> getPlayers() {
        return players;
    }

}
