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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 *
 * @author Dominic Masters
 */
public class PlayerManager  extends DataManager {
    private YamlConfiguration yml;
    private File file;
    
    public PlayerManager() {
        super(ManagerType.PLAYER);
    }
    
    public YamlConfiguration getCFG() {return yml;}
    
    @Override
    public void tryLoad() throws IOException {
        file = new File(getDataFolder(), "players.yml");
        if(!file.exists()) file.createNewFile();
        
        yml = YamlConfiguration.loadConfiguration(file);
        this.trySave();
    }
    
    @Override
    public void trySave() throws IOException {
        yml.save(file);
    }

    public int getSpentRep(OfflinePlayer player) {
        int amount = 0;
        
        if(!yml.contains(player.getName().toLowerCase() + ".pointsspent")) {
            return amount;
        }
        
        amount = yml.getInt(player.getName().toLowerCase() + ".pointsspent");
        
        return amount;
    }
    
    public void setSpentRep(OfflinePlayer player, int spentRep) {
        yml.set(player.getName().toLowerCase() + ".pointsspent", spentRep);
    }
    
    public void recordLastJoin(OfflinePlayer player) {
        long time = new Date().getTime();
        yml.set(player.getName().toLowerCase() + ".jointimestamp",  time);
    }
    
    public long getLastJoin(OfflinePlayer player) {
        if(!yml.contains(player.getName().toLowerCase() + ".jointimestamp")) {
            return 0;
        }
        long time = yml.getLong(player.getName().toLowerCase() + ".jointimestamp");
        return time;
    }
    
    public void setRepTime(OfflinePlayer player) {
        long time = new Date().getTime();
        yml.set(player.getName().toLowerCase() + ".reptime",  time);
    }
    
    public long getRepTime(OfflinePlayer player) {
        if(!yml.contains(player.getName().toLowerCase() + ".reptime")) {
            return 0;
        }
        long time = yml.getLong(player.getName().toLowerCase() + ".reptime");
        return time;
    }
    
    public void setVoteTime(OfflinePlayer player) {
        long time = new Date().getTime();
        yml.set(player.getName().toLowerCase() + ".votetime",  time);
    }
    
    public long getVoteTime(OfflinePlayer player) {
        if(!yml.contains(player.getName().toLowerCase() + ".votetime")) {
            return 0;
        }
        long time = yml.getLong(player.getName().toLowerCase() + ".votetime");
        return time;
    }
    
    public boolean areSameDay(Date a, Date b) {
        Calendar ca = Calendar.getInstance();
        Calendar cb = Calendar.getInstance();
        ca.setTime(a);
        cb.setTime(b);
        return ca.get(Calendar.DAY_OF_MONTH) == cb.get(Calendar.DAY_OF_MONTH) && ca.get(Calendar.YEAR) == cb.get(Calendar.YEAR) && ca.get(Calendar.MONTH) == cb.get(Calendar.MONTH);
    }
    
    public List<OfflinePlayer> getRepPlayers() {
        List<OfflinePlayer> players = new ArrayList<OfflinePlayer>();
        
        Set<String> player = yml.getKeys(false);
        for(String p : player) {
            players.add(Bukkit.getServer().getOfflinePlayer(p));
        }
        
        return players;
    }
}
