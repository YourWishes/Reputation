package com.domsplace.Reputation.Hooks;

import com.domsplace.Reputation.Bases.PluginHook;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultHook extends PluginHook {
    private Permission permission = null;
    private Chat chat = null;
    
    public VaultHook() {
        super("Vault");
        this.shouldHook(true);
    }
    
    public Permission getPermission() {
        try {
            return permission;
        } catch(NoClassDefFoundError e) {
            return null;
        }
    }
    
    public Chat getChat() {
        try {
            return chat;
        } catch(NoClassDefFoundError e) {
            return null;
        }
    }
    
    private boolean setupPermission() {
        try {
            RegisteredServiceProvider<Permission> provider = Bukkit.getServer().getServicesManager().getRegistration(Permission.class);
            if (provider != null) {
                permission = provider.getProvider();
            }

            return (permission != null);
        } catch(NoClassDefFoundError e) {
            permission = null;
            return false;
        }
    }
    
    private boolean setupChat() {
        try {
            RegisteredServiceProvider<Chat> provider = Bukkit.getServer().getServicesManager().getRegistration(Chat.class);
            if (provider != null) {
                chat = provider.getProvider();
            }

            return (chat != null);
        } catch(NoClassDefFoundError e) {
            chat = null;
            return false;
        }
    }
    
    @Override
    public void onHook() {
        super.onHook();
        this.setupPermission();
        this.setupChat();
    }
    
    @Override
    public void onUnhook() {
        super.onUnhook();
        this.permission = null;
        this.chat = null;
    }
}
