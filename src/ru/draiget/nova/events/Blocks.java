package ru.draiget.nova.events;

import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import ru.draiget.nova.gui.Interface;
import ru.draiget.nova.main.Main;

public class Blocks implements Listener {

	private Main plugin;
	
	public Blocks(Main plugin) {
		this.plugin = plugin;
	}

	
	String prefix = ChatColor.YELLOW + "" + ChatColor.BOLD + "[" + ChatColor.RED + "NovaAnarchy" + ChatColor.YELLOW + "] " + ChatColor.RESET;
	
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
	
	public String getUUID(String type, int j){
		
		String uuid = plugin.privat.getString("players." + type + "." + j + "." + "uuid");
		
		return uuid;
	}
	
	public void save(){
		
		try {
			plugin.privat.save(plugin.privatFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public void blockPlace(Player p, BlockPlaceEvent e){
		
		Block b = e.getBlock();
		
		Location loc = b.getLocation();
		
		int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();
		
		int i = 0;
		
		int result_1 = 0;
		int result_2 = 0;
		int result_3 = 0;
		
		while(i < plugin.privat.getInt("players.plrs")){
			
			i++;
			
			int x_min = getX(p, "x_min", "diamond", i);
			int y_min = getY(p, "y_min", "diamond", i);
			int z_min = getZ(p, "z_min", "diamond", i);
			
			int x_max = getX(p, "x_max", "diamond", i);
			int y_max = getY(p, "y_max", "diamond", i);
			int z_max = getZ(p, "z_max", "diamond", i);
			
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
			
			if(p.getUniqueId().toString().equalsIgnoreCase(getUUID("diamond", i))){
				
				result_1 = 0;
				result_2 = 0;
				result_3 = 0;
				
				e.setCancelled(false);
				
			}else{
				
				result_1 = 0;
				result_2 = 0;
				result_3 = 0;
				
				e.setCancelled(true);
				
			}
			
			}
			
		}
		while(i < plugin.privat.getInt("players.plrs_i")){
			
			i++;
			
			int x_min = getX(p, "x_min", "gold", i);
			int y_min = getY(p, "y_min", "gold", i);
			int z_min = getZ(p, "z_min", "gold", i);
			
			int x_max = getX(p, "x_max", "gold", i);
			int y_max = getY(p, "y_max", "gold", i);
			int z_max = getZ(p, "z_max", "gold", i);
			
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
			
			if(p.getUniqueId().toString().equalsIgnoreCase(getUUID("gold", i))){
				
				result_1 = 0;
				result_2 = 0;
				result_3 = 0;
				
				e.setCancelled(false);
				
			}else{
				
				result_1 = 0;
				result_2 = 0;
				result_3 = 0;
				
				e.setCancelled(true);
				
			}
			
			}
			
		}
		
		String clan_owner = plugin.clan.getString("players." + p.getUniqueId() + ".clan_name");

		while(i < plugin.privat.getInt("players.plrs_e")){
			
			i++;
			
			int x_min = getX(p, "x_min", "emerald", i);
			int y_min = getY(p, "y_min", "emerald", i);
			int z_min = getZ(p, "z_min", "emerald", i);
			
			int x_max = getX(p, "x_max", "emerald", i);
			int y_max = getY(p, "y_max", "emerald", i);
			int z_max = getZ(p, "z_max", "emerald", i);
			
			String uuid_private = plugin.privat.getString("players.emerald." + i + ".uuid");
			
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
			
			if(clan_owner.equalsIgnoreCase(plugin.clan.getString("players." + uuid_private + ".clan_name"))){
				
				result_1 = 0;
				result_2 = 0;
				result_3 = 0;
				
				e.setCancelled(false);
				
			} else {
				
				result_1 = 0;
				result_2 = 0;
				result_3 = 0;
				
				e.setCancelled(true);
				
			}
				
				
		  }
		}
			
			

		/*if(plugin.privat.contains("regions." + p.getUniqueId())){
			
			Block b = e.getBlock();
			
			Location loc = b.getLocation();
			
			int x = loc.getBlockX();
			int y = loc.getBlockY();
			int z = loc.getBlockZ();
			
			int x_min = getX(p, "x_min");
			int y_min = getY(p, "y_min");
			int z_min = getZ(p, "z_min");
			
			int x_max = getX(p, "x_max");
			int y_max = getY(p, "y_max");
			int z_max = getZ(p, "z_max");
			

			if(x>=x_min && x<=x_max){
			while(x_min <= x_max){
				
				x_min++;
				
				if(x_min == x){
					
					check.put("x", 1);

				}
			}
			}else{
				
				check.put("x", 0);
				
			}
			if(y>=y_min && y<=y_max){
			while(y_min <= y_max){
				
				y_min++;
				
				if(y_min == y){
					
					check.put("y", 1);

				}
			}
			}else{
				
				check.put("y", 0);
				
			}
			if(z>=z_min && z<=z_max){
			while(z_min <= z_max){
				
				z_min++;
				
				if(z_min == z){
					
					check.put("z", 1);

				}
				
			}
			}else{
				
				check.put("z", 0);
				
			}
			
			if(check.get("x") == 1 && check.get("y") == 1 && check.get("z") == 1){
				
				check.remove("x");
				check.remove("y");
				check.remove("z");
				
				
				p.sendMessage("ÐÀÁÎÒÀÅÒ");
				
			} else {
				
				check.remove("x");
				check.remove("y");
				check.remove("z");
				
				p.sendMessage("ÍÅ ÐÎÁÈÒ");
				
			}
			
			
			
		}
		
		*/
	}
	public void blockBreak(Player p, BlockBreakEvent e){
		
		Block b = e.getBlock();
		
		Location loc = b.getLocation();
		
		int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();
		
		if(b.getType() == Material.DIAMOND_BLOCK){
		
			int i = 0;
			
			int result_1 = 0;
			int result_2 = 0;
			int result_3 = 0;
			
			while(i < plugin.privat.getInt("players.plrs")){
				
				i++;
				
				int x_min = getX(p, "x_min", "diamond", i);
				int y_min = getY(p, "y_min", "diamond", i);
				int z_min = getZ(p, "z_min", "diamond", i);
				
				int x_max = getX(p, "x_max", "diamond", i);
				int y_max = getY(p, "y_max", "diamond", i);
				int z_max = getZ(p, "z_max", "diamond", i);
				
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
				
				if(p.getUniqueId().toString().equalsIgnoreCase(getUUID("diamond", i))){
					
					result_1 = 0;
					result_2 = 0;
					result_3 = 0;
					
					e.setCancelled(false);
					
					if(x == getXB(p, "x", "diamond", i) && y == getYB(p, "y", "diamond", i) && z == getZB(p, "z", "diamond", i)){
						
						plugin.privat.set("players." + "diamond." + i, null);
						int plrs = plugin.privat.getInt("players.plrs");
						
						int j = i;
						
						while(j < plrs){
							
							j++;
							
							int change = j - 1;
							
							String name_world = plugin.privat.getString("players." + "diamond." + j + ".world");
							int xx_min = plugin.privat.getInt("players." + "diamond." + j + ".x_min");
							int yy_min = plugin.privat.getInt("players." + "diamond." + j + ".y_min");
							int zz_min = plugin.privat.getInt("players." + "diamond." + j + ".z_min");
							int xx_max = plugin.privat.getInt("players." + "diamond." + j + ".x_max");
							int yy_max = plugin.privat.getInt("players." + "diamond." + j + ".y_max");
							int zz_max = plugin.privat.getInt("players." + "diamond." + j + ".z_max");
							int xx = plugin.privat.getInt("players." + "diamond." + j + ".block" + ".x");
							int yy = plugin.privat.getInt("players." + "diamond." + j + ".block" + ".y");
							int zz = plugin.privat.getInt("players." + "diamond." + j + ".block" + ".z");
							String uuid_p = plugin.privat.getString("players." + "diamond." + j + ".uuid");
							
							plugin.privat.set("players." + "diamond." + change + ".world", name_world);
							plugin.privat.set("players." + "diamond." + change + ".x_min", xx_min);
							plugin.privat.set("players." + "diamond." + change + ".y_min", yy_min);
							plugin.privat.set("players." + "diamond." + change + ".z_min", zz_min);
							plugin.privat.set("players." + "diamond." + change + ".x_max", xx_max);
							plugin.privat.set("players." + "diamond." + change + ".y_max", yy_max);
							plugin.privat.set("players." + "diamond." + change + ".z_max", zz_max);
							plugin.privat.set("players." + "diamond." + change + ".block" + ".x", xx);
							plugin.privat.set("players." + "diamond." + change + ".block" + ".y", yy);
							plugin.privat.set("players." + "diamond." + change + ".block" + ".z", zz);
							plugin.privat.set("players." + "diamond." + change + ".uuid", uuid_p);
							
							plugin.privat.set("players." + "diamond." + j, null);
						}
						
						int amount = plrs - 1;
						plugin.privat.set("players.plrs", amount);
						
						save();
						
					}
					
					
				}else{
					
					result_1 = 0;
					result_2 = 0;
					result_3 = 0;
					
					e.setCancelled(true);
					
					
				}
			}
		}
	  } else {
		  
		    int i = 0;
		    
		    int result_1 = 0;
		    int result_2 = 0;
		    int result_3 = 0;
		  
			while(i < plugin.privat.getInt("players.plrs")){
				
				i++;
				
				int x_min = getX(p, "x_min", "diamond", i);
				int y_min = getY(p, "y_min", "diamond", i);
				int z_min = getZ(p, "z_min", "diamond", i);
				
				int x_max = getX(p, "x_max", "diamond", i);
				int y_max = getY(p, "y_max", "diamond", i);
				int z_max = getZ(p, "z_max", "diamond", i);
				
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
				
				if(p.getUniqueId().toString().equalsIgnoreCase(getUUID("diamond", i))){
					
					result_1 = 0;
					result_2 = 0;
					result_3 = 0;
					
					e.setCancelled(false);
					
					
				}else{
					
					result_1 = 0;
					result_2 = 0;
					result_3 = 0;
					
					e.setCancelled(true);
					
					
				}
				
				}
				
			}

		  
	  }
		if(b.getType() == Material.GOLD_BLOCK){
			
			int i = 0;
			
			int result_1 = 0;
			int result_2 = 0;
			int result_3 = 0;
			
			while(i < plugin.privat.getInt("players.plrs_i")){
				
				i++;
				
				int x_min = getX(p, "x_min", "gold", i);
				int y_min = getY(p, "y_min", "gold", i);
				int z_min = getZ(p, "z_min", "gold", i);
				
				int x_max = getX(p, "x_max", "gold", i);
				int y_max = getY(p, "y_max", "gold", i);
				int z_max = getZ(p, "z_max", "gold", i);
				
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
				
				if(p.getUniqueId().toString().equalsIgnoreCase(getUUID("gold", i))){
					
					result_1 = 0;
					result_2 = 0;
					result_3 = 0;
					
					e.setCancelled(false);
					
					if(x == getXB(p, "x", "gold", i) && y == getYB(p, "y", "gold", i) && z == getZB(p, "z", "gold", i)){
						
						plugin.privat.set("players." + "gold." + i, null);
						int plrs = plugin.privat.getInt("players.plrs_i");
						
						int j = i;
						
						while(j < plrs){
							
							j++;
							
							int change = j - 1;
							
							String name_world = plugin.privat.getString("players." + "gold." + j + ".world");
							int xx_min = plugin.privat.getInt("players." + "gold." + j + ".x_min");
							int yy_min = plugin.privat.getInt("players." + "gold." + j + ".y_min");
							int zz_min = plugin.privat.getInt("players." + "gold." + j + ".z_min");
							int xx_max = plugin.privat.getInt("players." + "gold." + j + ".x_max");
							int yy_max = plugin.privat.getInt("players." + "gold." + j + ".y_max");
							int zz_max = plugin.privat.getInt("players." + "gold." + j + ".z_max");
							int xx = plugin.privat.getInt("players." + "gold." + j + ".block" + ".x");
							int yy = plugin.privat.getInt("players." + "gold." + j + ".block" + ".y");
							int zz = plugin.privat.getInt("players." + "gold." + j + ".block" + ".z");
							String uuid_p = plugin.privat.getString("players." + "gold." + j + ".uuid");
							
							plugin.privat.set("players." + "gold." + change + ".world", name_world);
							plugin.privat.set("players." + "gold." + change + ".x_min", xx_min);
							plugin.privat.set("players." + "gold." + change + ".y_min", yy_min);
							plugin.privat.set("players." + "gold." + change + ".z_min", zz_min);
							plugin.privat.set("players." + "gold." + change + ".x_max", xx_max);
							plugin.privat.set("players." + "gold." + change + ".y_max", yy_max);
							plugin.privat.set("players." + "gold." + change + ".z_max", zz_max);
							plugin.privat.set("players." + "gold." + change + ".block" + ".x", xx);
							plugin.privat.set("players." + "gold." + change + ".block" + ".y", yy);
							plugin.privat.set("players." + "gold." + change + ".block" + ".z", zz);
							plugin.privat.set("players." + "gold." + change + ".uuid", uuid_p);
							
							plugin.privat.set("players." + "gold." + j, null);
						}
						
						int amount = plrs - 1;
						plugin.privat.set("players.plrs_i", amount);
						
						save();
						
					}
					
					
				}else{
					
					result_1 = 0;
					result_2 = 0;
					result_3 = 0;
					
					e.setCancelled(true);
					
				}
			}
		}
	  } else {
		  
		    int i = 0;
		    
		    int result_1 = 0;
		    int result_2 = 0;
		    int result_3 = 0;
		  
			while(i < plugin.privat.getInt("players.plrs_i")){
				
				i++;
				
				int x_min = getX(p, "x_min", "gold", i);
				int y_min = getY(p, "y_min", "gold", i);
				int z_min = getZ(p, "z_min", "gold", i);
				
				int x_max = getX(p, "x_max", "gold", i);
				int y_max = getY(p, "y_max", "gold", i);
				int z_max = getZ(p, "z_max", "gold", i);
				
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
				
				if(p.getUniqueId().toString().equalsIgnoreCase(getUUID("gold", i))){
					
					result_1 = 0;
					result_2 = 0;
					result_3 = 0;
					
					e.setCancelled(false);
					
				}else{
					
					result_1 = 0;
					result_2 = 0;
					result_3 = 0;
					
					e.setCancelled(true);
					
				}
				
				}
				
			}

		  
	  }
		String clan_owner = plugin.clan.getString("players." + p.getUniqueId() + ".clan_name");
		
		if(b.getType() == Material.EMERALD_BLOCK){
			
			int i = 0;
			
			int result_1 = 0;
			int result_2 = 0;
			int result_3 = 0;
			
			while(i < plugin.privat.getInt("players.plrs_e")){
				
				i++;
				
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
					
				if(p.getUniqueId().toString().equalsIgnoreCase(getUUID("emerald", i))){
					
					result_1 = 0;
					result_2 = 0;
					result_3 = 0;
					
					e.setCancelled(false);
					
					if(x == getXB(p, "x", "emerald", i) && y == getYB(p, "y", "emerald", i) && z == getZB(p, "z", "emerald", i)){
						
						plugin.privat.set("players." + "emerald." + i, null);
						int plrs = plugin.privat.getInt("players.plrs_e");
						
						int j = i;
						
						while(j < plrs){
							
							j++;
							
							int change = j - 1;
							
							String name_world = plugin.privat.getString("players." + "emerald." + j + ".world");
							int xx_min = plugin.privat.getInt("players." + "emerald." + j + ".x_min");
							int yy_min = plugin.privat.getInt("players." + "emerald." + j + ".y_min");
							int zz_min = plugin.privat.getInt("players." + "emerald." + j + ".z_min");
							int xx_max = plugin.privat.getInt("players." + "emerald." + j + ".x_max");
							int yy_max = plugin.privat.getInt("players." + "emerald." + j + ".y_max");
							int zz_max = plugin.privat.getInt("players." + "emerald." + j + ".z_max");
							int xx = plugin.privat.getInt("players." + "emerald." + j + ".block" + ".x");
							int yy = plugin.privat.getInt("players." + "emerald." + j + ".block" + ".y");
							int zz = plugin.privat.getInt("players." + "emerald." + j + ".block" + ".z");
							String uuid_p = plugin.privat.getString("players." + "emerald." + j + ".uuid");
							
							plugin.privat.set("players." + "emerald." + change + ".world", name_world);
							plugin.privat.set("players." + "emerald." + change + ".x_min", xx_min);
							plugin.privat.set("players." + "emerald." + change + ".y_min", yy_min);
							plugin.privat.set("players." + "emerald." + change + ".z_min", zz_min);
							plugin.privat.set("players." + "emerald." + change + ".x_max", xx_max);
							plugin.privat.set("players." + "emerald." + change + ".y_max", yy_max);
							plugin.privat.set("players." + "emerald." + change + ".z_max", zz_max);
							plugin.privat.set("players." + "emerald." + change + ".block" + ".x", xx);
							plugin.privat.set("players." + "emerald." + change + ".block" + ".y", yy);
							plugin.privat.set("players." + "emerald." + change + ".block" + ".z", zz);
							plugin.privat.set("players." + "emerald." + change + ".uuid", uuid_p);
							
							plugin.privat.set("players." + "emerald." + j, null);
						}
						
						int amount = plrs - 1;
						plugin.privat.set("players.plrs_e", amount);
						
						save();
						
					}
					
					
				}else{
					
					result_1 = 0;
					result_2 = 0;
					result_3 = 0;
					
					e.setCancelled(true);
					
				}
			  
			}
		}
	  } else {
		  
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
					
					if(clan_owner.equalsIgnoreCase(plugin.clan.getString("players." + uuid_private + ".clan_name"))){
					
					result_1 = 0;
					result_2 = 0;
					result_3 = 0;
					
					e.setCancelled(false);
					
					}else{
						
						result_1 = 0;
						result_2 = 0;
						result_3 = 0;
						
						e.setCancelled(true);
						
					}
				
			}

		  }  
		}

	}

	public void pressRight(Player p, PlayerInteractEvent e){
	
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
		
		Block b = e.getClickedBlock();
		
		Location loc = b.getLocation();
		
		int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();
		
		int i = 0;
		
		int result_1 = 0;
		int result_2 = 0;
		int result_3 = 0;
		
		while(i < plugin.privat.getInt("players.plrs")){
			
			i++;
			
			int x_min = getX(p, "x_min", "diamond", i);
			int y_min = getY(p, "y_min", "diamond", i);
			int z_min = getZ(p, "z_min", "diamond", i);
			
			int x_max = getX(p, "x_max", "diamond", i);
			int y_max = getY(p, "y_max", "diamond", i);
			int z_max = getZ(p, "z_max", "diamond", i);
			
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
			
			if(x == getXB(p, "x", "diamond", i) && y == getYB(p, "y", "diamond", i) && z == getZB(p, "z", "diamond", i)){
				if(p.getUniqueId().toString().equalsIgnoreCase(getUUID("diamond", i))){
				plugin.coords.put(p.getName(), i);
				
				result_1 = 0;
				result_2 = 0;
				result_3 = 0;
				
				Interface.mainGuiD(p);
				}
			}
				
			if(p.getUniqueId().toString().equalsIgnoreCase(getUUID("diamond", i))){
				
				result_1 = 0;
				result_2 = 0;
				result_3 = 0;
				
				e.setCancelled(false);
				
			}else{
				
				result_1 = 0;
				result_2 = 0;
				result_3 = 0;
				
				e.setCancelled(true);
				
			}
			
			}
			
		}
		while(i < plugin.privat.getInt("players.plrs_i")){
			
			i++;
			
			int x_min = getX(p, "x_min", "gold", i);
			int y_min = getY(p, "y_min", "gold", i);
			int z_min = getZ(p, "z_min", "gold", i);
			
			int x_max = getX(p, "x_max", "gold", i);
			int y_max = getY(p, "y_max", "gold", i);
			int z_max = getZ(p, "z_max", "gold", i);
			
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
			
			if(x == getXB(p, "x", "gold", i) && y == getYB(p, "y", "gold", i) && z == getZB(p, "z", "gold", i)){
					
				if(p.getUniqueId().toString().equalsIgnoreCase(getUUID("gold", i))){
					
					result_1 = 0;
					result_2 = 0;
					result_3 = 0;
					
				plugin.coords.put(p.getName(), i);
				
				Interface.mainGuiG(p);
				
				}
					
			}
				
			if(p.getUniqueId().toString().equalsIgnoreCase(getUUID("gold", i))){
				
				result_1 = 0;
				result_2 = 0;
				result_3 = 0;
				
				e.setCancelled(false);
				
			}else{
				
				result_1 = 0;
				result_2 = 0;
				result_3 = 0;
				
				e.setCancelled(true);
				
			}
			
			}
			
		}
		
		while(i < plugin.privat.getInt("players.plrs_e")){
			
			i++;
			
			String uuid_private = plugin.privat.getString("players.emerald." + i + ".uuid");
			
			String clan_owner = plugin.clan.getString("players." + p.getUniqueId() + ".clan_name");
			
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
			
			if(x == getXB(p, "x", "emerald", i) && y == getYB(p, "y", "emerald", i) && z == getZB(p, "z", "emerald", i)){
				
				if(clan_owner.equalsIgnoreCase(plugin.clan.getString("players." + uuid_private + ".clan_name"))){
				
				result_1 = 0;
				result_2 = 0;
				result_3 = 0;
				
				plugin.coords.put(p.getName(), i);
				
				Interface.mainGuiE(p);
				}
			}
				
			if(clan_owner.equalsIgnoreCase(plugin.clan.getString("players." + uuid_private + ".clan_name"))){
				
				result_1 = 0;
				result_2 = 0;
				result_3 = 0;
				
				e.setCancelled(false);
				
			}else{
				
				result_1 = 0;
				result_2 = 0;
				result_3 = 0;
				
				e.setCancelled(true);
				
			}
			
			}
			
		}
		
	  }
		

	}
	
	@EventHandler
	public void blockPlace(BlockPlaceEvent e){
		
		Player p = e.getPlayer();
		
		Block b = e.getBlock();
		
		Location loc = b.getLocation();
		
		String world = b.getLocation().getWorld().getName();
		
		int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();
		
		if(b.getType() == Material.DIAMOND_BLOCK){
			
			
			int x_min = b.getLocation().getBlockX() - 8;
			int y_min = b.getLocation().getBlockY() - 16;
			int z_min = b.getLocation().getBlockZ() - 8;
			
			int x_max = b.getLocation().getBlockX() + 8;
			int y_max = b.getLocation().getBlockY() + 16;
			int z_max = b.getLocation().getBlockZ() + 8;
			
			//Bukkit.getWorld(world).getBlockAt(x_min, y_min, z_min).setType(Material.GOLD_BLOCK);
			//Bukkit.getWorld(world).getBlockAt(x_max, y_max, z_max).setType(Material.GOLD_BLOCK);
			
			if(!plugin.privat.contains("players." + "plrs")){
				plugin.privat.set("players." + "plrs", 1);
			} else{
				 
				int i = plugin.privat.getInt("players." + "plrs");
				
				int amount = i + 1;
				
				plugin.privat.set("players." + "plrs", amount);
				
			}
			
			int i = plugin.privat.getInt("players." + "plrs");
			
			plugin.privat.set("players." + "diamond." + i + ".world", world);
			plugin.privat.set("players." + "diamond." + i + ".x_min", x_min);
			plugin.privat.set("players." + "diamond." + i + ".y_min", y_min);
			plugin.privat.set("players." + "diamond." + i + ".z_min", z_min);
			plugin.privat.set("players." + "diamond." + i + ".x_max", x_max);
			plugin.privat.set("players." + "diamond." + i + ".y_max", y_max);
			plugin.privat.set("players." + "diamond." + i + ".z_max", z_max);
			plugin.privat.set("players." + "diamond." + i + ".block" + ".x", x);
			plugin.privat.set("players." + "diamond." + i + ".block" + ".y", y);
			plugin.privat.set("players." + "diamond." + i + ".block" + ".z", z);
			plugin.privat.set("players." + "diamond." + i + ".uuid", p.getUniqueId().toString());
			
			/*plugin.privat.set("regions." + p.getUniqueId() + ".diamond." + i + ".x_min", x_min);
			plugin.privat.set("regions." + p.getUniqueId() + ".diamond." + i + ".y_min", y_min);
			plugin.privat.set("regions." + p.getUniqueId() + ".diamond." + i + ".z_min", z_min);
			plugin.privat.set("regions." + p.getUniqueId() + ".diamond." + i + ".x_max", x_max);
			plugin.privat.set("regions." + p.getUniqueId() + ".diamond." + i + ".y_max", y_max);
			plugin.privat.set("regions." + p.getUniqueId() + ".diamond." + i + ".z_max", z_max);*/
			
			
			
			save();
		}
		if(b.getType() == Material.GOLD_BLOCK){
			
			int x_min = b.getLocation().getBlockX() - 14;
			int y_min = b.getLocation().getBlockY() - 28;
			int z_min = b.getLocation().getBlockZ() - 14;
			
			int x_max = b.getLocation().getBlockX() + 14;
			int y_max = b.getLocation().getBlockY() + 28;
			int z_max = b.getLocation().getBlockZ() + 14;
			
			//Bukkit.getWorld(world).getBlockAt(x_min, y_min, z_min).setType(Material.IRON_BLOCK);
			//Bukkit.getWorld(world).getBlockAt(x_max, y_max, z_max).setType(Material.IRON_BLOCK);
			
			if(!plugin.privat.contains("players." + "plrs_i")){
				plugin.privat.set("players." + "plrs_i", 1);
			} else{
				 
				int i = plugin.privat.getInt("players." + "plrs_i");
				
				int amount = i + 1;
				
				plugin.privat.set("players." + "plrs_i", amount);
				
			}
			
			int i = plugin.privat.getInt("players." + "plrs_i");
			
			plugin.privat.set("players." + "gold." + i + ".world", world);
			plugin.privat.set("players." + "gold." + i + ".x_min", x_min);
			plugin.privat.set("players." + "gold." + i + ".y_min", y_min);
			plugin.privat.set("players." + "gold." + i + ".z_min", z_min);
			plugin.privat.set("players." + "gold." + i + ".x_max", x_max);
			plugin.privat.set("players." + "gold." + i + ".y_max", y_max);
			plugin.privat.set("players." + "gold." + i + ".z_max", z_max);
			plugin.privat.set("players." + "gold." + i + ".block" + ".x", x);
			plugin.privat.set("players." + "gold." + i + ".block" + ".y", y);
			plugin.privat.set("players." + "gold." + i + ".block" + ".z", z);
			plugin.privat.set("players." + "gold." + i + ".uuid", p.getUniqueId().toString());
			
			
			save();
		}
		
		if(b.getType() == Material.EMERALD_BLOCK){
			
			String name_clan = plugin.clan.getString("players." + p.getUniqueId() + ".clan_name");
			String creator = plugin.clan.getString("clans." + name_clan + ".creator_name");
			
			if(p.getName().equalsIgnoreCase(creator)){
			
			int x_min = b.getLocation().getBlockX() - 20;
			int y_min = b.getLocation().getBlockY() - 40;
			int z_min = b.getLocation().getBlockZ() - 20;
			
			int x_max = b.getLocation().getBlockX() + 20;
			int y_max = b.getLocation().getBlockY() + 40;
			int z_max = b.getLocation().getBlockZ() + 20;
			
			//Bukkit.getWorld(world).getBlockAt(x_min, y_min, z_min).setType(Material.STONE);
			//Bukkit.getWorld(world).getBlockAt(x_max, y_max, z_max).setType(Material.STONE);
			
			if(!plugin.privat.contains("players." + "plrs_e")){
				plugin.privat.set("players." + "plrs_e", 1);
			} else{
				 
				int i = plugin.privat.getInt("players." + "plrs_e");
				
				int amount = i + 1;
				
				plugin.privat.set("players." + "plrs_e", amount);
				
			}
			
			int i = plugin.privat.getInt("players." + "plrs_e");
			
			plugin.privat.set("players." + "emerald." + i + ".world", world);
			plugin.privat.set("players." + "emerald." + i + ".x_min", x_min);
			plugin.privat.set("players." + "emerald." + i + ".y_min", y_min);
			plugin.privat.set("players." + "emerald." + i + ".z_min", z_min);
			plugin.privat.set("players." + "emerald." + i + ".x_max", x_max);
			plugin.privat.set("players." + "emerald." + i + ".y_max", y_max);
			plugin.privat.set("players." + "emerald." + i + ".z_max", z_max);
			plugin.privat.set("players." + "emerald." + i + ".block" + ".x", x);
			plugin.privat.set("players." + "emerald." + i + ".block" + ".y", y);
			plugin.privat.set("players." + "emerald." + i + ".block" + ".z", z);
			plugin.privat.set("players." + "emerald." + i + ".uuid", p.getUniqueId().toString());
			
			
			save();
			}else{
				
				p.sendMessage(prefix + ChatColor.RED + "Âû íå ÿâëÿåòåñü ñîçäàòåëåì êëàíà");
				
				e.setCancelled(true);
				
			}
		}
		
		blockPlace(p, e);
		
	}
	
	@EventHandler
	public void blockBreak(BlockBreakEvent e){
		
		Player p = e.getPlayer();
		
		blockBreak(p, e);
		
	}
	
	@EventHandler
	public void click(PlayerInteractEvent e){
		
		Player p = e.getPlayer();
		
		pressRight(p, e);
		
	}
	
}
