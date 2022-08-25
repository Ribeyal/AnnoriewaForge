package com.annoriewa.module.misc;

import org.lwjgl.input.Keyboard;

import com.annoriewa.Annoriewa;
import com.annoriewa.event.EventTarget;
import com.annoriewa.event.events.gui.Render2DEvent;
import com.annoriewa.module.Category;
import com.annoriewa.module.Module;
import com.annoriewa.utils.ColorUtils;
import java.awt.*;
import java.io.IOException;
import java.util.logging.*;

public class HUD extends Module{

	
	public HUD() {
		super("HUD", Category.MISC, Keyboard.KEY_NONE);
	}
	
	
	@EventTarget
	public void onRender2D(Render2DEvent render2DEvent){
			  
			String text = "Annoriewa";

	        char[] chars = text.toCharArray();

	        int pos = 2;
	        for(int i = 0; i < chars.length; i++) {
	            char ch = chars[i];
	            int color = i == 0 ? ColorUtils.TAB_SEPARATOR_COLOR : Color.WHITE.getRGB();
	            String newText = new String(new char[] {ch});
	            Annoriewa.getInstance().getCopyrightFont().drawStringWithShadow(newText, pos, 24, color);
	            pos += Annoriewa.getInstance().getCopyrightFont().getStringWidth(newText);
	        }
		}
	}

	
	