package yefimov483.figurepaint.Figures;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TriangleCustom extends Figure{
    public static int local_id = 0;
    public int side;

    public TriangleCustom(double side, Color color, Canvas canvas){
        param = side / 2;
        this.color = color;
        this.canvas = canvas;
        this.name = "trianglecustom" + local_id;
        this.posX = (canvas.getWidth() / 2);
        this.posY = (canvas.getHeight() / 2);
        local_id++;
    }

    public void doDrawing(){
        GraphicsContext g = canvas.getGraphicsContext2D();
        if(selected) g.setStroke(selColor);
        else g.setStroke(color);
        g.setLineWidth(3);
        g.strokePolygon(
                new double[]{posX - param, posX, posX + param},
                new double[]{posY + param, posY - param, posY + param }, 3);
    }

    @Override
    public boolean isSelected(double x, double y) {
        if(x >= posX - param*0.5 && x <= posX + param*0.5 && y >= posY - param*0.5 && y <= posY + param*0.5) return true;
        return false;
    }

}
