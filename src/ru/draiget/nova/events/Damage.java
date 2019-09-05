package ru.draiget.nova.events;

import java.io.IOException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import ru.draiget.nova.main.Main;

public class Damage implements Listener{

	private Main plugin;
	
	public Damage(Main plugin) {
		this.plugin = plugin;
	}

	
	@EventHandler
	public void killEnemy(PlayerDeathEvent e){
		
		Player p = e.getEntity().getKiller();
		
		String clan_name = plugin.clan.getString("players." + p.getUniqueId() + ".clan_name").toLowerCase();
		
		int kills = plugin.clan.getInt("clans." + clan_name + ".kills");
		int money = plugin.clan.getInt("clans." + clan_name + ".money");
		
		int all_kills = kills + 1;
		int all_money = money + 10;
		
		plugin.clan.set("clans." + clan_name + ".kills", all_kills);
		plugin.clan.set("clans." + clan_name + ".money", all_money);
		
		try {
			plugin.clan.save(plugin.clanFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	@EventHandler
	public void getDamage(EntityDamageByEntityEvent e){
		
		
		if(e.getDamager() instanceof Player){
			
			Player p1 = (Player) e.getDamager(); 
			
			Player p2 = (Player) e.getEntity();
			
			String name_p1 = plugin.clan.getString("players." + p1.getUniqueId() + ".clan_name"); 
			String name_p2 = plugin.clan.getString("players." + p2.getUniqueId() + ".clan_name"); 
			
			if(name_p1.equalsIgnoreCase(name_p2)){
				
				String ff = plugin.friendly_fire.get(name_p1);
				
				if(ff.equalsIgnoreCase("on")){
					
					return;
				}
				else if(ff.equalsIgnoreCase("off")){
					
					
					e.setCancelled(true);
				}
				
			}
			
		}
		
	}
	
}
