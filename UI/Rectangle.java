package UI;

import java.awt.*;

public class Rectangle extends Object{

    private int arcW = 0;
    private int arcH = 0;

    public Rectangle(float x, float y, float w, float h, Color color, int layer){
        super(x, y, w, h, color, ID.Rectangle, layer);
    }

    public java.awt.Rectangle getBounds() {
        return null;
    }

    public void tick() {}

    public void render(Graphics g) {
        if (this.display){
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(color);
            g2d.fillRoundRect((int)this.x, (int)this.y, (int)this.w, (int)this.h, this.arcW, this.arcH);
        }
    }

    public void setArcs(int arcW, int arcH) {
        this.arcW = arcW;
        this.arcH = arcH;
    }
}
