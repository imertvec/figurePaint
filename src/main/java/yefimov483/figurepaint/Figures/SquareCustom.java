package yefimov483.figurepaint.Figures;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SquareCustom extends Figure{
    static public int local_id = 0;

    public SquareCustom(double side, Color color, Canvas canvas){
        this.color = color;
        this.param = side;
        this.canvas = canvas;
        this.posX = (canvas.getWidth() / 2);
        this.posY = (canvas.getHeight() / 2);
        local_id++;
        this.name = "squarecustom" + local_id;
    }

    public void doDrawing(){
        GraphicsContext g = canvas.getGraphicsContext2D();
        if(selected) g.setStroke(selColor);
        else g.setStroke(color);
        g.setLineWidth(3);
        g.strokeRect(posX - param*0.5, posY - param*0.5, param, param);
    }

    public boolean isSelected(double curX, double curY){
        if(curX >= posX - param*0.5 && curX <= posX + param*0.5 && curY >= posY - param*0.5 && curY <= posY + param*0.5) return true;
        return false;
    }
}
