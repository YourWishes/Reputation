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
import com.domsplace.Reputation.DataManagers.KitManager;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author      Dominic
 * @since       12/10/2013
 */
public class BuyKitCommand extends BukkitCommand {
    public BuyKitCommand() {
        super("buykit");
    }
    
    @Override
    public boolean cmd(CommandSender sender, Command cmd, String label, String[] args) {        
        if(!isPlayer(sender)) {
            sendMessage(sender, ChatError + "Only players can do this.");
            return true;
        }
        
        if(args.length < 1) {
            //Get Kits
            String[] kits = KitManager.KIT_MANAGER.getKits();
            List<String> kitsValid = new ArrayList<String>();
            
            for(String kit : kits) {
                if(!KitManager.KIT_MANAGER.hasKitPermission(kit, sender)) continue;
                kitsValid.add(kit);
            }
            
            sendMessage(sender, new String[] {
                ChatImportant + "Kits you can buy: ",
                ChatDefault + Base.arrayToString(Base.listToArray(kitsValid),", ")
            });
            return true;
        }
        
        String kit = args[0].toLowerCase().replaceAll(" ", "");
        
        if(!KitManager.KIT_MANAGER.doesKitExist(kit)) {
            sendMessage(sender, ChatError + "That kit doesn't exist.");
            return true;
        }
        
        if(!KitManager.KIT_MANAGER.hasKitPermission(kit, sender)) {
            return this.noPermission(sender, cmd, label, args);
        }
        
        Player player = getPlayer(sender);
        
        int cost = KitManager.KIT_MANAGER.getKitCost(kit);
        if(!KitManager.REP_MANAGER.spendRep(player, cost)) {
            sender.sendMessage(ChatError + "You don't have enough rep to buy this. This costs " + cost);
            return true;
        }

        sendMessage(sender, 
                ChatDefault + "Purchased " + ChatImportant + kit + ChatDefault +
                        " for " + ChatImportant + cost + ChatDefault + " rep.");

        /* Run commands */
        KitManager.KIT_MANAGER.executeCommands(KitManager.KIT_MANAGER.getKitCommands(kit), sender);
        return true;
    }
}
