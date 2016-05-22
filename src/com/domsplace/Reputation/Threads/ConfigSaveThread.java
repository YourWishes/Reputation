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

import com.domsplace.Reputation.Bases.DataManager;
import com.domsplace.Reputation.Bases.DomsThread;

/**
 * @author      Dominic
 */
public class ConfigSaveThread extends DomsThread {
    public ConfigSaveThread() {
        super(1, 600);
    }
    
    @Override
    public void run() {
        if(DataManager.saveAll()) return;
        log("Failed to save Config! Check Console for Errors!");
    }
}
