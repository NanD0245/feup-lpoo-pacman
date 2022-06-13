package com.g50.controller;

import com.g50.Application;
import com.g50.gui.GUIObserver;

import java.io.IOException;

public abstract class Controller<T> implements GUIObserver {
    private final T model;

    protected Controller(T model) {
        this.model = model;
    }

    public abstract void update(Application application, int frame) throws IOException;

    public T getModel() {
        return model;
    }
}
