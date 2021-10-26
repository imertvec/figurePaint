package yefimov483.figurepaint.Figures;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

abstract public class Figure extends Canvas {
    public Color color;
    public Color selColor = Color.RED;
    public Canvas canvas;
    public double posX;
    public double posY;
    public boolean selected = false;
    public String name;
    public double param;

    public abstract void doDrawing();

    public abstract boolean isSelected(double x, double y);
}
