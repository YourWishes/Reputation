/*
 * Copyright 2013 Dominic.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.domsplace.Reputation.Threads;

import com.domsplace.Reputation.Bases.DomsThread;
import com.domsplace.Reputation.DataManagers.PlayerManager;
import java.util.Date;
import java.util.List;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

/**
 * @author      Dominic
 */
public class ReputationThread extends DomsThread {    
    public ReputationThread() {
        super(10, 600, true);
    }
    
    @Override
    public void run() {
        List<OfflinePlayer> ops = PlayerManager.PLAYER_MANAGER.getRepPlayers();
        for (OfflinePlayer op : ops) {
            Date lastRep = new Date(PlayerManager.PLAYER_MANAGER.getRepTime(op));
            if (PlayerManager.PLAYER_MANAGER.getRepTime(op) > 0) {
                if (PlayerManager.PLAYER_MANAGER.areSameDay(lastRep, new Date())){
                    continue;
                }
            }
            Date lastJoin = new Date(PlayerManager.PLAYER_MANAGER.getLastJoin(op));
            if (!PlayerManager.PLAYER_MANAGER.areSameDay(lastJoin, new Date())) {
                continue;
            }
            PlayerManager.REP_MANAGER.addRep(op, 1);
            PlayerManager.PLAYER_MANAGER.setRepTime(op);
            if (op.isOnline()) {
                Player p = op.getPlayer();
                p.sendMessage(ChatImportant + "You have recieved a rep point for being online today.");
            } else {
                PlayerManager.REP_MANAGER.getPlayers().add(op);
            }
        }

        //Save Rep
        PlayerManager.saveAll();
    }
}
