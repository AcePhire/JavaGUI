package UI;

import java.awt.*;
import java.awt.Rectangle;

public class Text extends Object {
   private String text;
   private int textSize;
   private String fontType = "arial";

   public Text(String text, int textSize, float x, float y, Color color, int layer) {
      super(x, y, color, ID.Text, layer);
      this.text = text;
      this.textSize = textSize;
   }

   public void tick() {
   }

   public void render(Graphics g) {
      if (this.display) {
         Font fnt = new Font(this.fontType, 15, this.textSize);
         g.setFont(fnt);
         FontMetrics fm = g.getFontMetrics(fnt);
         g.setColor(this.color);
         g.drawString(this.text, (int)(this.x - (float)(fm.stringWidth(this.text) / 2)), (int)(this.y - (float)(fm.getHeight() / 2) + (float)fm.getAscent()));
      }
   }

   public Rectangle getBounds() {
      return null;
   }

   public void setFontType(String fontType) {
      this.fontType = fontType;
   }

   public String getText() {
      return this.text;
   }

   public void setText(String text) {
      this.text = text;
   }

   public float getW(){
      Font fnt = new Font(this.fontType, 15, this.textSize);
      Canvas c = new Canvas();
      FontMetrics fm = c.getFontMetrics(fnt);
      return fm.stringWidth(text);
   }
}
