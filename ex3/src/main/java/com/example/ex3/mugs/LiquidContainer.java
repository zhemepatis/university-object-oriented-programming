package com.example.ex3.mugs;

public class LiquidContainer {
    protected String color;
    protected String liquid;
    protected double volume;

    public LiquidContainer(String color, String liquid,  double volume) {
        setColor(color);
        setLiquid(liquid);
        setVolume(volume);
    }

    void setColor(String color) {
        this.color = color;
    }

    void setLiquid(String liquid) {
        this.liquid = liquid;
    }

    void setVolume(double volume) {
        this.volume = volume;
    }

    String getColor() {
        return color;
    }

    String getLiquid() {
        return liquid;
    }

    double getVolume() {
        return volume;
    }
}
