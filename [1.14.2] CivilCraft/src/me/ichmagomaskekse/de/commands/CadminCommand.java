package me.ichmagomaskekse.de.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ichmagomaskekse.de.CivilCraft;
import me.ichmagomaskekse.de.filesystem.FileManager;
import me.ichmagomaskekse.de.permissions.PermissionManager;

public class CadminCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(PermissionManager.hasPermission(p, "cadmin")) {
				if(args.length == 0) {
					sendCadminInfo(p);					
				}else if(args.length == 1) {
					if(args[0].equals("reload")) {
						if(!PermissionManager.hasPermission(p, "cadmin reload")) return false;
//						CivilCraft.reload(sender);
						PermissionManager.reloadData();
					}
				}
				if(args.length >= 1) {
					if(args[0].equals("perms")) {
						CPermFunctions.computeCommand(sender, cmd, label, args);
					}
				}
			}
		} else {
//			CivilCraft.sendErrorInfo(sender, "", "Dieser Befehl ist bisher nur f�r Spieler vorgesehen");
			if(args.length == 0) {
				sendCadminInfo(sender);					
			}else if(args.length == 1) {
				if(args[0].equals("reload")) {
					CivilCraft.sendInfo(sender, "", "Lade Daten neu");
					if(FileManager.reloadData()) {
						CivilCraft.sendInfo(sender, "", "Daten wurden neu geladen!");
					}
				}
			}
			if(args.length >= 1) {
				if(args[0].equals("perms")) {
					CPermFunctions.computeCommand(sender, cmd, label, args);
				}
			}
		}
		
		return false;
	}
	
	public void sendCadminInfo(CommandSender sender) {
		sender.sendMessage("�f/cadmin �aHauptbefehl f�r "+FileManager.server_prefix);
		sender.sendMessage("�f/cadmin reload �aLade alle Daten neu");
		sender.sendMessage("�f/cadmin perms �aCivilCraft Permission-System");
	}
	
}
