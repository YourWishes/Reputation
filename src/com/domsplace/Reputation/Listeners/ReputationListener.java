/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.domsplace.Reputation.Listeners;

import com.domsplace.Reputation.Bases.DomsListener;
import com.domsplace.Reputation.DataManagers.RepManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 *
 * @author Dominic Masters
 */
public class ReputationListener extends DomsListener {
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e) {
        RepManager.PLAYER_MANAGER.recordLastJoin(e.getPlayer());
        if(RepManager.REP_MANAGER.getPlayers().contains((OfflinePlayer) e.getPlayer())) {
            e.getPlayer().sendMessage(ChatImportant + "You have recieved a rep point for being online today.");
            RepManager.REP_MANAGER.getPlayers().remove((OfflinePlayer) e.getPlayer());
        }
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        RepManager.PLAYER_MANAGER.recordLastJoin(e.getPlayer());
    }
}
