package com.annoriewa.utils;

import com.annoriewa.Annoriewa;
import com.annoriewa.event.EventTarget;
import com.annoriewa.event.events.TickEvent;
import com.annoriewa.module.Module;

import net.minecraft.client.Minecraft;

public class EventUtils {
	private Minecraft mc = Minecraft.getMinecraft();
	private KeyboardUtils keyboardUtils = new KeyboardUtils(250L);
	
	
	@EventTarget
	public void onTick(TickEvent tickEvent) {
		
		for (Module module : Annoriewa.getInstance().getModuleManager().getModules()) {
            if (keyboardUtils.isKeyDown(module.getKeybind())) {
                module.setState(!module.getState());
                boolean state = module.getState();
                if (state)
                    module.onEnable();
                else
                    module.onDisable();
            
            }
		} 
	}	
}
