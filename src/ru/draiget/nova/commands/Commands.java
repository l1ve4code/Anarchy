package ru.draiget.nova.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import ru.draiget.nova.main.Main;

public class Commands implements CommandExecutor {

	private Main plugin;
	
	public Commands(Main plugin) {
		this.plugin = plugin;
	}
	
	HashMap<String, String> invite = new HashMap<String, String>();
	
	HashMap<String, String> tags = new HashMap<String, String>();
	
	String prefix = ChatColor.YELLOW + "" + ChatColor.BOLD + "[" + ChatColor.RED + "NovaAnarchy" + ChatColor.YELLOW + "] " + ChatColor.RESET;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(!(sender instanceof Player)){
			
			sender.sendMessage(ChatColor.RED + "У вас нет доступа к данной команде!");
			
		}
		
		Player p = (Player)sender;
		
		if(args.length == 0 || args[0].equalsIgnoreCase("help")){
			
			sender.sendMessage(prefix + ChatColor.GREEN + "-====== << Помощь >> ======- " + ChatColor.DARK_GRAY + "Страница 1/1");
			sender.sendMessage(prefix + ChatColor.DARK_GRAY + "/aclan create [название] - создание клана");
			sender.sendMessage(prefix + ChatColor.DARK_GRAY + "/aclan disband - удалить клан");
			sender.sendMessage(prefix + ChatColor.DARK_GRAY + "/aclan invite [ник] - пригласить игрока");
			sender.sendMessage(prefix + ChatColor.DARK_GRAY + "/aclan kick [ник] - выгнать игрока из клана");
			sender.sendMessage(prefix + ChatColor.DARK_GRAY + "/aclan up [ник] - повысить игрока");
			sender.sendMessage(prefix + ChatColor.DARK_GRAY + "/aclan down [ник] - понизить игрока");
			sender.sendMessage(prefix + ChatColor.DARK_GRAY + "/aclan stats - статистика клана");
			sender.sendMessage(prefix + ChatColor.DARK_GRAY + "/aclan accept - принять приглашение в клан");
			sender.sendMessage(prefix + ChatColor.DARK_GRAY + "/aclan cancel - отменить приглашение в клан");
			sender.sendMessage(prefix + ChatColor.DARK_GRAY + "/aclan leave - выйти из клана");
			sender.sendMessage(prefix + ChatColor.DARK_GRAY + "/aclan tag [тэг] - изменить тэг клана");
			sender.sendMessage(prefix + ChatColor.DARK_GRAY + "/aclan leader [ник] - передать права владельца кланом");
			sender.sendMessage(prefix + ChatColor.DARK_GRAY + "/aclan ff on/off - урон по союзникам");
			sender.sendMessage(prefix + ChatColor.DARK_GRAY + "/aclan request - запросить вещь в клане");	
			
		}
		
		if(args.length == 1){
			
			if(args[0].equalsIgnoreCase("create")){
				
				p.sendMessage(prefix + ChatColor.DARK_GREEN + "/aclan create [название клана]");
				return true;
			}
			else if(args[0].equalsIgnoreCase("reset")){
				
				plugin.clan.set("players", null);
				plugin.clan.set("clans", null);
				plugin.clan.set("tags", null);
				
				try {
					plugin.clan.save(plugin.clanFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				p.sendMessage(prefix + ChatColor.GREEN + "Вы успешно отчистили конфиг");
				
				return true;
			}
			else if(args[0].equalsIgnoreCase("invite")){
				
				p.sendMessage(prefix + ChatColor.GREEN + "/aclan invite [ник]");
				
				return true;
			}
			else if(args[0].equalsIgnoreCase("accept")){
				
				if(invite.containsKey(sender.getName())){
					
					String clan_leader = invite.get(sender.getName());
					
					Player p_leader = Bukkit.getPlayer(clan_leader);
					
					String clan_name = plugin.clan.getString("players." + p_leader.getUniqueId() + ".clan_name").toLowerCase();
					
					String clan_tag = plugin.clan.getString("clans." + clan_name + ".clan_tag");
					
					clan_tag = clan_tag.replace("&", "\u00a7");
					
					sender.sendMessage(prefix + ChatColor.GREEN + "Вы вступили в клан:" + ChatColor.BOLD + " " + ChatColor.BLUE + clan_tag);
					
					Player sp = (Player)sender;
					
					plugin.clan.set("players." + sp.getUniqueId() + ".clan_name", clan_name.toLowerCase());
					plugin.clan.set("players." + sp.getUniqueId() + ".in_clan", true);
					plugin.clan.set("players." + sp.getUniqueId() + ".clan_tag", plugin.clan.getString("clans." + clan_name + ".clan_tag"));
					
					List<String> members = plugin.clan.getStringList("clans." + clan_name + ".members");
					
					members.add(sp.getUniqueId().toString());
					
					plugin.clan.set("clans." + clan_name + ".members", members);
					
					invite.remove(sp.getName());
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return true;
					
				}
				
				return true;
			}
			else if(args[0].equalsIgnoreCase("cancel")){
				
				Player sp = (Player)sender;
				
				invite.remove(sp.getName());
				
				sp.sendMessage(prefix + ChatColor.RED + "Вы отказали в заявке!");
				
				return true;
			}
			else if(args[0].equalsIgnoreCase("disband")){
				
				String name_clan = plugin.clan.getString("players." + p.getUniqueId() + ".clan_name").toLowerCase();
				
				String owner_nick = plugin.clan.getString("clans." + name_clan + ".creator_name");
				
				String clan_tag = plugin.clan.getString("clans." + name_clan + ".clan_tag");
				
				if(p.getName().equalsIgnoreCase(owner_nick)){
					
					List<String> members = plugin.clan.getStringList("clans." + name_clan + ".members");
					
					for(int i = 0; i < members.size(); i++){
						
						plugin.clan.set("players." + members.get(i).toString() + ".clan_name", 0);
						plugin.clan.set("players." + members.get(i).toString() + ".in_clan", false);
						plugin.clan.set("players." + members.get(i).toString() + ".clan_tag", 0);
						
					}
					
					tags.remove(clan_tag);
					
					plugin.clan.set("clans." + name_clan, null);
					
					p.sendMessage(prefix + ChatColor.GREEN + "Ваш клан был удалён!");
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
					}
					
				}
				
				return true;
			}
			else if(args[0].equalsIgnoreCase("stats")){
				
				String name_clan = plugin.clan.getString("players." + p.getUniqueId() + ".clan_name").toLowerCase();
				
				String clan_tag = plugin.clan.getString("clans." + name_clan + ".clan_tag");
				
				if(name_clan.equalsIgnoreCase("0")) return true;
				
				if(clan_tag.contains("&")){ clan_tag = clan_tag.replace("&", "\u00a7");}else {clan_tag = name_clan;}
				
				if(plugin.clan.contains("clans." + name_clan)){
					
					p.sendMessage(prefix + ChatColor.RESET + ChatColor.GREEN + "Название клана: " + ChatColor.GOLD + clan_tag);
					
					List<String> members = plugin.clan.getStringList("clans." + name_clan + ".members");
					
					int members_all = 0;
					
					for(int i = 0; i < members.size(); i++) members_all++;
					
					p.sendMessage(prefix + ChatColor.GREEN + "Всего участников: " + ChatColor.GOLD + members_all);
					p.sendMessage(prefix + ChatColor.GREEN + "Всего убийств: " + ChatColor.GOLD + plugin.clan.getString("clans." + name_clan + ".kills"));
					p.sendMessage(prefix + ChatColor.GREEN + "Уровень клана: " + ChatColor.GOLD + plugin.clan.getString("clans." + name_clan + ".lvl"));
					p.sendMessage(prefix + ChatColor.GREEN + "Создатель клана: " + ChatColor.GOLD + plugin.clan.getString("clans." + name_clan + ".creator_name"));
				}
				
				return true;
			}
			else if(args[0].equalsIgnoreCase("leave")){
				
				String name_clan = plugin.clan.getString("players." + p.getUniqueId() + ".clan_name").toLowerCase();
				
				String clan_tag = plugin.clan.getString("clans." + name_clan + ".clan_tag");
				
				String name_co = plugin.clan.getString("clans." + name_clan + ".creator_name");
				
				if(!name_clan.equalsIgnoreCase("0")){
				
					if(!p.getName().equalsIgnoreCase(name_co)){
					
					List<String> members = plugin.clan.getStringList("clans." + name_clan + ".members");
					
					plugin.clan.set("players." + p.getUniqueId() + ".clan_name", 0);
					plugin.clan.set("players." + p.getUniqueId() + ".in_clan", false);
					plugin.clan.set("players." + p.getUniqueId() + ".clan_tag", 0);
				
					members.remove(p.getUniqueId().toString());
				
					plugin.clan.set("clans." + name_clan + ".members", members);
				
					clan_tag = clan_tag.replace("&", "\u00a7");
					
					p.sendMessage(prefix + ChatColor.GREEN + "Вы вышли из клана: " + ChatColor.GOLD + clan_tag);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return true;
				
					}
				}
			}
			else if(args[0].equalsIgnoreCase("tag")){
				
				sender.sendMessage(prefix + ChatColor.GREEN + "/aclan tag [тэг] - изменить тэг клана");
				return true;
			}
			else if(args[0].equalsIgnoreCase("leader")){
				
				sender.sendMessage(prefix + ChatColor.GREEN + "/aclan leader [ник] - передать права владельца кланом");
				
				return true;
			}
			else if(args[0].equalsIgnoreCase("ff")){
				
				sender.sendMessage(prefix + ChatColor.GREEN + "/aclan ff on/off - урон по союзникам");
				
				return true;
			}
			
		}
		
		else if(args.length == 2){
			
			if(args[0].equalsIgnoreCase("create")){				
					
				if(plugin.clan.contains("clans." + args[1].toString())){
					
					p.sendMessage(prefix + ChatColor.RED + "Клан с именем: " + args[1].toString() + ", уже существует!");
					
					return true;
				}
				
				if(plugin.clan.getBoolean("players." + p.getUniqueId() + ".in_clan") == false){
					
					if(!args[1].contains("&")){
					
					List<String> members = new ArrayList<String>();
					
					members.add(p.getUniqueId().toString());
					
					plugin.clan.set("clans." + args[1].toLowerCase() + ".creator_uuid", p.getUniqueId().toString());
					plugin.clan.set("clans." + args[1].toLowerCase() + ".creator_name", p.getName());
					plugin.clan.set("clans." + args[1].toLowerCase() + ".clan_tag", args[1].toString());
					plugin.clan.set("clans." + args[1].toLowerCase() + ".kills", 0);
					plugin.clan.set("clans." + args[1].toLowerCase() + ".lvl", 0);
					plugin.clan.set("clans." + args[1].toLowerCase() + ".members", members);
					plugin.clan.set("clans." + args[1].toLowerCase() + ".money", 0);
					plugin.clan.set("players." + p.getUniqueId() + ".in_clan", true);
					plugin.clan.set("players." +  p.getUniqueId() + ".clan_name", args[1].toLowerCase().toString());
					plugin.clan.set("players." + p.getUniqueId() + ".clan_tag", args[1].toString());		
					
					tags.put(args[1].toLowerCase(), args[1]);
					
					plugin.friendly_fire.put(args[1].toLowerCase(), "off");
					
					p.sendMessage(prefix + ChatColor.GREEN + "Вы создали клан: " + ChatColor.GOLD + args[1]);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  }else{
					  
					  p.sendMessage(prefix + ChatColor.RED + "Цветное название вы сможете изменить в " + ChatColor.DARK_GRAY + "/aclan tag [тэг]");
					  
				  }
					
				} else{
					
					p.sendMessage(prefix + ChatColor.RED + "У вас уже есть клан/ Вы являетесь участником");
					
				}
				
				return true;
			 }
			else if(args[0].equalsIgnoreCase("invite")){
				
				if(plugin.clan.getBoolean("players." + p.getUniqueId() + ".in_clan") == true){
					
					String clan_owner = plugin.clan.getString("players." + p.getUniqueId() + ".clan_name").toLowerCase();
					String creator_name = plugin.clan.getString("clans." + clan_owner + ".creator_name");
					
					String clan_tag = plugin.clan.getString("clans." + clan_owner + ".clan_tag");
					
					Player invite_player = Bukkit.getPlayer(args[1]);
						
					Boolean in_clan = plugin.clan.getBoolean("players." + invite_player.getUniqueId() + ".in_clan");
					
					if(creator_name.equalsIgnoreCase(p.getName())){
						
						if(in_clan == false){
							
							sender.sendMessage(prefix + ChatColor.GREEN + "Вы пригласили игрока: " + ChatColor.BLUE + invite_player.getName() + ChatColor.GREEN + " в клан");
							
							clan_tag = clan_tag.replace("&", "\u00a7");
							
							invite_player.sendMessage(prefix + ChatColor.GREEN + "Вас пригласил игрок: " + ChatColor.GOLD + p.getName() + ", в клан: " + clan_tag);
							invite_player.sendMessage(prefix + ChatColor.GREEN + "/aclan accept");
							invite_player.sendMessage(prefix + ChatColor.RED + "/aclan cancel");
							
							invite.put(invite_player.getName(), p.getName());
							
							try {
								plugin.clan.save(plugin.clanFile);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							return true;
							
						}
						
					}
					
				 
				
				}
				
			}
			else if(args[0].equalsIgnoreCase("kick")){
				
				String kick_player = args[1];
				
				String owner_cn = plugin.clan.getString("players." + p.getUniqueId() + ".clan_name").toLowerCase();
				
				String owner = plugin.clan.getString("clans." + owner_cn + ".creator_name");
				
				Player kp = Bukkit.getPlayer(kick_player);
				
				String kick_info = plugin.clan.getString("players." + kp.getUniqueId() + ".clan_name").toLowerCase();
				
				if(p.getName().equalsIgnoreCase(owner)){
					
					if(owner_cn.equalsIgnoreCase(kick_info)){
						
						plugin.clan.set("players." + kp.getUniqueId() + ".clan_tag" , 0);
						plugin.clan.set("players." + kp.getUniqueId() + ".clan_name" , 0);
						plugin.clan.set("players." + kp.getUniqueId() + ".in_clan" , false);
						
						List<String> members = plugin.clan.getStringList("clans." + owner_cn + ".members");
						
					    members.remove(kp.getUniqueId().toString());
						
						plugin.clan.set("clans." + owner_cn + ".members", members);
						
						try {
							plugin.clan.save(plugin.clanFile);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						return true;
						
					}
					
				}
				
				
			}
			else if(args[0].equalsIgnoreCase("tag")){
				
				if(plugin.clan.getString("players." + p.getUniqueId() + ".clan_name").equalsIgnoreCase("0")){ p.sendMessage(prefix + ChatColor.RED + "Вы не состоите в клане"); return true;}
				
				String clan_owner = plugin.clan.getString("players." + p.getUniqueId() + ".clan_name").toLowerCase();
				
				String clan_creator = plugin.clan.getString("clans." + clan_owner + ".creator_name");
				
				String clan_tag = plugin.clan.getString("clans." + clan_owner + ".clan_tag");
				
				if(!tags.containsValue(args[1])){
				
				if(p.getName().equalsIgnoreCase(clan_creator)){
					
				plugin.clan.set("clans." + clan_owner + ".clan_tag", args[1].toString());
				
				List<String> members = plugin.clan.getStringList("clans." + clan_owner + ".members");
				
				for(int i = 0; i < members.size(); i++){
					
					plugin.clan.set("players." + members.get(i).toString() + ".clan_tag", args[1].toString());
					
				}
				
				try {
					plugin.clan.save(plugin.clanFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				tags.replace(clan_owner, clan_tag, args[1]);
				
				p.sendMessage(prefix + ChatColor.GREEN + "Ваш клан-тэг изменён на: " + plugin.clan.getString("clans." + clan_owner + ".clan_tag").replace("&", "\u00a7"));
					
				
				} else p.sendMessage(prefix + ChatColor.RED + "Вы не создатель клана!"); 
				
				} else p.sendMessage(prefix + ChatColor.RED + "Данный префикс уже существует!");
				return true;
			}
			else if(args[0].equalsIgnoreCase("leader")){
				
				String name_clan = plugin.clan.getString("players." + p.getUniqueId() + ".clan_name").toLowerCase();
				
				String name_owner = plugin.clan.getString("clans." + name_clan + ".creator_name");
				
				Player lead = Bukkit.getPlayer(args[1].toString());
				
				String member = plugin.clan.getString("players." + lead.getUniqueId() + ".clan_name").toLowerCase();
				
				if(p.getName().equalsIgnoreCase(name_owner) && name_clan.equalsIgnoreCase(member)){
					
					plugin.clan.set("clans." + member + ".creator_name", lead.getName());
					plugin.clan.set("clans." + member + ".creator_uuid", lead.getUniqueId().toString());
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return true;
					
				} else{p.sendMessage(prefix + ChatColor.RED + "Вы не являетесь владельцем клана/ или не состоите в клане");}
				
			}
			else if(args[0].equalsIgnoreCase("ff")){
				
				String name_clan = plugin.clan.getString("players." + p.getUniqueId() + ".clan_name").toLowerCase();
				
				String clan_owner = plugin.clan.getString("clans." + name_clan + ".creator_name");
				
				if(p.getName().equalsIgnoreCase(clan_owner)){
					
				if(args[1].equalsIgnoreCase("on")){
					
					plugin.friendly_fire.replace(name_clan, "on");
					
					p.sendMessage(prefix + ChatColor.GREEN + "Урон по союзникам: " + ChatColor.RED + "включён");
					
				}
				
				else if(args[1].equalsIgnoreCase("off")){
					
					plugin.friendly_fire.replace(name_clan, "off");
					
					p.sendMessage(prefix + ChatColor.GREEN + "Урон по союзникам: " + ChatColor.RED + "выключен");
					
				}
				} else {
					
					p.sendMessage(prefix + ChatColor.RED + "Вы не являетесь создателем клана!");
					
				}
				
				
				return true;
			}
			else if(args[0].equalsIgnoreCase("reset")){
				
				if(args[1].equalsIgnoreCase("anarchy")){
					
					plugin.privat.set("players", null);
					plugin.privat.set("regions", null);
					
					p.sendMessage(prefix + ChatColor.GREEN + "Вы очистили конфиг 'private'");
					
					try {
						plugin.privat.save(plugin.privatFile);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return true;
				}
				
			}
			else if(args[0].equalsIgnoreCase("help")){
				
				if(args[1].equalsIgnoreCase("admin")){
					
					sender.sendMessage(prefix + ChatColor.GREEN + "-====== << Помощь >> ======- " + ChatColor.DARK_GRAY + "Страница 1/1");
					sender.sendMessage(prefix + ChatColor.DARK_GRAY + "/aclan add lvl/money [название клана] [кол-во] - добавить лвл/опыт в клан");
					sender.sendMessage(prefix + ChatColor.DARK_GRAY + "/aclan set lvl/money [название клана] [кол-во] - установить лвл/опыт в клан");
					sender.sendMessage(prefix + ChatColor.DARK_GRAY + "/aclan reset - отчистить конфиг с кланами");
					sender.sendMessage(prefix + ChatColor.DARK_GRAY + "/aclan reset anarchy - отчистить конфиг с анархией");
				}
				
			}
			else if(args[0].equalsIgnoreCase("give")){
				
				String name = args[1];
				
				String name_sender = plugin.clan.getString("players." + p.getUniqueId() + ".clan_name");
				
				List<String> members = plugin.clan.getStringList("clans." + name_sender + ".members");
				
				if(plugin.trade.containsKey(name)){
				
				for(int i = 0; i < members.size(); i++){
				
					if(plugin.clan.getString("players." + p.getUniqueId() + ".clan_name").equalsIgnoreCase(name_sender)){
					
					String name_player = plugin.clan.getString("players." + members.get(i) + ".name");
					
					if(name.equalsIgnoreCase(name_player)){
						
						Player gp = Bukkit.getPlayer(name_player);
						
						for(int j = 0; j < p.getInventory().getSize(); j++){
							
							if(plugin.trade.get(gp.getName()) == p.getInventory().getItem(j).getType()){
								
								int minus = p.getInventory().getItem(j).getAmount() - 1;
								
								p.getInventory().getItem(j).setAmount(minus);
								
								int summ = plugin.summ.get(name_player) + 1;
								
								plugin.summ.replace(name_player, summ);
								
								p.sendMessage(prefix + ChatColor.GREEN + "Вы отправили игроку: " + ChatColor.BLACK + gp.getName() + ChatColor.BLUE + " один ресурс");
								
								gp.sendMessage(prefix + ChatColor.GREEN + "Вам отправили ресурсы!");
								
								if(plugin.amount.get(name_player) == plugin.summ.get(gp.getName())){
									
									ItemStack item = new ItemStack(plugin.trade.get(gp.getName()));
									
									item.setAmount(plugin.amount.get(gp.getName()));
									
									plugin.amount.remove(name_player);
									plugin.trade.remove(name_player);
									plugin.summ.remove(name_player);
									
									gp.getInventory().addItem(item);
									
									return true;
									
								}
								
							}
							
						}
						
					}
					
					}
				}
			   }
			  }
			else if(args[0].equalsIgnoreCase("request")){
				
				int amount = Integer.parseInt(args[1]);
				
				if(plugin.trade.containsKey(p.getName())){
					
					p.sendMessage(prefix + ChatColor.RED + "Ваш запрос находится в обработке");
					
				}
				else if(p.getInventory().getItemInMainHand().getType() == Material.AIR){
					
					p.sendMessage(prefix + ChatColor.RED + "Возьмите в руки предмет, который хотите получить");
					
					
				}else{
					
					p.sendMessage(prefix + ChatColor.GREEN + "Вы запросили: " + amount + ", ресурса");
					
					Material mat = p.getInventory().getItemInMainHand().getType();
					
					plugin.trade.put(p.getName(), mat);
					plugin.amount.put(p.getName(), amount);
					plugin.summ.put(p.getName(), 0);
					
				}
				
				
			}
				
			}
		else if(args.length == 4){
			
			if(args[0].equalsIgnoreCase("set")){
				
				if(args[1].equalsIgnoreCase("money")){
					
					String name_clan = args[2];
					
					if(plugin.clan.contains("clans." + name_clan)){
						
						plugin.clan.set("clans." + name_clan + ".money", Integer.parseInt(args[3]));
						
						p.sendMessage(prefix + ChatColor.GREEN + "Вы изменили кол-во опыта в клане: " + ChatColor.LIGHT_PURPLE + name_clan + ChatColor.GREEN + ", на " + args[3]);
						
						try {
							plugin.clan.save(plugin.clanFile);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
				}
				else if(args[1].equalsIgnoreCase("lvl")){
					
					String name_clan = args[2];
					
					if(plugin.clan.contains("clans." + name_clan)){
						
						plugin.clan.set("clans." + name_clan + ".lvl", Integer.parseInt(args[3]));
						
						p.sendMessage(prefix + ChatColor.GREEN + "Вы изменили уровень клана: " + ChatColor.LIGHT_PURPLE + name_clan  + ChatColor.GREEN + ", на " + args[3]);
						
						try {
							plugin.clan.save(plugin.clanFile);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
				}
				
			}
			else if(args[0].equalsIgnoreCase("add")){
				
				if(args[1].equalsIgnoreCase("money")){
					
					String name_clan = args[2];
					
					if(plugin.clan.contains("clans." + name_clan)){
					
					int money = plugin.clan.getInt("clans." + name_clan + ".money");
					int summ = money + Integer.parseInt(args[3]);
					
					p.sendMessage(prefix + ChatColor.GREEN + "Вы добавили опыт клану: " + ChatColor.LIGHT_PURPLE + name_clan  + ChatColor.GREEN + ", на " + args[3]);
					
					plugin.clan.set("clans." + name_clan + ".money", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					}
				}
				else if(args[1].equalsIgnoreCase("lvl")){
					
					String name_clan = args[2];
					
					if(plugin.clan.contains("clans." + name_clan)){
					
					int money = plugin.clan.getInt("clans." + name_clan + ".lvl");
					int summ = money + Integer.parseInt(args[3]);
					
					p.sendMessage(prefix + ChatColor.GREEN + "Вы добавили уровень клану: " + ChatColor.LIGHT_PURPLE + name_clan  + ChatColor.GREEN + ", на " + args[3]);
					
					plugin.clan.set("clans." + name_clan + ".lvl", summ);
					
					try {
						plugin.clan.save(plugin.clanFile);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					}
					
				}
				
			}
			
		}
			
		
		
		
		return true;
	}

}
