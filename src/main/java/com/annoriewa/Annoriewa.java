package com.annoriewa;

import java.awt.*;

import com.annoriewa.event.EventBus;
import com.annoriewa.gui.font.FontGL11;
import com.annoriewa.helpers.ReflectionHelper;
import com.annoriewa.hook.ProfilerHook;
import com.annoriewa.module.Module;
import com.annoriewa.module.ModuleManager;
import com.annoriewa.utils.EventUtils;

import net.minecraft.client.Minecraft;
import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.List;


public class Annoriewa {
	
	
	public static Annoriewa instance;
	private int version = 1;
	private FontGL11 copyrightFont;
	private List<Module> modules;
	public ModuleManager modulemanager;

    
    public Annoriewa() throws Throwable {
    	instance = new Annoriewa();
    	instance.start();
    }
	
	
	public void start() throws Throwable {
		
		
			modulemanager = new ModuleManager();
			ReflectionHelper.lookup = MethodHandles.lookup();
			modulemanager.loadModules();
			ReflectionHelper.hookField(ReflectionHelper.getField(Minecraft.class, "mcProfiler", "field_71424_I", "B"), Minecraft.getMinecraft(), new ProfilerHook());
			EventBus.register(new EventUtils());
			
	}
	
	
	
	public final static Annoriewa getInstance(){
		return instance;
	}
	
    public FontGL11 getCopyrightFont() {
        if(copyrightFont == null) {
            copyrightFont = new FontGL11(new Font("Linux Biolinum", Font.BOLD, 30), true, true);
        }
        return copyrightFont;
    }
    

	public ModuleManager getModuleManager(){
		return modulemanager;
	}
 
    
}