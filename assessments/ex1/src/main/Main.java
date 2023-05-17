package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("IKEA Simulator");

        Playground gameView = new Playground();
        window.add(gameView);
        window.pack(); // sets window size to match with panel's size

        window.setLocationRelativeTo(null); // puts window in the center of the screen
        window.setVisible(true);

        gameView.startGameThread();
    }
}