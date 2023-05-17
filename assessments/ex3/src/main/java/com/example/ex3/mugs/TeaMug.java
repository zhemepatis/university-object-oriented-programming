package com.example.ex3.mugs;

public class TeaMug extends LiquidContainer {
    private String print;

    public TeaMug(String color, String print) {
        super(color, "tea", 0.25);
        setPrint(print);
    }

    void setPrint(String print) {
        this.print = print;
    }

    public String getDetails() {
        return "This is " + color + " mug with " + liquid + " in it. The print on the mug says '" + print + "'.";
    }
}
