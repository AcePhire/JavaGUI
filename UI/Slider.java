package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Slider extends Object {
   private Handler handler;
   private boolean selected = false;
   private float valueW = 20.0F;
   private float maxValue;
   private ArrayList<Float> valueX = new ArrayList();
   private HoverDetails details;
   private final int hdLayer = 3;
   private boolean hideHoverNumber = false;

   public Slider(float maxValue, float x, float y, float w, Color color, int layer, Handler handler) {
      super(x, y, color, ID.Slider, layer);
      this.handler = handler;
      this.w = w;
      this.h = 5.0F;
      this.maxValue = maxValue;
      this.valueX.add(0.0F);
      float[] trans = this.getTrans();
      String value = String.format("%.2f", this.getValue());
      this.details = new HoverDetails(value, trans[0] + trans[2] / 2.0F, trans[1] - trans[2] - 12.5F, 3);
      this.details.flip();
   }

   public Rectangle getBounds() {
      return null;
   }

   public void tick() {
      float[] mPos = Utilities.getMousePos(this.handler.getFrame());
      float mx = mPos[0];
      float my = mPos[1];
      float[] trans = this.getTrans();
      String value = String.format("%.2f", this.getValue());
      boolean mouseOver = this.mouseOver(trans[0], trans[1], trans[2], trans[2], mx, my);
      if (mouseOver && !this.hideHoverNumber) {
         this.details.isDisplay(true);
         if (this.details.getY() <= 10.0F) {
            this.details.flip();
            this.details.setY(trans[1] + trans[2] + 12.5F);
         }

         this.details.setX(trans[0] + trans[2] / 2.0F);
         this.details.setText(value);
      } else {
         this.details.isDisplay(false);
      }

   }

   public void render(Graphics g) {
      if (this.display) {
         g.setColor(new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), 128));
         g.fillRoundRect((int)this.x, (int)(this.y - this.h / 2.0F), (int)this.w, (int)this.h, 5, 5);
         g.setColor(this.color);
         g.fillOval((int)(this.x - this.valueW / 2.0F + (Float)this.valueX.get(0)), (int)(this.y - this.valueW / 2.0F), (int)this.valueW, (int)this.valueW);
         if (this.mouseOver) {
            g.setColor(Utilities.addAlpha(this.color, 64));
            g.fillOval((int)(this.x - this.valueW + (Float)this.valueX.get(0)), (int)(this.y - this.valueW), (int)this.valueW * 2, (int)this.valueW * 2);
            if (!this.hideHoverNumber) {
               this.details.render(g);
            }
         }

      }
   }

   public float[] getTrans() {
      float w1 = this.valueW;
      float x1 = this.x - this.valueW / 2.0F + (Float)this.valueX.get(0);
      float y1 = this.y - this.valueW / 2.0F;
      return new float[]{x1, y1, w1};
   }

   public float getValue() {
      float value = (Float)this.valueX.get(0) / this.w * this.maxValue;
      return value;
   }

   public void setValueX(float valueX) {
      this.valueX.set(0, valueX);
   }

   public void isSelected(boolean selected) {
      this.selected = selected;
   }

   public boolean isSelected() {
      return this.selected;
   }

   public void hideHoverNumber() {
      this.hideHoverNumber = true;
   }
}
