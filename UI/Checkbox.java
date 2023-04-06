package UI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Checkbox extends Object {
   private boolean checked = false;
   private int borderSize = 2;
   private int arcW;
   private int arcH = 0;
   private Color tickColor;

   public Checkbox(float x, float y, float w, float h, Color borderColor, Color tickColor, int layer) {
      super(x, y, w, h, borderColor, ID.Checkbox, layer);
      this.tickColor = tickColor;
   }

   public Rectangle getBounds() {
      return null;
   }

   public void tick() {
   }

   public void render(Graphics g) {
      if (this.display) {
         Graphics2D g2d = (Graphics2D)g.create();
         g2d.setStroke(new BasicStroke((float)this.borderSize));
         if (this.checked) {
            int p1x = (int)((double)this.x + 0.125D * (double)this.w);
            int p1y = (int)((double)this.y + 0.6D * (double)this.h);
            int p2x = (int)((double)this.x + 0.325D * (double)this.w);
            int p2y = (int)((double)this.y + 0.875D * (double)this.h);
            int p3x = (int)((double)this.x + 0.875D * (double)this.w);
            int p3y = (int)((double)this.y + 0.225D * (double)this.h);
            g2d.setColor(this.color);
            g2d.fillRoundRect((int)this.x, (int)this.y, (int)this.w, (int)this.h, this.arcW, this.arcH);
            g2d.setColor(this.tickColor);
            g2d.drawPolyline(new int[]{p1x, p2x, p3x}, new int[]{p1y, p2y, p3y}, 3);
         } else {
            g2d.setColor(this.color);
            g2d.drawRoundRect((int)this.x, (int)this.y, (int)this.w, (int)this.h, this.arcW, this.arcH);
         }

         if (this.mouseOver) {
            g.setColor(Utilities.addAlpha(this.color, 64));
            g.fillOval((int)(this.x - this.w / 2.0F), (int)(this.y - this.h / 2.0F), (int)this.w * 2, (int)this.h * 2);
         }

      }
   }

   public void setBorderSize(int borderSize) {
      this.borderSize = borderSize;
   }

   public void setArcs(int arcW, int arcH) {
      this.arcW = arcW;
      this.arcH = arcH;
   }

   public void isChecked(boolean checked) {
      this.checked = checked;
   }

   public boolean isChecked() {
      return this.checked;
   }
}
