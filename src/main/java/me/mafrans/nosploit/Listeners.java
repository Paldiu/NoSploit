/*
 * Copyright 2016 NoSploit
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 *
 * @author Mafrans
 */
package me.mafrans.nosploit;

import me.mafrans.nosploit.Main;
import me.mafrans.nosploit.listeners.*;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public enum Listeners {

    BOOK(new BookPatch(), "stripBookJSON"),
    CHEST(new ChestPatch(), "chests"),
    CRASHBLOCK(new CrashblockPatch(), "crashblocks"),
    HOTBAR(new HotbarPatch(), "safehotbar"),
    JUKEBOX(new JukeboxPatch(), "jukeboxes"),
    INTERACT(new InteractCooldown(), "interact"),
    MOB(new MobPatch(), "mobs"),
    SIGN(new SignPatch(), "signs"),
    SPAWNER(new SpawnerPatch(), "spawners");

    Listeners(Listener executor, String configEntry) {
        this.executor = executor;
        this.configEntry = configEntry;
    }
    public final Listener executor;
    public final String configEntry;

    private static final Main plugin = Main.plugin;

    public static void registerListeners(PluginManager manager) {
        for (Listeners value : Listeners.values()) {
            if (isActivated(value.configEntry)) {
                NLogger.info("Event registered: " + value.toString());
                manager.registerEvents(value.executor, plugin);
            }
        }
    }

    public static boolean isActivated(String path) {
        return plugin.config.getStringList("modules").contains(path);
    }
}
