package fr.schneider.core;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Leo on 05/06/2017.
 */
public enum Type implements Rotation{
    CUBE(new int[]{0,1,0,1},new int[]{0,0,1,1},Color.YELLOW) {
        @Override
        public int[] rotationPositions() {
            return new int[]{0,1,0,1, 0,0,1,1, 0,1,0,1, 0,0,1,1, 0,1,0,1, 0,0,1,1, 0,1,0,1, 0,0,1,1};
        }
    },
    BARRE(new int[]{0,1,2,3},new int[]{0,0,0,0},Color.CYAN) {
        @Override
        public int[] rotationPositions() {
            return new int[]{0,1,2,3,0, 0,0,0,0, 0,0,0,0, 1,2,3, 0,1,2,3,0, 0,0,0,0, 0,0,0,0, 1,2,3};
        }
    },
    GRANDL(new int[]{0,0,0,1},new int[]{0,1,2,2},Color.BLUE) {
        @Override
        public int[] rotationPositions() {
            return new int[]{0,0,0,1, 0,1,2,2, 0,1,2,2, 1,1,1,0, 0,1,1,1, 0,0,1,2, 0,0,1,2, 1,0,0,0};
        }
    },
    TRIANGLE(new int[]{0,1,2,1},new int[]{0,0,0,1},Color.MAGENTA) {
        @Override
        public int[] rotationPositions() {
            return new int[]{0,1,2,1, 0,0,0,1, 0,0,0,1, 0,1,2,1, 0,1,2,1, 1,1,1,0, 0,1,1,1, 1,0,1,2};
        }
    },
    RETOURNEL(new int[]{0,1,2,2},new int[]{0,0,0,1},Color.PINK) {//0122 //1110
        @Override
        public int[] rotationPositions() {
            return new int[]{0,1,2,2, 0,0,0,1, 0,1,1,1, 2,2,1,0, 0,0,0,1, 2,1,0,0, 0,0,1,2, 0,1,1,1};
        }
    },
    SERPENTDROITE(new int[]{1,2,0,1},new int[]{0,0,1,1},Color.GREEN) {
        @Override
        public int[] rotationPositions() {
            return new int[]{1,2,0,1, 0,0,1,1, 0,0,1,1, 0,1,1,2, 1,2,0,1, 0,0,1,1, 0,0,1,1, 0,1,1,2};
        }
    },
    SERPENTGAUCHE(new int[]{0,1,1,2},new int[]{0,0,1,1},Color.RED) {
        @Override
        public int[] rotationPositions() {
            return new int[]{0,1,1,2, 0,0,1,1, 1,1,0,0, 0,1,1,2, 0,1,1,2, 0,0,1,1, 1,1,0,0, 0,1,1,2};
        }
    };

    private int[] relativeLocationsX;
    private int[] relativeLocationsY;

    private Color color;

    Type(int[] relativelocationsX, int[] relativelocationsY, Color color) {
        this.relativeLocationsX = relativelocationsX;
        this.relativeLocationsY = relativelocationsY;
        this.color = color;
    }

    private static final List<Type> allValues = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = allValues.size();
    private static final Random random= new Random();

    public static Type randomType()  {
        return allValues.get(random.nextInt(SIZE));
    }

    public List<Brique> renvBriques(){
        List<Brique> toReturn = new ArrayList<>();
        for (int i=0;i<4;i++){
            toReturn.add(new Brique(relativeLocationsX[i],relativeLocationsY[i],color));
        }
        return toReturn;
    }

}
