package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HoverDetails extends Object {
   private final int OFFSET = 10;
   private boolean flipped = false;
   private String text;
   private String fontType = "arial";

   public HoverDetails(String text, float x, float y, int layer) {
      super(x, y, Color.darkGray.darker(), ID.HoverDetails, layer);
      this.text = text;
   }

   public Rectangle getBounds() {
      return null;
   }

   public void tick() {
   }

   public void render(Graphics g) {
      if (this.display) {
         Font fnt = new Font(this.fontType, 1, 15);
         g.setFont(fnt);
         FontMetrics fm = g.getFontMetrics(fnt);
         this.h = (float)(fm.getHeight() + 5);
         this.w = (float)(fm.stringWidth(this.text) + 20);
         g.setColor(this.color);
         int[] px;
         int[] py;
         if (!this.flipped) {
            px = new int[]{(int)(this.x - this.w / 2.0F) + 10, (int)(this.x - this.w / 2.0F + this.w - 10.0F), (int)(this.x - this.w / 2.0F + this.w / 2.0F)};
            py = new int[]{(int)this.y, (int)this.y, (int)(this.y - this.h / 3.0F)};
         } else {
            px = new int[]{(int)(this.x - this.w / 2.0F) + 10, (int)(this.x - this.w / 2.0F + this.w - 10.0F), (int)(this.x - this.w / 2.0F + this.w / 2.0F)};
            py = new int[]{(int)(this.y + this.h), (int)(this.y + this.h), (int)(this.y + this.h + this.h / 3.0F)};
         }

         g.fillPolygon(px, py, 3);
         g.fillRoundRect((int)(this.x - this.w / 2.0F), (int)this.y, (int)this.w, (int)this.h, (int)this.h - 10, (int)this.h - 10);
         g.setColor(Utilities.inverseColor(this.color));
         g.drawString(this.text, (int)(this.x - this.w / 2.0F) + 10, (int)(this.y + this.h / 2.0F - (float)(fm.getHeight() / 2) + (float)fm.getAscent()));
      }
   }

   public void setText(String text) {
      this.text = text;
   }

   public void setFontType(String fontType) {
      this.fontType = fontType;
   }

   public void flip() {
      this.flipped = !this.flipped;
   }
}
