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


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Handle commands related to hats.
 *
 * @author rylinaux
 */
public class HatCommands implements CommandExecutor {

    /**
     * The plugin instance.
     */
    private final Hat plugin;

    /**
     * Construct the object.
     *
     * @param plugin the plugin instance.
     */
    public HatCommands(Hat plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (!(player.hasPermission("hat.use"))) {
            player.sendMessage(ChatColor.RED + "You do not have permission to do that.");
            return true;
        }

        if (args.length == 0) {

            if (plugin.getHatManager().hasHat(player)) {
                plugin.getHatManager().removeHat(player);
                player.sendMessage(Hat.PREFIX + "Your hat has been removed and your previous item restored.");
            } else {
                player.sendMessage(Hat.PREFIX + "Use '/hat <id>' or '/hat <name>' to get a hat.");
            }

            return true;

        }

        String[] data = args[0].split(":");


        if (data.length == 0 || Material.matchMaterial(data[0]) == null) {
            player.sendMessage(Hat.PREFIX + "Not a valid material.");
            return true;
        }

        Material material = Material.matchMaterial(data[0]);

        ItemStack item;

        if (data.length > 1) {
            item = new ItemStack(material, 1, Short.parseShort(data[1]));
        } else {
            item = new ItemStack(material, 1);
        }

        if (plugin.getHatManager().hasHat(player)) {
            plugin.getHatManager().updateHat(player, item);
        } else {
            plugin.getHatManager().addHat(player, item);
        }

        player.sendMessage(Hat.PREFIX + "Your hat has been set to " + material.name() + ".");

        return true;

    }

}
