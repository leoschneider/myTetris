package fr.schneider.interfacer;

import fr.schneider.core.Board;
import fr.schneider.core.Tetromino;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Leo on 18/06/2017.
 */
public class MyKeylistener implements KeyListener {

    private Board board;

    public MyKeylistener(Board board) {
        this.board = board;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_UP){
            board.tempTetroRotate();
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            board.moveLeft();
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            board.moveRight();
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            //    System.out.println("fall");
            //    board.moveDown();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
