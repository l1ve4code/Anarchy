package ru.draiget.nova.events;


import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import ru.draiget.nova.main.Main;

public class CheckLVL implements Listener {

	private Main plugin;
	
	public CheckLVL(Main plugin) {
		this.plugin = plugin;
	}
	
	public int getX(Player p, String x, String type, int j){
		
		int i = plugin.privat.getInt("players." + type + "." + j + "." + x);
		
		return i;
	}
	public int getY(Player p, String y, String type, int j){
		
		int i = plugin.privat.getInt("players." + type + "." + j + "." + y);
		
		return i;
	}
	public int getZ(Player p, String z, String type, int j){
		
		int i = plugin.privat.getInt("players." + type + "." + j + "." + z);
		
		return i;
	}
		
	public int getXB(Player p, String x, String type, int j){
		
		int i = plugin.privat.getInt("players." + type + "." + j + ".block." + x);
		
		return i;
	}
	public int getYB(Player p, String y, String type, int j){
		
		int i = plugin.privat.getInt("players." + type + "." + j + ".block." + y);
		
		return i;
	}
	public int getZB(Player p, String z, String type, int j){
		
		int i = plugin.privat.getInt("players." + type + "." + j + ".block." + z);
		
		return i;
	}	
	


	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void checkPos(PlayerMoveEvent e){
		
		Player p = e.getPlayer();
		
		String clan_name = plugin.clan.getString("players." + p.getUniqueId() + ".clan_name");
		
		Location loc = p.getLocation();
		
		int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();
		
		if(!clan_name.equalsIgnoreCase("0")){
				
			    int i = 0;
			    
			    int result_1 = 0;
			    int result_2 = 0;
			    int result_3 = 0;
			    
				while(i < plugin.privat.getInt("players.plrs_e")){
					
					i++;
					
					String uuid_private = plugin.privat.getString("players.emerald." + i + ".uuid");
					
					int x_min = getX(p, "x_min", "emerald", i);
					int y_min = getY(p, "y_min", "emerald", i);
					int z_min = getZ(p, "z_min", "emerald", i);
					
					int x_max = getX(p, "x_max", "emerald", i);
					int y_max = getY(p, "y_max", "emerald", i);
					int z_max = getZ(p, "z_max", "emerald", i);
					
					if(x >= x_min && x <= x_max){
						
						while(x_min <= x_max){
							
							x_min++;
							if(x_min == x){
								
								result_1 = 1;
								
							}
							
						}
						
					}
					if(y >= y_min && y <= y_max){
						
						while(y_min <= y_max){
							
							y_min++;
							if(y_min == y){
								
								result_2 = 1;
								
							}
							
						}
						
					}
					if(z >= z_min && z <= z_max){
						
						while(z_min <= z_max){
							
							z_min++;
							if(z_min == z){
								
								result_3 = 1;
								
							}
							
						}
						
					}
				
					if(result_1 == 1 && result_2 == 1 && result_3 == 1){
						
						if(clan_name.equalsIgnoreCase(plugin.clan.getString("players." + uuid_private + ".clan_name"))){
						
						result_1 = 0;
						result_2 = 0;
						result_3 = 0;
						
						if(plugin.clan.getInt("clans." + clan_name + ".lvl") == 5){		
						p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, 0));
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 0));
						p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200, 1));
						}
						
						if(plugin.clan.getInt("clans." + clan_name + ".lvl") > 3 &&  plugin.clan.getInt("clans." + clan_name + ".lvl") < 5 ){
							
							p.setMaxHealth(24);
							
						}
						if(plugin.clan.getInt("clans." + clan_name + ".lvl") == 6){
							
							p.setMaxHealth(26);
							p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 0));
							
						}
						if(plugin.clan.getInt("clans." + clan_name + ".lvl") == 7){
							
							p.setMaxHealth(28);
							
						}
						if(plugin.clan.getInt("clans." + clan_name + ".lvl") == 8){
							
							p.setMaxHealth(34);
							
						}
						if(plugin.clan.getInt("clans." + clan_name + ".lvl") == 9){
							
							p.setMaxHealth(36);
							p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 0));
							
						}
						if(plugin.clan.getInt("clans." + clan_name + ".lvl") == 0){
							
							p.setMaxHealth(20);
							
						}
						
						}else{
							
							result_1 = 0;
							result_2 = 0;
							result_3 = 0;
							
							
						}
					
				}
			}
		
	}else{
		
		p.setMaxHealth(20);
		
	  }	
			
	}
}	

