package com.zagurskaya.cash.controller.command;

import com.zagurskaya.cash.controller.command.Сommand;

public abstract class AbstractСommand implements Сommand {
    private final String path;

    public AbstractСommand(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
