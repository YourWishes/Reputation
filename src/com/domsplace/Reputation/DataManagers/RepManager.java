/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.domsplace.Reputation.DataManagers;

import com.domsplace.Reputation.Bases.DataManager;
import com.domsplace.Reputation.Enums.ManagerType;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 *
 * @author Dominic Masters
 */
public class RepManager  extends DataManager {
    private YamlConfiguration yml;
    private File file;
    private List<OfflinePlayer> notify;
    
    public RepManager() {
        super(ManagerType.REP);
    }
    
    public YamlConfiguration getCFG() {return yml;}
    public List<OfflinePlayer> getPlayers() {return this.notify;}
    
    @Override
    public void tryLoad() throws IOException {
        file = new File(getDataFolder(), "reps.yml");
        if(!file.exists()) file.createNewFile();
        
        this.notify = new ArrayList<OfflinePlayer>();
        
        yml = YamlConfiguration.loadConfiguration(file);
        this.trySave();
    }
    
    @Override
    public void trySave() throws IOException {
        yml.save(file);
    }
    
    public int getRep(OfflinePlayer player) {
        int amount = 0;
        
        if(!yml.contains(player.getName().toLowerCase())) {
            return amount;
        }
        
        amount = yml.getInt(player.getName().toLowerCase());
        
        return amount;
    }
    
    public void addRep(OfflinePlayer player, int rep) {
        int oldRep = getRep(player);
        
        int newRep = oldRep + rep;
        
        setRep(player, newRep);
    }
    
    public boolean spendRep(OfflinePlayer player, int rep) {
        int oldRep = getRep(player);
        
        if(oldRep < rep) {
            return false;
        }
        
        int newRep = oldRep - rep;
        
        int oldSpentRep = DataManager.PLAYER_MANAGER.getSpentRep(player);
        int newSpentRep = oldSpentRep + rep;
        
        setRep(player, newRep);
        DataManager.PLAYER_MANAGER.setSpentRep(player, newSpentRep);
        
        return true;
    }
    
    public void setRep(OfflinePlayer player, int rep) {
        yml.set(player.getName().toLowerCase(), rep);
    }
}
