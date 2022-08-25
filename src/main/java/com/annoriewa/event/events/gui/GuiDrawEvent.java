package com.annoriewa.event.events.gui;

import com.annoriewa.event.Event;

import net.minecraft.client.gui.GuiScreen;

public class GuiDrawEvent extends Event {

    public GuiScreen guiScreen;
    public GuiDrawState state;

    public GuiDrawEvent(GuiScreen guiScreen, GuiDrawState state) {
        this.guiScreen = guiScreen;
        this.state = state;
    }

    public static enum GuiDrawState {
        PRE,
        POST
    }
}