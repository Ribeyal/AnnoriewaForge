package com.annoriewa.event.events.gui;

import com.annoriewa.event.Event;

import net.minecraft.client.gui.GuiScreen;

public class GuiOpenEvent extends Event {

    public GuiScreen screen;

    public GuiOpenEvent(GuiScreen screen) {
        this.screen = screen;
    }
}