package fr.schneider.core;

import java.awt.*;

/**
 * Created by Leo on 05/06/2017.
 */
public class Brique {


    private int x;
    private int y;
    private Color color;

    public Brique(int x, int y,Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setYPlusUn() {
        this.y += 1;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setXDiff(int XDiff) {
        this.x += XDiff;
    }
}
