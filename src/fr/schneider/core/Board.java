package fr.schneider.core;

import fr.schneider.interfacer.ElTableau;
import fr.schneider.interfacer.Tableau;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Leo on 05/06/2017.
 */
public class Board {

    private final int width;
    private final int height;
    private Tetromino tempTetromino;
    private List<Brique> briques;
    private boolean tetrominoTouched;
    private boolean over;
    private List<Brique> topBriques;
    private Tableau tableau;
    private Brique[][] tableauPositionBriques;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.briques = new ArrayList<>();
        this.topBriques = new ArrayList<>();
        this.over = false;
        this.tetrominoTouched = false;
        this.tempTetromino = new Tetromino();
        this.tableauPositionBriques = new Brique[width][height];
     }

    public boolean addTetromino(){
        tempTetromino = new Tetromino();
        for(Brique brique:tempTetromino.getBriques()){
            if(tableauPositionBriques[brique.getX()][brique.getY()] != null){
                over=true;
                return true;
            }
        }
        return true;

        /*tempTetromino = new Tetromino();
        for(Brique brique:briques){
            for(Brique b:tempTetromino.getBriques()){
                if(b.getX() == brique.getX() && b.getY() == brique.getY()){
                    over = true;
                    return true;
                }
            }
        }
        return true;
        */
    }

    public void start(Tableau tableau){
        this.tableau = tableau;
        addTetromino();
        while(!over) {
            if(tetrominoTouched) {
                checkFullLines();
                addTetromino();
                //defineTopBriques();
                tetrominoTouched = false;
                tableau.update();
            }else{
                tempTetroFall();//.fall();
                tableau.update();
            }
            try{
                Thread.sleep(300);
            }
            catch(Exception e){
            }
        }
    }

    private void checkFullLines() {


        for(int i =0;i<height;i++){
            int count = 0;
            innerloop:
            for(int j=0;j<width;j++){
                if(tableauPositionBriques[j][i] != null){
                    count++;
                }else{
                    break innerloop;
                }
            }
            if (count == width){
                destroyLine(i);
                moveUpperLines(i);
                checkFullLines();
            }
        }

        /*

        int[] counted = new int[height];

        for(int i=0;i<height;i++){
            counted[i]=0;
        }
            for(Brique brique1:briques){
                counted[brique1.getY()]++;
            }

        for(int i=0;i<height;i++){
            if (counted[i] == width+1){
                Iterator<Brique> iter = tempTetromino.getBriques().iterator();
                while(iter.hasNext()){
                    if(iter.next().getY()==i){
                        iter.remove();
                    }
                }
                for(Brique brique :briques){
                    if(brique.getY()<i){
                        brique.setYPlusUn();
                        checkFullLines();
                    }
                }
            }
        }


        tableau.update();
    */
    }

    private void moveUpperLines(int lineIndex) {
        for(int i=0;i<width;i++){
            for(int j=lineIndex;j>-1;j--){
                if(tableauPositionBriques[i][j] != null){
                    tableauPositionBriques[i][j].setYPlusUn();
                    tableauPositionBriques[i][j+1] = tableauPositionBriques[i][j];
                    tableauPositionBriques[i][j] = null;
                }
            }
        }
    }

    private void destroyLine(int lineIndex) {
        for (int i=0;i<width;i++){
            if(tableauPositionBriques[i][lineIndex] != null){
                briques.remove(briques.indexOf(tableauPositionBriques[i][lineIndex]));
                tableauPositionBriques[i][lineIndex] = null;
            }
        }
    }

    public void tempTetroRotate(){
        int r = tempTetromino.getRotation();
        int[] t= tempTetromino.getType().rotationPositions();
        for(int i=0;i<4;i++) {
            if(tempTetromino.getBriques().size()==0){return;}
            int posX = t[((r*8)+i+1+tempTetromino.getBriques().get(i).getX()-t[i%32])%32];
            int posY = t[((r*8)+4+i+1+tempTetromino.getBriques().get(i).getY()-t[(i+4)%32])%32];
            if(tableauPositionBriques[posX][posY] != null ){
                return;
            }


            /*if (! isSpaceFreeAt(
                    tempTetromino.getType().rotationPositions()[((tempTetromino.getRotation() * 8) + i+1)% 32],
                    tempTetromino.getType().rotationPositions()[((tempTetromino.getRotation() * 8) + 4 + i+1 )% 32],
                    i)
                    ) {
                        return;
            }*/
        }
        for(int i=0;i<4;i++) {
            Brique b = tempTetromino.getBriques().get(i);
            //System.out.print("was x: " + b.getX() + " y: " + b.getY() + " ");
            b.setXDiff(-1 * tempTetromino.getType().rotationPositions()[((8*tempTetromino.getRotation()+i)%32)] + tempTetromino.getType().rotationPositions()[((8*tempTetromino.getRotation()+i+8)%32)]); //i
            b.setY(b.getY() - tempTetromino.getType().rotationPositions()[(8*tempTetromino.getRotation()+i+4)%32] + tempTetromino.getType().rotationPositions()[(8*tempTetromino.getRotation()+i+4+8)%32]); //i+4
            //System.out.println("set Position x : " + (b.getX() - tempTetromino.getType().rotationPositions()[i]) + " et y : " + (b.getY() - tempTetromino.getType().rotationPositions()[i + 4]));
            //System.out.println("Index at x : "+((8*tempTetromino.getRotation()+i+8)%32)+" and y : "+(8*tempTetromino.getRotation()+i+4+8)%32);
        }
        //System.out.println("---------------------");
        tempTetromino.setRotationPlusOne();
    }

    private boolean isSpaceFreeAt(int locX,int locY,int index){
        locX += tempTetromino.getBriques().get(index).getX()-tempTetromino.getType().rotationPositions()[index];
        locY += tempTetromino.getBriques().get(index).getY()-tempTetromino.getType().rotationPositions()[(index+4)%32];

        for(Brique brique:briques){
            if (brique.getY() == locY && brique.getX() == locX) {
                return false;
            }
            //System.out.println("Position x : "+locX+" et y : "+locY);
        }
        return true;
    }

    private void tempTetroFall() {
        boolean fall = true;
        for(Brique brique: tempTetromino.getBriques()){
            if(brique.getY()>height-2){
                fall=false;
                setTetrominoTouched(true);
                break;
            }
            if(brique.getX()<width && brique.getY()<height-1){
               if(tableauPositionBriques[brique.getX()][(brique.getY()+1)%height] != null){
                   fall=false;
                   setTetrominoTouched(true);
                   break;
               }
            }
        }

            /*
            for(Brique topBrique: briques){ //check all briques insteed of top ones
                if( ( brique.getY() == topBrique.getY()-1 && brique.getX() == topBrique.getX() ) || brique.getY()>height-2) {
                    fall = false;
                    setTetrominoTouched(true);
                    break;
                }
            }
        }*/
        if (fall){
            tempTetromino.fall();
        }else{
            for(Brique b : tempTetromino.getBriques()){
                briques.add(b);
                tableauPositionBriques[b.getX()][b.getY()]= b;
            }
            Iterator<Brique> it = tempTetromino.getBriques().iterator();
            while(it.hasNext()){
                it.next();
                it.remove();
            }
        }
    }

    public void setTetrominoTouched(boolean tetrominoTouched) {
        this.tetrominoTouched = tetrominoTouched;
    }

    public void defineTopBriques(){
        topBriques.clear();
        for(Brique brique:briques){
            boolean top = true;
            for(Brique brique1 : briques){
                if( brique.getX() == brique1.getX()+1 && brique.getY() == brique1.getY() ){
                    top = false;
                }
            }
            if(top){topBriques.add(brique); }
        }
    }

    public void moveRight() {
        if(canMoveRight()){
            for(Brique brique:tempTetromino.getBriques()){
                brique.setXDiff(1);
            }
        }
        tableau.update();
    }

    private boolean canMoveRight() {

        for(Brique b:tempTetromino.getBriques()){
            if(b.getX() == width-1){
                return false;
            }
            if(tableauPositionBriques[b.getX()+1][b.getY()]!= null){
                return false;
            }
        }
        return true;

        /*for(Brique tempBrique : tempTetromino.getBriques()){
            if(tempBrique.getX() == width){
                return false;
            }
            for(Brique brique: briques){ //check all briques insteed of top ones
                if( tempBrique.getY() == brique.getY() && tempBrique.getX() == brique.getX()-1 ) {
                    return false;
                }
            }
        }
        return true;
        */
    }

    public void moveLeft() {
        if(canMoveLeft()){
            for(Brique brique:tempTetromino.getBriques()){
                brique.setXDiff(-1);
            }
        }
        tableau.update();
    }

    private boolean canMoveLeft() {
        for(Brique b:tempTetromino.getBriques()){
            if(b.getX() == 0){
                return false;
            }
            if(tableauPositionBriques[b.getX()-1][b.getY()]!= null){
                return false;
            }
        }
        return true;
        /*
        for(Brique tempBrique : tempTetromino.getBriques()){
            if(tempBrique.getX() == 0){
                return false;
            }
            for(Brique brique: briques){ //check all briques insteed of top ones
                if(tempBrique.getY() == brique.getY() && tempBrique.getX()-1 == brique.getX()) {
                    return false;
                }
            }
        }
        return true;
    */
    }

    private boolean canMoveDown() {
        //if (tempTetromino.getBriques().get(0).getY()>height){
        //    return false;
        //}

        for(Brique b:tempTetromino.getBriques()){
            if(b.getY() == height){
                return false;
            }
            if(tableauPositionBriques[b.getX()][b.getY()+1]!= null){
                return false;
            }
        }
        return true;

        /*
        for(Brique brique: tempTetromino.getBriques()){
            for(Brique topBrique: briques){ //check all briques instead of top ones
                if( ( brique.getY() == topBrique.getY()-1 && brique.getX() == topBrique.getX() ) || brique.getY()>height-1) {
                    return false;
                }
            }
        }
        return true;*/
    }

    public void moveDown() {

        if(canMoveDown()){
            tempTetromino.fall();
        }
    }

    public List<Brique> getBriquesAndTemp() {
        List<Brique> briques2 = new ArrayList<>();
        briques2.addAll(briques);
        for(Brique b : tempTetromino.getBriques()){
            briques2.add(b);
        }
        return briques2;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Tetromino getTempTetromino() {
        return tempTetromino;
    }


}
