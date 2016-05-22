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

package com.domsplace.Reputation;

import com.domsplace.Reputation.Commands.*;
import com.domsplace.Reputation.Bases.*;
import com.domsplace.Reputation.Listeners.*;
import com.domsplace.Reputation.Threads.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author      Dominic
 * @since       04/10/2013
 */
public class ReputationPlugin extends JavaPlugin {
    private boolean enabled = false;
    
    //Commands
    private DoNothingCommand doNothingCommand;
    private GetReputationCommand getRepCommand;
    private GiveReputationCommand giveRepCommand;
    private SendRepCommand sendRepCommand;
    private SetRepCommand setRepCommand;
    private BuyKitCommand buyKitCommand;
    
    //Listeners
    private CustomEventCommandListener customEventCommandListener;
    private PlayDirtyListener playDirtyListener;
    private ServerUnloadListener serverUnloadListener;
    private ReputationListener repListener;
    
    //Threads
    private ConfigSaveThread playerTimeThread;
    private ReputationThread reputationThread;
    
    @Override
    public void onEnable() {
        //Register Plugin
        Base.setPlugin(this);
        
        //Load Data
        if(!DataManager.loadAll()) {
            this.disable();
            return;
        }
        
        //Load Commands
        this.doNothingCommand = new DoNothingCommand();
        this.getRepCommand = new GetReputationCommand();
        this.giveRepCommand = new GiveReputationCommand();
        this.sendRepCommand = new SendRepCommand();
        this.setRepCommand = new SetRepCommand();
        this.buyKitCommand = new BuyKitCommand();
        
        //Load Listeners
        this.customEventCommandListener = new CustomEventCommandListener();
        this.playDirtyListener = new PlayDirtyListener();
        this.serverUnloadListener = new ServerUnloadListener();
        this.repListener = new ReputationListener();
        
        //Load Threads
        this.playerTimeThread = new ConfigSaveThread();
        this.reputationThread = new ReputationThread();
        
        PluginHook.hookAll();
        
        this.enabled = true;
        Base.debug("Enabled " + this.getName());
    }
    
    @Override
    public void onDisable() {
        if(!enabled) {
            return;
        }
        
        DomsThread.stopAllThreads();
        DataManager.saveAll();
    }
    
    public void disable() {
        Bukkit.getPluginManager().disablePlugin(this);
    }
}
