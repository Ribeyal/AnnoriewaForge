package com.annoriewa.module;


import java.util.ArrayList;
import java.util.List;

import com.annoriewa.Annoriewa;
import com.annoriewa.event.EventBus;
import com.annoriewa.module.misc.HUD;
import com.annoriewa.module.movement.Sprint;


public class ModuleManager {
	
    private static List<Module> modules = new ArrayList();
    
    public static void disableModule(String moduleName) {
        Module module = ModuleManager.getModule(moduleName);
        if (module != null) {
            module.setState(false);
        }
    }

    public static Module getModule(Class moduleClass) {
        for (Module module : modules) {
            if (module.getClass() != moduleClass) {
                continue;
            }
            return module;
        }
        return null;
    }

    public static Module getModule(String moduleName) {
        for (Module module : modules) {
            if (!module.getName().equals(moduleName)) {
                continue;
            }
            return module;
        }
        return null;
    }

    public static List<Module> getModules() {
        return modules;
    }

    public static boolean isModuleEnabled(String moduleName) {
        Module module = ModuleManager.getModule(moduleName);
        if (module != null) {
            return module.getState();
        }
        return false;
    }

    public static boolean isModuleEnabled(Class moduleClass) {
        Module module = ModuleManager.getModule(moduleClass);
        if (module != null) {
            return module.getState();
        }
        return false;
    }
    
    private static void registerModule(Module module){
        	EventBus.register(module);
        	modules.add(module);
    }
    
    
    public static void loadModules()  {
    		registerModule(new HUD());
    		registerModule(new Sprint());
    }

}
