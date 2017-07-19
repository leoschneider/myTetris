package fr.schneider.core;

import java.util.*;

/**
 * Created by Leo on 05/06/2017.
 */
public class Tetromino {

    private List<Brique> briques;
    private Type type;
    private int rotation;

    public Tetromino() {
        this.type = Type.randomType();
        briques = new ArrayList<>();
        briques.addAll(type.renvBriques());
        rotation = 0;
    }

    public void fall(int minX){
        for (Brique brique:briques){
            if(brique.getX()<=minX){
                brique.setYPlusUn();
            }
        }
    }

    public void fall(){
        for (Brique brique:briques){
                brique.setYPlusUn();
        }
    }

    public List<Brique> getBriques() {
        return briques;
    }

    public int getRotation() {
        return rotation;
    }

    public Type getType() {
        return type;
    }

    public void setRotationPlusOne() {
        this.rotation +=1 ;
    }
}
