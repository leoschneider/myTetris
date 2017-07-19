package fr.schneider.interfacer;

import fr.schneider.core.Board;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Leo on 16/06/2017.
 */
public class ElTableau implements Runnable{

    private JFrame frame;
    private Tableau tableau;
    private Board board;

    public ElTableau(Board board) {
        this.board = board;
    }

    @Override
    public void run() {
        //board = new Board(10,20);
        frame = new JFrame("El Tetris");
        frame.setPreferredSize(new Dimension(board.getWidth()*20,board.getHeight()*21));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);

    }

    private void createComponents(Container content) {
        tableau = new Tableau(board);
        content.add(tableau);

        frame.addKeyListener(new MyKeylistener(board));
    }

    public Updatable getUpdatable(){
        return tableau;
    }

    public Tableau getTableau() {
        return tableau;
    }
}
