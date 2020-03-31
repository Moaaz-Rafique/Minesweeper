package minesweeper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class Menu  extends MouseAdapter{

    private Minesweeper game;
    private Handler handler;

    public Menu(Minesweeper game, Handler handler) {
        this.game = game;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        
        if (game.gameState == Minesweeper.STATE.Menu) {
            //play button
            if (mouseOver(mx, my, 360, 100, 200, 64)) {
                this.handler.newGame();
                handler.over = false;                
                game.gameState = Minesweeper.STATE.Game;
                
            }
            //help button
            if (mouseOver(mx, my, 360, 250, 200, 64)) {
                this.game.gameState = Minesweeper.STATE.Help;
            }

            //quit button
            if (mouseOver(mx, my, 360, 276, 200, 64)) {
                System.exit(1);
            }
        }
        
    }

    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            }
        }
        return false;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        Font fnt = new Font("arial", 1, 50);
        Font fnt2 = new Font("arial", 1, 30);
        if(handler.over)
            game.gameState = Minesweeper.STATE.Menu;
        if (game.gameState == Minesweeper.STATE.Menu) {
            g.setFont(fnt);
            g.drawString("Menu", 240, 70);

            g.setFont(fnt2);
            g.drawRect(360, 100, 200, 64);
            g.drawString("Play Again", 390, 140);

            g.drawRect(360, 276, 200, 64);
            g.drawString("Quit", 430, 316);
        }
    }
}
