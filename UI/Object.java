package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Object {
   protected float x;
   protected float y;
   protected float w;
   protected float h;
   protected float angle;
   protected Color color;
   protected ID id;
   protected int layer;
   protected boolean mouseOver = false;
   protected boolean display = true;

   public Object(float x, float y, Color color, ID id, int layer) {
      this.x = x;
      this.y = y;
      this.w = 0.0F;
      this.h = 0.0F;
      this.angle = 0.0F;
      this.color = color;
      this.id = id;
      this.layer = layer;
   }

   public Object(float x, float y, float w, float h, Color color, ID id, int layer) {
      this.x = x;
      this.y = y;
      this.w = w;
      this.h = h;
      this.angle = 0.0F;
      this.color = color;
      this.id = id;
      this.layer = layer;
   }

   public abstract Rectangle getBounds();

   public boolean mouseOver(float mx, float my) {
      if (this.w != 0.0F && this.h != 0.0F) {
         if (mx >= this.x && mx <= this.x + this.w) {
            if (my >= this.y && my <= this.y + this.h) {
               this.isMouseOver(true);
               return true;
            } else {
               this.isMouseOver(false);
               return false;
            }
         } else {
            this.isMouseOver(false);
            return false;
         }
      } else {
         this.isMouseOver(false);
         return false;
      }
   }

   public boolean mouseOver(float x, float y, float w, float h, float mx, float my) {
      if (w != 0.0F && h != 0.0F) {
         if (mx >= x && mx <= x + w) {
            if (my >= y && my <= y + h) {
               this.isMouseOver(true);
               return true;
            } else {
               this.isMouseOver(false);
               return false;
            }
         } else {
            this.isMouseOver(false);
            return false;
         }
      } else {
         this.isMouseOver(false);
         return false;
      }
   }

   public abstract void tick();

   public abstract void render(Graphics var1);

   public void setX(float x) {
      this.x = x;
   }

   public void setY(float y) {
      this.y = y;
   }

   public void setW(float w) {
      this.w = w;
   }

   public void setH(float h) {
      this.h = h;
   }

   public void setAngle(float angle) {
      this.angle = angle;
   }

   public void setColor(Color color) {
      this.color = color;
   }

   public void setLayer(int layer) {
      this.layer = layer;
   }

   public float getX() {
      return this.x;
   }

   public float getY() {
      return this.y;
   }

   public float getW() {
      return this.w;
   }

   public float getH() {
      return this.h;
   }

   public float getAnlge() {
      return this.angle;
   }

   public Color getColor() {
      return this.color;
   }

   public ID getId() {
      return this.id;
   }

   public int getLayer() {
      return this.layer;
   }

   public void isMouseOver(boolean mouseOver) {
      this.mouseOver = mouseOver;
   }

   public boolean isMouseOver() {
      return this.mouseOver;
   }

   public void isDisplay(boolean display) {
      this.display = display;
   }

   public boolean isDisplay() {
      return this.display;
   }
}
