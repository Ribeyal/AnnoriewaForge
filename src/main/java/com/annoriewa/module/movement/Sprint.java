package com.annoriewa.module.movement;

import org.lwjgl.input.Keyboard;

import com.annoriewa.event.EventTarget;
import com.annoriewa.event.events.TickEvent;
import com.annoriewa.module.Category;
import com.annoriewa.module.Module;

public class Sprint extends Module{

	
	public Sprint() {
		super("Sprint", Category.MOVEMENT, Keyboard.KEY_N);

	}
	
    @EventTarget
    public void onUpdate(TickEvent tickEvent) {
        if (this.getState() && this.mc.gameSettings.keyBindForward.isKeyDown()) {
            this.mc.thePlayer.setSprinting(true);
        }
    }
}
