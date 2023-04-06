package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Item extends Object {
   private String text;
   private Font fnt;
   private Color textColor;
   private String maxStr = "";
   public Color darkerColor;

   public Item(String text, Font fnt, float x, float y, float w, float h, Color color, Color textColor, int layer) {
      super(x, y, w, h, color, ID.Item, layer);
      this.text = text;
      this.fnt = fnt;
      this.darkerColor = color.darker();
      this.textColor = textColor;
   }

   public Rectangle getBounds() {
      return null;
   }

   public void tick() {
   }

   public void render(Graphics g) {
      if (this.display) {
         g.setFont(this.fnt);
         FontMetrics fm = g.getFontMetrics(this.fnt);
         if (this.mouseOver) {
            g.setColor(this.darkerColor);
         } else {
            g.setColor(this.color);
         }

         if (this.maxStr == "") {
            this.maxStr = Utilities.limitString(this.text, fm, this.w - 20.0F);
            this.text = this.maxStr;
         }

         g.fillRect((int)this.x, (int)this.y, (int)this.w, (int)this.h);
         g.setColor(this.textColor);
         g.drawString(this.text, (int)(this.x + this.w / 2.0F - (float)(fm.stringWidth(this.text) / 2)), (int)(this.y + this.h / 2.0F - (float)(fm.getHeight() / 2) + (float)fm.getAscent()));
      }
   }

   public String getText() {
      return this.text;
   }
}
