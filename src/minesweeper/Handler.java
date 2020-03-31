package minesweeper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Handler {

    private int heightOfMine = 10;
    private int widthOfMine = 10;
    private int bombs = 10;

    MinesweeperBackend mines;
    private int[][] generatedMines;
    private boolean[][] open;

    public boolean over;
    
    private int remainingCells;
    public void tick() {
        
    }

    public void open(int i, int j) {
        if(!(this.open[i][j] || this.over))
        {
            this.open[i][j] = true;
            System.out.println(this.remainingCells--);
            if (this.generatedMines[i][j] == 0) {
                for (int x = -1; x < 2; x++) {
                    for (int y = -1; y < 2; y++) {
                        if (!(x == 0 && y == 0)) {
                            if ((i + x) >= 0 && (i + x) <= (mines.maxX - 1)) {
                                if ((j + y) >= 0 && (j + y) <= (mines.maxY - 1)) {
                                    open(i + x, j + y);
                                }                                
                            }
                        }
                    }
                }
                return;
            }
        }
    }

    public void render(Graphics g) {
        if(this.remainingCells <= this.bombs && !this.over){
            System.out.println(this.remainingCells);
            this.over = true;
        }
        for (int i = 0; i < mines.maxX; i++) {
            for (int j = 0; j < mines.maxY; j++) {
                if (open[i][j]) {
                    if (generatedMines[i][j] <= 9) {
                        g.setColor(Color.gray);
                        g.fillRect(100 + 24 * i, 100 + 24 * j, 23, 23);
                        if (generatedMines[i][j] > 0) {
                            g.setColor(Color.white);
                            Font fnt = new Font("arial", 1, 20);
                            g.setFont(fnt);
                            g.drawString(Integer.toString(generatedMines[i][j]), 100 + 24 * i + 5, 100 + 24 * (j + 1) - 4);
                        }
                    }
                    if (generatedMines[i][j] >= 10) {
                        g.setColor(Color.red);
                        g.fillRect(100 + 24 * i, 100 + 24 * j, 23, 23);
                        over = true;
                    }

                } else {
                    g.setColor(Color.CYAN);
                    g.fillRect(100 + 24 * i, 100 + 24 * j, 23, 23);
                }
            }
        }

    }
    public void newGame(){
        this.mines = new MinesweeperBackend(this.widthOfMine,this.heightOfMine,this.bombs);
        this.generatedMines = mines.getMineInfo();
        open = new boolean[mines.maxX][mines.maxY];
        this.over = false;
        this.remainingCells  = heightOfMine * widthOfMine;
    }
}
