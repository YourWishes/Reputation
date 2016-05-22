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

package com.domsplace.Reputation.Events;

import com.domsplace.Reputation.Bases.Base;
import com.domsplace.Reputation.Bases.BukkitCommand;
import com.domsplace.Reputation.Bases.DomsCancellableEvent;
import java.util.List;
import org.bukkit.command.CommandSender;

public class PreCommandEvent extends DomsCancellableEvent {
    
    private CommandSender player;
    private String command;
    private List<String> args;
    
    public PreCommandEvent(CommandSender player, String command, List<String> args) {
        this.player = player;
        this.command = command;
        this.args = args;
    }
    
    public CommandSender getPlayer() {return this.player;}
    public String getCommand() {return this.command;}
    public List<String> getArgs() {return this.args;}
    
    public String toFullCommand() {
        String s = this.command;
        for(String str : args) {
            s += " " + str;
        }
        
        return s;
    }
    
    public BukkitCommand tryGetBukkitCommand() {return BukkitCommand.getCommandSearchAliases(this.command);}
    
    public boolean willResult(String s) {
        if(s.equalsIgnoreCase(command)) return true;
        BukkitCommand cmd = this.tryGetBukkitCommand();
        if(cmd == null) return false;
        return cmd.isCommand(s);
    }
}
