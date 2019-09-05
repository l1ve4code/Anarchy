package ru.draiget.nova.gui;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import ru.draiget.nova.main.Main;

public class Interface implements Listener {

	HashMap<String, Integer> particle = new HashMap<String, Integer>();
	
	String prefix = ChatColor.YELLOW + "" + ChatColor.BOLD + "[" + ChatColor.RED + "NovaAnarchy" + ChatColor.YELLOW + "] " + ChatColor.RESET;
	
	private static Main plugin;
	
	@SuppressWarnings("static-access")
	public Interface(Main plugin) {
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

	
	public static void mainGuiD(Player p){
		
		Inventory inv = Bukkit.createInventory(null, 27, ChatColor.DARK_BLUE + "Алмазный приват");
		
		ItemStack item1 = new ItemStack(Material.GHAST_TEAR);
		ItemStack item2 = new ItemStack(Material.SKULL_ITEM);
		ItemStack item3 = new ItemStack(Material.STONE);
		
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)7);
		
		ItemMeta meta1 = item1.getItemMeta();
		ItemMeta meta2 = item2.getItemMeta();
		ItemMeta meta3 = item3.getItemMeta();
		
		meta1.setDisplayName(ChatColor.GREEN + "вкл/выкл частички границ привата");
		meta2.setDisplayName(ChatColor.BLUE + "Список игроков в привате");
		meta3.setDisplayName(ChatColor.YELLOW + "Перенести блок привата");
		
		item1.setItemMeta(meta1);
		item2.setItemMeta(meta2);
		item3.setItemMeta(meta3);
		
		for(int i = 0; i < inv.getSize(); i++){
			
		inv.setItem(2, item1);
		inv.setItem(6, item2);
		inv.setItem(22, item3);
		
		inv.setItem(i, glass);
		}
		p.openInventory(inv);
		
	}
	public static void mainGuiG(Player p){
		
		Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GOLD + "Золотой приват");
		
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)7);
		
		ItemStack item1 = new ItemStack(Material.GHAST_TEAR);
		ItemStack item2 = new ItemStack(Material.SKULL_ITEM);
		ItemStack item3 = new ItemStack(Material.STONE);
		
		ItemMeta meta1 = item1.getItemMeta();
		ItemMeta meta2 = item2.getItemMeta();
		ItemMeta meta3 = item3.getItemMeta();
		
		meta1.setDisplayName(ChatColor.GREEN + "вкл/выкл частички границ привата");
		meta2.setDisplayName(ChatColor.BLUE + "Список игроков в привате");
		meta3.setDisplayName(ChatColor.YELLOW + "Перенести блок привата");
		
		item1.setItemMeta(meta1);
		item2.setItemMeta(meta2);
		item3.setItemMeta(meta3);
		
		for(int i = 0; i < inv.getSize(); i++){
			
		inv.setItem(2, item1);
		inv.setItem(6, item2);
		inv.setItem(22, item3);
		
		inv.setItem(i, glass);
		
		}
		
		p.openInventory(inv);
		
	}
	
	@SuppressWarnings("deprecation")
	public static void players(Player p){
		
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.GREEN + "Игроки ");
		
		String clan_name = plugin.clan.getString("players." + p.getUniqueId() + ".clan_name");
		
		List<String> members = plugin.clan.getStringList("clans." + clan_name + ".members");
		
		ItemStack head = new ItemStack(Material.SKULL_ITEM);
		
		SkullMeta meta = (SkullMeta) head.getItemMeta();
		
		for(int i = 0; i < members.size(); i++){
			
			String name = plugin.clan.getString("players." + members.get(i) + ".name");
			
			meta.setDisplayName(name);
			
			meta.setOwner(name);
			
			head.setItemMeta(meta);
			
			inv.setItem(i, head);
			
		}
		
		p.openInventory(inv);
	}
	
	public static void trade(Player p){
		
		Inventory inv = Bukkit.createInventory(null, 9, "TRADE");
		
		
		p.openInventory(inv);
	}

	public static void donate(Player p){
		
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.GOLD + "Управление кланом");
		
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)7);
		
		ItemStack item1 = new ItemStack(Material.EXP_BOTTLE);
		ItemStack item2 = new ItemStack(Material.GLASS_BOTTLE);
		ItemStack item3 = new ItemStack(Material.SIGN);
		
		ItemMeta meta1 = item1.getItemMeta();
		ItemMeta meta2 = item2.getItemMeta();
		ItemMeta meta3 = item3.getItemMeta();
		
		meta1.setDisplayName(ChatColor.GREEN + "Опыт клана: " + plugin.clan.getInt("clans." + plugin.clan.getString("players." + p.getUniqueId() + ".clan_name") + ".money"));
		meta2.setDisplayName(ChatColor.GOLD + "Уровень клана: " + plugin.clan.getInt("clans." + plugin.clan.getString("players." + p.getUniqueId() + ".clan_name") + ".lvl"));
		meta3.setDisplayName(ChatColor.DARK_PURPLE + "Внести опыт");
		
		item1.setItemMeta(meta1);
		item2.setItemMeta(meta2);
		item3.setItemMeta(meta3);
		
		inv.setItem(0, item1);
		inv.setItem(4, item2);
		inv.setItem(8, item3);
		
		for(int i = 0; i < inv.getSize(); i++){
		
		inv.setItem(i, glass);
		
		}
		
		inv.setItem(0, item1);
		inv.setItem(4, item2);
		inv.setItem(8, item3);
		
		
		p.openInventory(inv);
	}
	
	public static void giveR(Player p){
		
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.DARK_RED + "Трэйд");
		
		List<String> members = plugin.clan.getStringList("clans." + plugin.clan.getString("players." + p.getUniqueId() + ".clan_name") + ".members");
		
		for(int i = 0; i < members.size(); i++){
		
			//String name = plugin.clan.getString("players." + members.get(i) + ".name");
			
			
		}
		
		p.openInventory(inv);
	}
	
	public static void items(Player p){
		
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.DARK_PURPLE + "Внести опыт");
		
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)7);
		
		ItemStack glass_red = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)14);
		ItemStack glass_green = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)13);
		
		ItemMeta meta1 = glass_red.getItemMeta();
		ItemMeta meta2 = glass_green.getItemMeta();
		ItemMeta meta3 = glass.getItemMeta();
		
		meta1.setDisplayName(ChatColor.RED + "Выйти");
		meta2.setDisplayName(ChatColor.GREEN + "Внести в клан");
		meta3.setDisplayName(" ");
		
		glass_red.setItemMeta(meta1);
		glass_green.setItemMeta(meta2);
		glass.setItemMeta(meta3);
		
		for(int i = 0; i < inv.getSize(); i++){
			
			inv.setItem(i, glass);
			inv.setItem(4, null);
			inv.setItem(0, glass_red);
			inv.setItem(8, glass_green);
			
		}
		
		p.openInventory(inv);
	}
	
	public static void mainGuiE(Player p){
		
		Inventory inv = Bukkit.createInventory(null, 27, ChatColor.DARK_GREEN + "Изумрудный приват");
		
		ItemStack item1 = new ItemStack(Material.GHAST_TEAR);
		ItemStack item2 = new ItemStack(Material.SKULL_ITEM);
		ItemStack item3 = new ItemStack(Material.STONE);
		ItemStack item4 = new ItemStack(Material.DIAMOND);
		ItemStack item5 = new ItemStack(Material.GOLD_INGOT);
		
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)7);
		
		ItemMeta meta1 = item1.getItemMeta();
		ItemMeta meta2 = item2.getItemMeta();
		ItemMeta meta3 = item3.getItemMeta();
		ItemMeta meta4 = item4.getItemMeta();
		ItemMeta meta5 = item5.getItemMeta();
		
		meta1.setDisplayName(ChatColor.GREEN + "вкл/выкл частички границ привата");
		meta2.setDisplayName(ChatColor.BLUE + "Список игроков в привате");
		meta3.setDisplayName(ChatColor.YELLOW + "Перенести блок привата");
		meta4.setDisplayName(ChatColor.DARK_RED + "Трэйд");
		meta5.setDisplayName(ChatColor.GOLD + "Уровень клана");
		
		item1.setItemMeta(meta1);
		item2.setItemMeta(meta2);
		item3.setItemMeta(meta3);
		
		item4.setItemMeta(meta4);
		item5.setItemMeta(meta5);
		
		for(int i = 0; i < inv.getSize(); i++){
		
		inv.setItem(2, item1);
		inv.setItem(6, item2);
		inv.setItem(20, item3);
		
		inv.setItem(24, item4);
		inv.setItem(13, item5);
		
		inv.setItem(i, glass);
		
		}
		
		p.openInventory(inv);
		
	}
	
	@EventHandler
	public void click(InventoryClickEvent e){
		
		Player p = (Player) e.getWhoClicked();
		
		String clan_name = plugin.clan.getString("players." + p.getUniqueId() + ".clan_name");
		//String creator_name = plugin.clan.getString("clans." + clan_name + ".creator_name");
		int money = plugin.clan.getInt("clans." + clan_name + ".money");
		//int lvl = plugin.clan.getInt("clans." + clan_name + ".lvl");
		
		if(e.getInventory().getName().equalsIgnoreCase(ChatColor.DARK_BLUE + "Алмазный приват")){
			
			
			if(e.getCurrentItem().getType() == Material.GHAST_TEAR){
				
				int x_min = getX(p, "x_min", "diamond", plugin.coords.get(p.getName()));
				int y_min = getY(p, "y_min", "diamond", plugin.coords.get(p.getName()));
				int z_min = getZ(p, "z_min", "diamond", plugin.coords.get(p.getName()));
				
				int x_max = getX(p, "x_max", "diamond", plugin.coords.get(p.getName()));
				int y_max = getY(p, "y_max", "diamond", plugin.coords.get(p.getName()));
				int z_max = getZ(p, "z_max", "diamond", plugin.coords.get(p.getName()));
				
				if(!particle.containsKey(p.getName())){
				
					particle.put(p.getName(), 1);
				
					//Bukkit.getWorld(p.getName()).playEffect(new Location(p.getWorld(), (double)x_min, (double)y_min, (double)z_min), Effect.ANVIL_BREAK, 0);
					
				Bukkit.getWorld(p.getWorld().getName()).getBlockAt(x_min, y_min, z_min).setType(Material.STATIONARY_WATER);
				Bukkit.getWorld(p.getWorld().getName()).getBlockAt(x_max, y_max, z_max).setType(Material.STATIONARY_WATER);
				
				}
				else if(particle.get(p.getName()) == 1){
					
					Bukkit.getWorld(p.getWorld().getName()).getBlockAt(x_min, y_min, z_min).setType(Material.STATIONARY_WATER);
					Bukkit.getWorld(p.getWorld().getName()).getBlockAt(x_max, y_max, z_max).setType(Material.STATIONARY_WATER);
					
					particle.replace(p.getName(), 0);		
					
				}
				else if(particle.get(p.getName()) == 0){
					
					Bukkit.getWorld(p.getWorld().getName()).getBlockAt(x_min, y_min, z_min).setType(Material.AIR);
					Bukkit.getWorld(p.getWorld().getName()).getBlockAt(x_max, y_max, z_max).setType(Material.AIR);
					
					particle.replace(p.getName(), 1);
					
				}
			}
			else if(e.getCurrentItem().getType() == Material.SKULL_ITEM){
				
				p.sendMessage(prefix + "Ник игрока в привате: " + ChatColor.GREEN + p.getName());
				
				
			}
			else if(e.getCurrentItem().getType() == Material.STONE){
				
				int x = getXB(p, "x", "diamond", plugin.coords.get(p.getName()));
				int y = getYB(p, "y", "diamond", plugin.coords.get(p.getName()));
				int z = getZB(p, "z", "diamond", plugin.coords.get(p.getName()));
				
				p.getInventory().addItem(new ItemStack(Material.DIAMOND_BLOCK));
				
				plugin.privat.set("players." + "diamond." + plugin.coords.get(p.getName()), null);
				int plrs = plugin.privat.getInt("players.plrs");
				
				int j = plugin.coords.get(p.getName());
				
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
				
				try {
					plugin.privat.save(plugin.privatFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Bukkit.getWorld(p.getWorld().getName()).getBlockAt(x, y, z).setType(Material.AIR);
			}
			
			e.setCancelled(true);
		}
		
		if(e.getInventory().getName().equalsIgnoreCase(ChatColor.GOLD + "Золотой приват")){
			
			
			if(e.getCurrentItem().getType() == Material.GHAST_TEAR){
				
				int x_min = getX(p, "x_min", "gold", plugin.coords.get(p.getName()));
				int y_min = getY(p, "y_min", "gold", plugin.coords.get(p.getName()));
				int z_min = getZ(p, "z_min", "gold", plugin.coords.get(p.getName()));
				
				int x_max = getX(p, "x_max", "gold", plugin.coords.get(p.getName()));
				int y_max = getY(p, "y_max", "gold", plugin.coords.get(p.getName()));
				int z_max = getZ(p, "z_max", "gold", plugin.coords.get(p.getName()));
				
				if(!particle.containsKey(p.getName())){
				
					particle.put(p.getName(), 1);
				
				Bukkit.getWorld(p.getWorld().getName()).getBlockAt(x_min, y_min, z_min).setType(Material.STATIONARY_WATER);
				Bukkit.getWorld(p.getWorld().getName()).getBlockAt(x_max, y_max, z_max).setType(Material.STATIONARY_WATER);
				
				}
				else if(particle.get(p.getName()) == 1){
					
					Bukkit.getWorld(p.getWorld().getName()).getBlockAt(x_min, y_min, z_min).setType(Material.STATIONARY_WATER);
					Bukkit.getWorld(p.getWorld().getName()).getBlockAt(x_max, y_max, z_max).setType(Material.STATIONARY_WATER);
					
					particle.replace(p.getName(), 0);		
					
				}
				else if(particle.get(p.getName()) == 0){
					
					Bukkit.getWorld(p.getWorld().getName()).getBlockAt(x_min, y_min, z_min).setType(Material.AIR);
					Bukkit.getWorld(p.getWorld().getName()).getBlockAt(x_max, y_max, z_max).setType(Material.AIR);
					
					particle.replace(p.getName(), 1);
					
				}
			}
			else if(e.getCurrentItem().getType() == Material.SKULL_ITEM){
				
				p.sendMessage(prefix + "Ник игрока в привате: " + ChatColor.GREEN + p.getName());
				
				
			}
			else if(e.getCurrentItem().getType() == Material.STONE){
				
				int x = getXB(p, "x", "gold", plugin.coords.get(p.getName()));
				int y = getYB(p, "y", "gold", plugin.coords.get(p.getName()));
				int z = getZB(p, "z", "gold", plugin.coords.get(p.getName()));
				
				p.getInventory().addItem(new ItemStack(Material.GOLD_BLOCK));
				
				plugin.privat.set("players." + "gold." + plugin.coords.get(p.getName()), null);
				int plrs = plugin.privat.getInt("players.plrs_i");
				
				int j = plugin.coords.get(p.getName());
				
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
				
				try {
					plugin.privat.save(plugin.privatFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Bukkit.getWorld(p.getWorld().getName()).getBlockAt(x, y, z).setType(Material.AIR);
			}
			
			e.setCancelled(true);
		}
		if(e.getInventory().getName().equalsIgnoreCase(ChatColor.DARK_GREEN + "Изумрудный приват")){
			
			if(e.getCurrentItem().getType() == Material.GHAST_TEAR){
				
				int x_min = getX(p, "x_min", "emerald", plugin.coords.get(p.getName()));
				int y_min = getY(p, "y_min", "emerald", plugin.coords.get(p.getName()));
				int z_min = getZ(p, "z_min", "emerald", plugin.coords.get(p.getName()));
				
				int x_max = getX(p, "x_max", "emerald", plugin.coords.get(p.getName()));
				int y_max = getY(p, "y_max", "emerald", plugin.coords.get(p.getName()));
				int z_max = getZ(p, "z_max", "emerald", plugin.coords.get(p.getName()));
				
				if(!particle.containsKey(p.getName())){
				
					particle.put(p.getName(), 1);
				
				Bukkit.getWorld(p.getWorld().getName()).getBlockAt(x_min, y_min, z_min).setType(Material.STATIONARY_WATER);
				Bukkit.getWorld(p.getWorld().getName()).getBlockAt(x_max, y_max, z_max).setType(Material.STATIONARY_WATER);
				
				}
				else if(particle.get(p.getName()) == 1){
					
					Bukkit.getWorld(p.getWorld().getName()).getBlockAt(x_min, y_min, z_min).setType(Material.STATIONARY_WATER);
					Bukkit.getWorld(p.getWorld().getName()).getBlockAt(x_max, y_max, z_max).setType(Material.STATIONARY_WATER);
					
					particle.replace(p.getName(), 0);		
					
				}
				else if(particle.get(p.getName()) == 0){
					
					Bukkit.getWorld(p.getWorld().getName()).getBlockAt(x_min, y_min, z_min).setType(Material.AIR);
					Bukkit.getWorld(p.getWorld().getName()).getBlockAt(x_max, y_max, z_max).setType(Material.AIR);
					
					particle.replace(p.getName(), 1);
					
				}
			}
			else if(e.getCurrentItem().getType() == Material.SKULL_ITEM){
				
				players(p);
				
			}
			else if(e.getCurrentItem().getType() == Material.STONE){
				
				int x = getXB(p, "x", "emerald", plugin.coords.get(p.getName()));
				int y = getYB(p, "y", "emerald", plugin.coords.get(p.getName()));
				int z = getZB(p, "z", "emerald", plugin.coords.get(p.getName()));
				
				p.getInventory().addItem(new ItemStack(Material.EMERALD_BLOCK));
				
				plugin.privat.set("players." + "emerald." + plugin.coords.get(p.getName()), null);
				int plrs = plugin.privat.getInt("players.plrs_e");
				
				int j = plugin.coords.get(p.getName());
				
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
				
				try {
					plugin.privat.save(plugin.privatFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Bukkit.getWorld(p.getWorld().getName()).getBlockAt(x, y, z).setType(Material.AIR);
			}
			else if(e.getCurrentItem().getType() == Material.GOLD_INGOT){
				
				donate(p);
				
				if(plugin.clan.getInt("clans." + clan_name + ".money") > 1000 && plugin.clan.getInt("clans." + clan_name + ".money") <= 6500){
					
					plugin.clan.set("clans." + clan_name + ".lvl", 1);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				if(plugin.clan.getInt("clans." + clan_name + ".money") > 6500 && plugin.clan.getInt("clans." + clan_name + ".money") < 15000){
					if(!plugin.lvl_clan.containsKey(clan_name)){
						
						plugin.lvl_clan.put(clan_name, 2);
						plugin.clan.set("clans." + clan_name + ".lvl", plugin.lvl_clan.get(clan_name));
						try {
							plugin.clan.save(plugin.clanFile);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}else{
						
						plugin.lvl_clan.replace(clan_name, 2);
						plugin.clan.set("clans." + clan_name + ".lvl", plugin.lvl_clan.get(clan_name));
						try {
							plugin.clan.save(plugin.clanFile);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					}
					
					if(plugin.clan.getInt("clans." + clan_name + ".money") > 15000 && plugin.clan.getInt("clans." + clan_name + ".money") < 30000){
					if(!plugin.lvl_clan.containsKey(clan_name)){
						
						plugin.lvl_clan.put(clan_name, 3);
						plugin.clan.set("clans." + clan_name + ".lvl", plugin.lvl_clan.get(clan_name));
						try {
							plugin.clan.save(plugin.clanFile);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}else{
						
						plugin.lvl_clan.replace(clan_name, 3);
						plugin.clan.set("clans." + clan_name + ".lvl", plugin.lvl_clan.get(clan_name));
						try {
							plugin.clan.save(plugin.clanFile);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					}
					
					if(plugin.clan.getInt("clans." + clan_name + ".money") > 30000 && plugin.clan.getInt("clans." + clan_name + ".money") < 50000){
					if(!plugin.lvl_clan.containsKey(clan_name)){
						
						plugin.lvl_clan.put(clan_name, 4);
						plugin.clan.set("clans." + clan_name + ".lvl", plugin.lvl_clan.get(clan_name));
						try {
							plugin.clan.save(plugin.clanFile);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}else{
						
						plugin.lvl_clan.replace(clan_name, 4);
						plugin.clan.set("clans." + clan_name + ".lvl", plugin.lvl_clan.get(clan_name));
						try {
							plugin.clan.save(plugin.clanFile);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					}
					if(plugin.clan.getInt("clans." + clan_name + ".money") > 50000 && plugin.clan.getInt("clans." + clan_name + ".money") < 75000){
					if(!plugin.lvl_clan.containsKey(clan_name)){
						
						plugin.lvl_clan.put(clan_name, 5);
						plugin.clan.set("clans." + clan_name + ".lvl", plugin.lvl_clan.get(clan_name));
						try {
							plugin.clan.save(plugin.clanFile);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}else{
						
						plugin.lvl_clan.replace(clan_name, 5);
						plugin.clan.set("clans." + clan_name + ".lvl", plugin.lvl_clan.get(clan_name));
						try {
							plugin.clan.save(plugin.clanFile);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					}
					if(plugin.clan.getInt("clans." + clan_name + ".money") > 75000 && plugin.clan.getInt("clans." + clan_name + ".money") < 90000){
					if(!plugin.lvl_clan.containsKey(clan_name)){
						
						plugin.lvl_clan.put(clan_name, 6);
						plugin.clan.set("clans." + clan_name + ".lvl", plugin.lvl_clan.get(clan_name));
						try {
							plugin.clan.save(plugin.clanFile);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}else{
						
						plugin.lvl_clan.replace(clan_name, 6);
						plugin.clan.set("clans." + clan_name + ".lvl", plugin.lvl_clan.get(clan_name));
						try {
							plugin.clan.save(plugin.clanFile);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					}
					if(plugin.clan.getInt("clans." + clan_name + ".money") > 90000 && plugin.clan.getInt("clans." + clan_name + ".money") < 100000){
					if(!plugin.lvl_clan.containsKey(clan_name)){
						
						plugin.lvl_clan.put(clan_name, 7);
						plugin.clan.set("clans." + clan_name + ".lvl", plugin.lvl_clan.get(clan_name));
						try {
							plugin.clan.save(plugin.clanFile);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}else{
						
						plugin.lvl_clan.replace(clan_name, 7);
						plugin.clan.set("clans." + clan_name + ".lvl", plugin.lvl_clan.get(clan_name));
						try {
							plugin.clan.save(plugin.clanFile);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					}
					if(plugin.clan.getInt("clans." + clan_name + ".money") > 100000 && plugin.clan.getInt("clans." + clan_name + ".money") < 110000){
					if(!plugin.lvl_clan.containsKey(clan_name)){
						
						plugin.lvl_clan.put(clan_name, 8);
						plugin.clan.set("clans." + clan_name + ".lvl", plugin.lvl_clan.get(clan_name));
						try {
							plugin.clan.save(plugin.clanFile);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}else{
						
						plugin.lvl_clan.replace(clan_name, 8);
						plugin.clan.set("clans." + clan_name + ".lvl", plugin.lvl_clan.get(clan_name));
						try {
							plugin.clan.save(plugin.clanFile);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					}
					if(plugin.clan.getInt("clans." + clan_name + ".money") > 110000 && plugin.clan.getInt("clans." + clan_name + ".money") < 120000){
					if(!plugin.lvl_clan.containsKey(clan_name)){
						
						plugin.lvl_clan.put(clan_name, 9);
						plugin.clan.set("clans." + clan_name + ".lvl", plugin.lvl_clan.get(clan_name));
						try {
							plugin.clan.save(plugin.clanFile);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}else{
						
						plugin.lvl_clan.replace(clan_name, 9);
						plugin.clan.set("clans." + clan_name + ".lvl", plugin.lvl_clan.get(clan_name));
						try {
							plugin.clan.save(plugin.clanFile);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					}
					if(plugin.clan.getInt("clans." + clan_name + ".money") > 120000){
					if(!plugin.lvl_clan.containsKey(clan_name)){
						
						plugin.lvl_clan.put(clan_name, 10);
						plugin.clan.set("clans." + clan_name + ".lvl", plugin.lvl_clan.get(clan_name));
						try {
							plugin.clan.save(plugin.clanFile);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}else{
						
						plugin.lvl_clan.replace(clan_name, 10);
						plugin.clan.set("clans." + clan_name + ".lvl", plugin.lvl_clan.get(clan_name));
						try {
							plugin.clan.save(plugin.clanFile);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					}
				
			}
			/*else if(e.getCurrentItem().getType() == Material.DIAMOND){
				
				giveR(p);
				
			}*/
			
			e.setCancelled(true);
		}
		if(e.getInventory().getName().equalsIgnoreCase(ChatColor.GOLD + "Управление кланом")){
			
			if(e.getCurrentItem().getType() == Material.SIGN){
				
				items(p);
				
			}
			
			e.setCancelled(true);
		}
		if(e.getInventory().getName().equalsIgnoreCase(ChatColor.DARK_PURPLE + "Внести опыт")){
			
			if(e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE || e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(" ")){
				
				e.setCancelled(true);
				
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Выйти")){
				
				p.closeInventory();
				
			}
			
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Внести в клан")){
					
				if(e.getInventory().getItem(4).getType() == Material.AIR){
					
					p.closeInventory();
					
				}
				if(e.getInventory().getItem(4).getType() == Material.IRON_INGOT){
					
					int summ = money + 30;
					
					p.closeInventory();
					
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}				
				else if(e.getInventory().getItem(4).getType() == Material.GOLD_INGOT){
					
					int summ = money + 50;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getType() == Material.DIAMOND){
					
					int summ = money + 100;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getType() == Material.EMERALD){
					
					int summ = money + 105;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getType() == Material.COAL){
					
					int summ = money + 5;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getType() == Material.OBSIDIAN){
					
					int summ = money + 20;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getType() == Material.LAPIS_BLOCK){
					
					int summ = money + 10;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getType() == Material.WOOD){
					
					int summ = money + 2;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getType() == Material.GOLD_BLOCK){
					
					int summ = money + 470;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getType() == Material.IRON_BLOCK){
					
					int summ = money + 300;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getType() == Material.DIAMOND_BLOCK){
					
					int summ = money + 1000;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getType() == Material.EMERALD_BLOCK){
					
					int summ = money + 1100;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getType() == Material.CHORUS_FLOWER){
					
					int summ = money + 30;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getType() == Material.ENDER_PEARL){
					
					int summ = money + 50;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getType() == Material.DRAGON_EGG){
					
					int summ = money + 5000;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getType() == Material.EYE_OF_ENDER){
					
					int summ = money + 75;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getType() == Material.BLAZE_ROD){
					
					int summ = money + 20;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getType() == Material.NETHER_STAR){
					
					int summ = money + 900;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getType() == Material.GOLDEN_APPLE){
					
					int summ = money + 450;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getEnchantmentLevel(Enchantment.DIG_SPEED) == 5){
					
					int summ = money + 600;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getEnchantmentLevel(Enchantment.SILK_TOUCH) == 4){
					
					int summ = money + 400;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getEnchantmentLevel(Enchantment.DURABILITY) == 3){
					
					int summ = money + 300;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getEnchantmentLevel(Enchantment.LUCK) == 3){
					
					int summ = money + 700;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getEnchantmentLevel(Enchantment.LURE) == 3){
					
					int summ = money + 200;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) == 4){
					
					int summ = money + 700;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getEnchantmentLevel(Enchantment.PROTECTION_FIRE) == 4){
					
					int summ = money + 300;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getEnchantmentLevel(Enchantment.PROTECTION_EXPLOSIONS) == 4){
					
					int summ = money + 250;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getEnchantmentLevel(Enchantment.PROTECTION_PROJECTILE) == 4){
					
					int summ = money + 250;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getEnchantmentLevel(Enchantment.OXYGEN) == 3){
					
					int summ = money + 350;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getEnchantmentLevel(Enchantment.THORNS) == 3){
					
					int summ = money + 800;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getEnchantmentLevel(Enchantment.WATER_WORKER) == 3){
					
					int summ = money + 350;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getEnchantmentLevel(Enchantment.FROST_WALKER) == 1){
					
					int summ = money + 1200;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getEnchantmentLevel(Enchantment.DAMAGE_ALL) == 5){
					
					int summ = money + 900;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
				else if(e.getInventory().getItem(4).getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS) == 5){
					
					int summ = money + 200;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD) == 5){
					
					int summ = money + 300;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getEnchantmentLevel(Enchantment.KNOCKBACK) == 2){
					
					int summ = money + 350;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getEnchantmentLevel(Enchantment.FIRE_ASPECT) == 2){
					
					int summ = money + 600;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getEnchantmentLevel(Enchantment.KNOCKBACK) == 2){
					
					int summ = money + 500;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getEnchantmentLevel(Enchantment.ARROW_FIRE) == 1){
					
					int summ = money + 800;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else if(e.getInventory().getItem(4).getEnchantmentLevel(Enchantment.ARROW_INFINITE) == 1){
					
					int summ = money + 800;
					p.closeInventory();
					plugin.clan.set("clans." + clan_name + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}

				
			  }
			
		}
		if(e.getInventory().getName().equalsIgnoreCase(ChatColor.GREEN + "Игроки")){
		
			e.setCancelled(true);
		}
		/*if(e.getInventory().getName().equalsIgnoreCase(ChatColor.DARK_RED + "Трэйд")){
			

			
			e.setCancelled(true);
		}*/
	}
	
}
