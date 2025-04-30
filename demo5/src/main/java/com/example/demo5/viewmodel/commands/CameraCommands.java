package com.example.demo5.viewmodel.commands;

import com.example.demo5.interfaces.ICommand;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class CameraCommands implements ICommand {
    public final BooleanProperty trigger = new SimpleBooleanProperty(false);
    private final Runnable action;

    public CameraCommands(Runnable action) {
        this.action = action;


        trigger.addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                execute();
                trigger.set(false);
            }
        });
    }

    @Override
    public void execute() {
        action.run();
    }
}