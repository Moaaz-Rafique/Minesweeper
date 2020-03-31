package minesweeper;

import java.util.Random;

public class MinesweeperBackend {
    public int maxX, maxY;
    private int noOfBombs;
    private int[] bombs;
    private int[][] neighbours;
    
    public MinesweeperBackend(int x, int y, int noOfBombs){
        this.maxX = x;
        this.maxY = y;
        this.noOfBombs = noOfBombs;
        
        this.bombs = calcBombs(x*y);
        this.neighbours = calcNeighbours(this.bombs);
        //this.display();
    }
    
    private int[] calcBombs(int cells){
        
        int[] arr = new int[this.noOfBombs];
        for(int i = 0; i < this.noOfBombs; i++){
            Random r = new Random();
            arr[i] = r.nextInt(cells);
        }
        return arr;
    }
    
    private int[][] calcNeighbours(int arr[]){
        
        int[][] res = new int[this.maxX][this.maxY];
        
        for(int i = 0; i < arr.length; i++){
            for(int x=-1;x<2;x++){
                for(int y=-1;y<2;y++){
                    
                    int bombX=arr[i] % this.maxX;
                    int bombY=arr[i] / this.maxY;
                    
                    if(!(x == 0 && y == 0)){
                        if(bombX+x >= 0 && bombX+x <= (maxX-1))
                            if(bombY+y >= 0 && bombY+y <= (maxY-1))
                        res[ x + bombX ][ y + bombY ]++;
                    }
                    else if(x==0 && y==0){
                        res[bombX][bombY]=10;
                    }
                }
            }
                
        }
        
        return res;
    }
    private void display(){
        for(int i = 0; i < this.maxX; i++){
            for(int j = 0; j< this.maxY; j++){
                if(this.neighbours[i][j]>10)this.neighbours[i][j]=10;
                System.out.print(this.neighbours[i][j] + "\t");
            }
            System.out.println("");
        }
    }
    public int[][] getMineInfo(){
        return this.neighbours;
    }
}