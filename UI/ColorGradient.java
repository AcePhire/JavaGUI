package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class ColorGradient extends Object {
   private Color color2;
   private boolean flipped = false;
   private boolean rotated = false;

   public ColorGradient(float x, float y, float w, float h, Color color1, Color color2, int layer) {
      super(x, y, w, h, color1, ID.ColorGradient, layer);
      this.color2 = color2;
   }

   public Rectangle getBounds() {
      return null;
   }

   public void tick() {
   }

   public void render(Graphics g) {
      if (this.display) {
         Graphics2D g2d = (Graphics2D)g.create();
         int i;
         float range;
         if (!this.flipped) {
            for(i = 0; (float)i < this.h; ++i) {
               range = (float)i / this.h;
               g2d.setColor(Utilities.colorInterpolation(this.color, this.color2, range));
               if (!this.rotated) {
                  g2d.fillRect((int)this.x, (int)this.y + i, (int)this.w, 1);
               } else {
                  g2d.fillRect((int)this.x + i, (int)this.y, 1, (int)this.w);
               }
            }
         } else {
            for(i = (int)this.h; i > 0; --i) {
               range = (float)i / this.h;
               g2d.setColor(Utilities.colorInterpolation(this.color, this.color2, range));
               if (!this.rotated) {
                  g2d.fillRect((int)this.x, (int)(this.y + (this.h - (float)i)), (int)this.w, 1);
               } else {
                  g2d.fillRect((int)(this.x + (this.h - (float)i)), (int)this.y, 1, (int)this.w);
               }
            }
         }

      }
   }

   public void flip() {
      this.flipped = !this.flipped;
   }

   public void rotate() {
      float temp = this.w;
      this.w = this.h;
      this.h = temp;
      this.rotated = !this.rotated;
   }
}
