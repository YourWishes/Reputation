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
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * @author      Dominic
 * @since       12/10/2013
 */
public class GiveReputationCommand extends BukkitCommand {
    public GiveReputationCommand() {
        super("giverep");
    }
    
    @Override
    public boolean cmd(CommandSender sender, Command cmd, String label, String[] args) {        
        if(args.length < 1) {
            sendMessage(sender, ChatError + "Pleas enter a player name.");
            return false;
        }
        
        if(args.length < 2) {
            sendMessage(sender, ChatError + "Please enter an amount.");
            return false;
        }
        
        int amt = 0;
        if(!isInt(args[1]) || (amt = getInt(args[1])) <= 0) {
            sendMessage(sender, ChatError + "Amount must be a valid number of 1 or above.");
            return true;
        }
        
        OfflinePlayer target = Base.getOfflinePlayer(sender, args[0]);
        
        if(target == null || target.getName().equalsIgnoreCase(sender.getName())) {
            sendMessage(sender, ChatError + "Couldn't find that player.");
            return true;
        }
        
        DataManager.REP_MANAGER.addRep(target, amt);
        sendMessage(sender, "Gave " + ChatImportant + getDisplayName(target) + ChatDefault + 
            amt + " rep."
        );
        return true;
    }
}
