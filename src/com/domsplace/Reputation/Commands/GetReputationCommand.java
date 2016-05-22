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

package com.domsplace.Reputation.Commands;

import com.domsplace.Reputation.Bases.Base;
import com.domsplace.Reputation.Bases.BukkitCommand;
import com.domsplace.Reputation.Bases.DataManager;
import com.domsplace.Reputation.DataManagers.PlayerManager;
import com.domsplace.Reputation.DataManagers.RepManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * @author      Dominic
 * @since       12/10/2013
 */
public class GetReputationCommand extends BukkitCommand {
    public GetReputationCommand() {
        super("getrep");
    }
    
    @Override
    public boolean cmd(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length < 1 && !isPlayer(sender)) {
            sendMessage(sender, ChatError + "Please enter a player name.");
            return false;
        }
        
        OfflinePlayer target = null;
        if(args.length > 0) {
            target = Base.getOfflinePlayer(sender, args[0]);
        } else {
            target = Bukkit.getOfflinePlayer(sender.getName());
        }
        
        if(target == null) {
            sendMessage(sender, ChatError + "Couldn't find that player.");
            return true;
        }
        
        sendMessage(sender, 
            ChatImportant + getDisplayName(target) + ChatDefault + " has " +
            ChatImportant + RepManager.REP_MANAGER.getRep(target) + ChatDefault + " rep points, and has spent " +
            ChatImportant + PlayerManager.PLAYER_MANAGER.getSpentRep(target) + ChatDefault + " rep points."
        );
        return true;
    }
}
