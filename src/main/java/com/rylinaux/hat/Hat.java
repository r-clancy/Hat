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

import com.rylinaux.hat.listener.HatListener;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * A plugin that allows players to wear items as if they were hats.
 *
 * @author rylinaux
 */
public class Hat extends JavaPlugin {

    /**
     * The prefix of the plugin.
     */
    public static final String PREFIX = ChatColor.GRAY + "[" + ChatColor.GREEN + "Hat" + ChatColor.GRAY + "] ";

    /**
     * The hat manager.
     */
    private HatManager hatManager;

    @Override
    public void onEnable() {
        this.hatManager = new HatManager();
        this.getCommand("hat").setExecutor(new HatCommands(this));
        this.getServer().getPluginManager().registerEvents(new HatListener(this), this);
    }

    @Override
    public void onDisable() {
        hatManager.removeAllHats();
        hatManager = null;
    }

    /**
     * Get the hat manager.
     *
     * @return the hat manager.
     */
    public HatManager getHatManager() {
        return hatManager;
    }

}