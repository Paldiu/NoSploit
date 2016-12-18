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

import java.util.ArrayList;
import java.util.List;
import me.mafrans.nosploit.commands.Command_getnbt;
import me.mafrans.nosploit.listeners.PluginListener;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{

    public static Main plugin;
    public static String pName;
    public static String pVersion;
    public static List<String> pAuthors;
    public FileConfiguration config;
    public Server server;
    public List<Player> jukeboxPlayers;
    public List<Player> leverPlayers;

    @Override
    public void onLoad() {
        plugin = this;
        
        pName = this.getDescription().getName();
        pVersion = this.getDescription().getVersion();
        pAuthors = this.getDescription().getAuthors();
    }
    
    @Override
    public void onEnable() {
        config = this.getConfig();
        server = this.getServer();
        jukeboxPlayers = new ArrayList();
        leverPlayers = new ArrayList();

        plugin.saveDefaultConfig();
        Data.registerData();
        EntityData.registerData();

        server.getPluginManager().registerEvents(new PluginListener(), plugin);

        plugin.getCommand("getnbt").setExecutor(new Command_getnbt());
        
        NLogger.info(String.format("[%s] version %s by %s has been enabled!", 
                pName, 
                pVersion, 
                pAuthors.toString()));
    }
    
    @Override
    public void onDisable() {
        NLogger.info(pName + " has been disabled!");
    }
}
