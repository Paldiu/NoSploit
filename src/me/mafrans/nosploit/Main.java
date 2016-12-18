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
    public FileConfiguration config;
    public Server server;
    public List<Player> jukeboxPlayers;
    public List<Player> leverPlayers;

    @Override
    public void onEnable()
    {
        plugin = this;
        config = this.getConfig();
        server = this.getServer();
        jukeboxPlayers = new ArrayList();
        leverPlayers = new ArrayList();

        plugin.saveDefaultConfig();
        Data.registerData();

        server.getPluginManager().registerEvents(new PluginListener(), plugin);

        plugin.getCommand("getnbt").setExecutor(new Command_getnbt());
    }
}
