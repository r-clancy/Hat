package com.rylinaux.hat.listener;

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

import com.rylinaux.hat.Hat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Handle events dealing with hats.
 *
 * @author rylinaux
 */
public class HatListener implements Listener {

    /**
     * The plugin instance.
     */
    private final Hat plugin;

    /**
     * Construct the object.
     *
     * @param plugin the plugin instance.
     */
    public HatListener(Hat plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        // If the player has a has, remove it.
        if (plugin.getHatManager().hasHat(event.getPlayer())) {
            plugin.getHatManager().removeHat(event.getPlayer());
        }
    }

    @EventHandler
    public void onInteract(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        // Cancel if the player has a hat and tries to edit the helmet slot
        if (plugin.getHatManager().hasHat(player)) {
            if (event.getSlot() == 39) {
                player.sendMessage(Hat.PREFIX + "You can't remove your hat! To restore your previous item, do '/hat'.");
                event.setCancelled(true);
            }
        }
    }

}
