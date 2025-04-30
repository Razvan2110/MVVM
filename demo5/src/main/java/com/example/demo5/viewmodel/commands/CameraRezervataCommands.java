package com.example.demo5.viewmodel.commands;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class CameraRezervataCommands {
    public final BooleanProperty trigger = new SimpleBooleanProperty(false);
    private final Runnable action;

    public CameraRezervataCommands(Runnable action) {
        this.action = action;


        trigger.addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                execute();
                trigger.set(false); // Reset the trigger
            }
        });
    }

    public void execute() {
        action.run();
    }
}