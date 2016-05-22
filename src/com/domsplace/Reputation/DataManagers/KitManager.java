/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.domsplace.Reputation.DataManagers;

import com.domsplace.Reputation.Bases.Base;
import com.domsplace.Reputation.Bases.DataManager;
import com.domsplace.Reputation.Enums.ManagerType;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 *
 * @author Dominic Masters
 */
public class KitManager  extends DataManager {
    private YamlConfiguration yml;
    private File file;
    
    public KitManager() {
        super(ManagerType.KIT);
    }
    
    public YamlConfiguration getCFG() {return yml;}
    
    @Override
    public void tryLoad() throws IOException {
        file = new File(getDataFolder(), "kits.yml");
        if(!file.exists()) file.createNewFile();
        yml = YamlConfiguration.loadConfiguration(file);
        
        
        
        this.trySave();
    }
    
    @Override
    public void trySave() throws IOException {
        yml.save(file);
    }
    
    public int getKitCost(String kit) {
        return yml.getInt(kit + ".cost");
    }
    
    public String[] getKits() {
        return Base.setToArray(yml.getKeys(false));
    }
    
    public List<String> getKitCommands(String kit) {
        return yml.getStringList(kit + ".commands");
    }
    
    public void executeCommands(List<String> commands, CommandSender cs) {
        for (String command : commands) {
            command = command.replaceAll("(?i)\\{player\\}", cs.getName());
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), command);
        }
    }

    public boolean hasKitPermission(String k, CommandSender cs) {
        if(!cs.hasPermission("reputation.kit." + k)) {
            return false;
        }
        return true;
    }
    
    public boolean doesKitExist(String kit) {
        if(!yml.contains(kit)) {
            return false;
        }
        return true;
    }
    
}
