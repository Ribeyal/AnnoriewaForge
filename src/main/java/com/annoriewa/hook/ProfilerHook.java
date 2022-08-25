package com.annoriewa.hook;

import com.annoriewa.event.EventBus;
import com.annoriewa.event.events.TickEvent;
import com.annoriewa.event.events.gui.Render2DEvent;

import net.minecraft.client.Minecraft;
import net.minecraft.profiler.Profiler;

public class ProfilerHook extends Profiler {
	
	private Minecraft mc = Minecraft.getMinecraft();

	 @Override
	  public void startSection(String string) {
		   
	      if(string.equals("tick")) {
	          if (this.mc != null && this.mc.thePlayer != null && this.mc.theWorld != null) {
	              EventBus.call(new TickEvent());
	          }
	      }
		   
	      if (string.equals("gui")) {
	          Minecraft.getMinecraft().entityRenderer.setupOverlayRendering();
	          EventBus.call(new Render2DEvent());
	      }

	      super.startSection(string);
	  }
	   

}