package Modelo;
/*
 * @author danteeh
 */
import java.awt.Color;

public class Block {
    private Color color;
    private int x, y;
    
    public Block(Color color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }
    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
}
