package UI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class ActionButton extends Object {
   private String text;
   private int textSize;
   private Color insideColor;
   private String fontType = "arial";
   private int borderSize = 2;
   private int arcW = 0;
   private int arcH = 0;
   private Action action;

   public ActionButton(float x, float y, float w, float h, Color borderColor, Color insideColor, int layer) {
      super(x, y, w, h, borderColor, ID.ActionButton, layer);
      this.insideColor = insideColor;
   }

   public Rectangle getBounds() {
      return null;
   }

   public void tick() {
   }

   public void render(Graphics g) {
      if (this.display) {
         Graphics2D g2d = (Graphics2D)g.create();
         if (this.isMouseOver()) {
            g2d.setColor(this.color);
         } else {
            g2d.setColor(this.insideColor);
         }

         g2d.fillRoundRect((int)this.x, (int)this.y, (int)this.w, (int)this.h, this.arcW, this.arcW);
         if (this.text != null) {
            Font fnt = new Font(this.fontType, 1, this.textSize);
            g2d.setFont(fnt);
            FontMetrics fm = g2d.getFontMetrics(fnt);
            if (this.isMouseOver()) {
               g2d.setColor(this.insideColor);
            } else {
               g2d.setColor(this.color);
            }

            g2d.drawString(this.text, (int)(this.x + this.w / 2.0F - (float)(fm.stringWidth(this.text) / 2)), (int)(this.y + this.h / 2.0F - (float)(fm.getHeight() / 2) + (float)fm.getAscent()));
         }

         if (this.isMouseOver()) {
            g2d.setColor(this.insideColor);
         } else {
            g2d.setColor(this.color);
         }

         g2d.setStroke(new BasicStroke((float)this.borderSize));
         g2d.drawRoundRect((int)this.x, (int)this.y, (int)this.w, (int)this.h, this.arcW, this.arcW);
      }
   }

   public void setText(String text, int textSize) {
      this.text = text;
      this.textSize = textSize;
   }

   public void setFontType(String fontType) {
      this.fontType = fontType;
   }

   public void setBorderSize(int borderSize) {
      this.borderSize = borderSize;
   }

   public void setArcs(int arcW, int arcH) {
      this.arcW = arcW;
      this.arcH = arcH;
   }

   public String getText() {
      return this.text;
   }

   public void switchColors() {
      Color newColor = this.color;
      this.color = this.insideColor;
      this.insideColor = newColor;
   }

   public void setAction(Action action) {
      this.action = action;
   }

   public Action getAction() {
      return this.action;
   }
}
