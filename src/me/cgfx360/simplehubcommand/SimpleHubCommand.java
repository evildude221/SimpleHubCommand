package me.cgfx360.simplehubcommand;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;



public class SimpleHubCommand extends JavaPlugin{
	
	public final Logger logger = Logger.getLogger("Minecraft");
	public static SimpleHubCommand plugin;
	
	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Has Been Disabled!");
		
		saveConfig();
	
	}
    
	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " Has Been Enabled!");
		
		getConfig().options().copyDefaults(true);
	    saveConfig();
	    
	    
	    }	
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
		if(commandLabel.equalsIgnoreCase("sethub") && 
		  (player.hasPermission("hub.set"))){
        World world = player.getWorld();	
				
		double value = player.getLocation().getX();
		double value2 = player.getLocation().getY();
        double value3 = player.getLocation().getZ();
		double value4 = player.getLocation().getYaw();
		double value5 = player.getLocation().getPitch();
		getConfig().set("X", Double.valueOf(value));
		getConfig().set("Y", Double.valueOf(value2));
		getConfig().set("Z", Double.valueOf(value3));
		getConfig().set("Yaw", Double.valueOf(value4));
		getConfig().set("Pitch", Double.valueOf(value5));
		saveWorld(world);
		saveConfig();
					
		   player.sendMessage(ChatColor.GOLD + "Hub Spawnpoint has been set!"); }
			
			
	 
	      
    
		
		
	        	
		if (commandLabel.equalsIgnoreCase("hub") &&
		  (player.hasPermission("hub.use"))){
	           
            
            
            player.teleport(new Location(loadWorld(), getConfig().getInt("X"), getConfig().getInt("Y"), getConfig().getInt("Z"), getConfig().getInt("Yaw"), getConfig().getInt("Pitch")));
          
            player.sendMessage(ChatColor.GREEN + "You are at hub!"); }
       
           
        
            
		      
		
		
		
		
		
		return false;
		
	}
	
	public void saveWorld(World world) {
	    getConfig().set("world", world.getName());
	  }

	  public World loadWorld() {
	    String w = getConfig().getString("world");
	    World world = getServer().getWorld(w);
	    return world;
	  }
	  
	  
	  
	  
	
}
