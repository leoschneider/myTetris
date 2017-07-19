package fr.schneider;

import fr.schneider.core.Board;
import fr.schneider.interfacer.ElTableau;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Board board = new Board(10,20);
        ElTableau elTableau = new ElTableau(board);
        elTableau.run();
        board.start(elTableau.getTableau());
    }
}
