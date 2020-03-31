package minesweeper;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseSelections extends MouseAdapter{
    
    private Handler handler;
    public MouseSelections(Handler handler){
        this.handler = handler;
    }
    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();
        
        if(mx>100 && mx<340 && my>100 && my<340){
            handler.open((mx-100)/24,(my-100)/24);
        }
       
    }
}
