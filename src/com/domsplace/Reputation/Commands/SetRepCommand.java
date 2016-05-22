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
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * @author      Dominic
 * @since       12/10/2013
 */
public class SetRepCommand extends BukkitCommand {
    public SetRepCommand() {
        super("sendrep");
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
        if(!isInt(args[1])) {
            sendMessage(sender, ChatError + "Amount must be a valid number.");
            return true;
        }
        
        OfflinePlayer target = Base.getOfflinePlayer(sender, args[0]);
        
        if(target == null) {
            sendMessage(sender, ChatError + "Couldn't find that player.");
            return true;
        }
        
        DataManager.REP_MANAGER.setRep(target, amt);
        sendMessage(sender, "Set " + ChatImportant + getDisplayName(target) + ChatDefault +  "' rep to " + amt + ".");
        return true;
    }
}
