package ru.draiget.nova.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import ru.draiget.nova.main.Main;

public class ChatEvent implements Listener {

	private Main plugin;
	
	public ChatEvent(Main plugin) {
		this.plugin = plugin;
	}

	
	@EventHandler
	public void lastSaid(AsyncPlayerChatEvent e){
		
		Player p = e.getPlayer();
		
		String clan_name = plugin.clan.getString("players." + e.getPlayer().getUniqueId() + ".clan_tag");
		
		if(!clan_name.equalsIgnoreCase("0")){
			
			clan_name = clan_name.replace("&", "\u00a7");
			
		    e.setFormat(clan_name + ChatColor.RESET + " * > [" + p.getName() + "] " + e.getMessage());
			
		}else {
			
			e.setFormat("[" + p.getName() + "] " + e.getMessage());
			
			return;
			
		}
		
	}
	
}
