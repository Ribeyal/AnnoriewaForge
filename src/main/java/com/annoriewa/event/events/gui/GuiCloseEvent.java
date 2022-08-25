package com.annoriewa.event.events.gui;

import com.annoriewa.event.Event;

import net.minecraft.client.gui.GuiScreen;

public class GuiCloseEvent extends Event {

    public GuiScreen screen;

    public GuiCloseEvent(GuiScreen screen) {
        this.screen = screen;
    }
}