package UI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class ToggleButton extends Object {
   private boolean toggle = false;
   private float toggleX;
   private Color onColor;
   private int borderSize = 2;
   private int arcW = 0;
   private int arcH = 0;

   public ToggleButton(float x, float y, float w, float h, Color color, int layer) {
      super(x, y, w, h, color, ID.ToggleButton, layer);
      this.toggleX = x;
      this.onColor = color.darker();
   }

   public Rectangle getBounds() {
      return null;
   }

   public void tick() {
      if (this.toggle) {
         this.toggleX += 15.0F;
      } else {
         this.toggleX -= 15.0F;
      }

      this.toggleX = Utilities.clamp(this.toggleX, this.x, this.x + this.w - this.h);
   }

   public void render(Graphics g) {
      if (this.display) {
         Graphics2D g2d = (Graphics2D)g.create();
         g2d.setStroke(new BasicStroke((float)this.borderSize));
         float range = (this.toggleX - this.x) / (this.w - this.h);
         g2d.setColor(Utilities.colorInterpolation(this.color, this.onColor, range));
         g2d.fillRoundRect((int)this.toggleX + 5, (int)this.y + 5, (int)this.h - 10, (int)this.h - 10, this.arcW, this.arcH);
         g2d.drawRoundRect((int)this.x, (int)this.y, (int)this.w, (int)this.h, this.arcW, this.arcH);
      }
   }

   public void setOnColor(Color color) {
      this.onColor = color;
   }

   public void setBorderSize(int borderSize) {
      this.borderSize = borderSize;
   }

   public void setArcs(int arcW, int arcH) {
      this.arcW = arcW;
      this.arcH = arcH;
   }

   public void isToggle(boolean toggle) {
      this.toggle = toggle;
   }

   public boolean isToggle() {
      return this.toggle;
   }
}
