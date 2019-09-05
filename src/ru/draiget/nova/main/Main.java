package ru.draiget.nova.main;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import ru.draiget.nova.commands.Commands;
import ru.draiget.nova.events.Blocks;
import ru.draiget.nova.events.ChatEvent;
import ru.draiget.nova.events.CheckLVL;
import ru.draiget.nova.events.Damage;
import ru.draiget.nova.events.Join;
import ru.draiget.nova.gui.Interface;

public class Main extends JavaPlugin {

	public File clanFile;
	public FileConfiguration clan;
	
	public HashMap<String, Material> trade = new HashMap<String, Material>(); 
	
	public HashMap<String, String> friendly_fire = new HashMap<String, String>();
	
	public HashMap<String, Integer> coords = new HashMap<String, Integer>();
	
	public HashMap<String, Integer> amount = new HashMap<String, Integer>();
	
	public HashMap<String, Integer> summ = new HashMap<String, Integer>();
	
	public HashMap<String, Integer> lvl_clan = new HashMap<String, Integer>();
	
	public File privatFile;
	public FileConfiguration privat;
		
	public void onEnable(){
		
		Bukkit.getPluginManager().registerEvents(new Join(this), this);
		Bukkit.getPluginManager().registerEvents(new Damage(this), this);
		Bukkit.getPluginManager().registerEvents(new ChatEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new Blocks(this), this);
		Bukkit.getPluginManager().registerEvents(new Interface(this), this);
		Bukkit.getPluginManager().registerEvents(new CheckLVL(this), this);
		
		getCommand("aclan").setExecutor(new Commands(this));
		
		clanFile = new File(getDataFolder() + File.separator + "clan.yml");
		clan = YamlConfiguration.loadConfiguration(clanFile);
		clan.options().copyDefaults(true);
		
		privatFile = new File(getDataFolder() + File.separator + "private.yml");
		privat = YamlConfiguration.loadConfiguration(privatFile);
		privat.options().copyDefaults(true);
		
		try {
			privat.save(privatFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			clan.save(clanFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void onDisable(){
		
		try {
			privat.save(privatFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			clan.save(clanFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
