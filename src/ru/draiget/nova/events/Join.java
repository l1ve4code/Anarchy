package ru.draiget.nova.events;

import java.io.IOException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ru.draiget.nova.main.Main;

public class Join implements Listener {

	private Main plugin;
	
	public Join(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void playerJoin(PlayerJoinEvent e){
		
		Player p = e.getPlayer();
		
		if(!plugin.clan.contains("players." + p.getUniqueId())){
			
			plugin.clan.set("players." + p.getUniqueId() + ".name" , p.getName());
			plugin.clan.set("players." + p.getUniqueId() + ".clan_name" , 0);
			plugin.clan.set("players." + p.getUniqueId() + ".clan_tag" , 0);
			plugin.clan.set("players." + p.getUniqueId() + ".in_clan" , false);
			
			try {
				plugin.clan.save(plugin.clanFile);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
}
