package fr.schneider.interfacer;

import fr.schneider.core.Board;
import fr.schneider.core.Brique;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

/**
 * Created by Leo on 16/06/2017.
 */
public class Tableau extends JPanel implements Updatable {

private Board board;

    public Tableau(Board board){
       super.setBackground(Color.white);
        this.board = board;
        this.setPreferredSize(new Dimension(board.getWidth()*20,board.getHeight()*20));

    }

    public void paint(Graphics g){;

        Graphics2D g2 = (Graphics2D) g;


        g.setColor(Color.GRAY);
        g.fillRect(0,0,board.getWidth(),board.getHeight());
        //g.drawLine(0,0,0,board.getHeight()*15);
        //g.drawLine(0,board.getHeight()*15,board.getWidth()*15,board.getHeight()*15);
        //g.setColor(Color.BLUE);

        Stroke dashed = new BasicStroke(0, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0);

        for(int i=0;i<board.getWidth();i++){
            Line2D lin = new Line2D.Float(i*20, 0, i*20, board.getHeight()*20);
            g2.setStroke(dashed);
            g2.draw(lin);
        }

        for(int j=0;j<board.getHeight();j++){
            Line2D lin = new Line2D.Float(0, j*20, board.getWidth()*20,j*20 );
            g2.setStroke(dashed);
            g2.draw(lin);
        }

        for(Brique brique: board.getBriquesAndTemp()){
            g.setColor(brique.getColor());
            g.fill3DRect(brique.getX()*20,brique.getY()*20,20,20,true);
        }
    }

    @Override
    public void update() {
        repaint();
    }
}
