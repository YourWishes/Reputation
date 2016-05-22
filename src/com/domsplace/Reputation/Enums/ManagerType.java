package com.domsplace.Reputation.Enums;

import com.domsplace.Reputation.Bases.DomsEnum;

public class ManagerType extends DomsEnum {
    public static final ManagerType CONFIG = new ManagerType("Configuration");
    public static final ManagerType PLUGIN = new ManagerType("Plugin");
    public static final ManagerType PLAYER = new ManagerType("Player");
    public static final ManagerType REP = new ManagerType("Reputation");
    public static final ManagerType KIT = new ManagerType("Kit");
    
    //Instance
    private String type;
    
    public ManagerType(String type) {
        this.type = type;
    }
    
    public String getType() {
        return this.type;
    }
}
