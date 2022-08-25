package com.annoriewa.module;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;

public class Module {

	 private String name;
	 private boolean state = false;
	 private int keybind;
	 private Category category;
	 protected Minecraft mc = Minecraft.getMinecraft();
	    
	    
	 public Module(String name, Category category, int keybind) {
	        this.name = name;
	        this.category = category;
	        this.keybind = keybind;
	    }   
	
	 
	 public Category getCategory() {
	        return category;
	    }

	 public void setCategory(Category category) {
	        this.category = category;
	    }

	 public String getName() {
	        return name;
	    }

	 public void setName(String name) {
	        this.name = name;
	    }

	 public boolean getState() {
	        return state;
	    }

	 public void setState(boolean state) {
	        this.state = state;
	    }

	 public int getKeybind() {
	        return keybind;
	    }

	 public void setKeybind(int keybind) {
	        this.keybind = keybind;
	    }
	    
	 public void onEnable(){}

	 public void onDisable(){}
	
	 
	 @Override
	 public String toString(){
	        return this.getName();
	    }
	    
}




