package yefimov483.figurepaint.Figures;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CircleCustom extends Figure{
    private double halfSqX;
    private double halfSqY;
    static public int local_id = 0;

    public CircleCustom(double radius, Color color, Canvas canvas){
        this.color = color;
        this.param = radius;
        this.canvas = canvas;
        this.posX = (canvas.getWidth() / 2);
        this.posY = (canvas.getHeight() / 2);
        local_id++;
        this.name = "cirlcecustom" + local_id;

    }

    public void doDrawing(){
        GraphicsContext g = canvas.getGraphicsContext2D();
        if(selected) g.setStroke(selColor);
        else g.setStroke(color);
        g.setLineWidth(3);
        g.strokeOval(posX - param*0.5, posY - param*0.5, param, param);
    }

    @Override
    public boolean isSelected(double curX, double curY) {
        halfSqX = ((posX - param*0.5) - curX);
        halfSqY = ((posY - param*0.5) - curY);
        if(halfSqX * halfSqX + halfSqY * halfSqY <= param * param) return true;
        return false;
    }
}
