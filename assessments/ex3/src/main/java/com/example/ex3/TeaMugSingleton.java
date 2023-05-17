package com.example.ex3;

import com.example.ex3.mugs.TeaMug;

public final class TeaMugSingleton {
    private TeaMug mug;
    private final static TeaMugSingleton INSTANCE = new TeaMugSingleton();

    private TeaMugSingleton() {

    }

    public static TeaMugSingleton getInstance() {
        return INSTANCE;
    }

    public void setMug(TeaMug mug) {
        this.mug = mug;
    }

    public TeaMug getMug() {
        return mug;
    }
}
