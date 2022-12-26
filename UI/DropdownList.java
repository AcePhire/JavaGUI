package UI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.LinkedList;

public class DropdownList extends Object {
   private LinkedList<Item> list = new LinkedList();
   private String[] textList;
   private float listHeight = 0.0F;
   private float listY;
   private int listStartingItem = 0;
   private int visibleItems = 0;
   private String selectedText;
   private int textSize = 15;
   private String fontType = "arial";
   private boolean opened = false;
   private int borderSize = 2;
   private int arcW = 0;
   private int arcH = 0;
   FontMetrics fm;
   private Color insideColor;

   public DropdownList(String[] list, float x, float y, float w, float h, Color borderColor, Color insideColor, int layer) {
      super(x, y, w, h, borderColor, ID.DropdownList, layer);
      this.textList = list;
      this.insideColor = insideColor;
   }

   public Rectangle getBounds() {
      return null;
   }

   public void tick() {
   }

   public void render(Graphics g) {
      Graphics2D g2d = (Graphics2D)g.create();
      BasicStroke stoke = new BasicStroke((float)this.borderSize);
      Font fnt = new Font(this.fontType, 1, this.textSize);
      g2d.setFont(fnt);
      this.fm = g2d.getFontMetrics(fnt);
      this.listY = this.y + stoke.getLineWidth() * 2.0F + this.h;
      int offSet = 5;
      int x1;
      if (this.textList != null) {
         for(x1 = 0; x1 < this.textList.length; ++x1) {
            Item item = new Item(this.textList[x1], fnt, this.x, this.listY + (float)offSet + (float)((this.fm.getHeight() + this.fm.getDescent()) * x1), this.w, (float)(this.fm.getHeight() + this.fm.getDescent()), this.insideColor, this.color, this.layer);
            item.isDisplay(false);
            this.list.add(item);
         }

         this.textList = null;
      }

      g2d.setColor(this.color);
      g2d.setStroke(stoke);
      x1 = (int)((double)this.x + (double)this.w * 0.8D);
      int x2 = (int)((double)this.x + (double)this.w * 0.9D);
      int x3 = (int)((double)this.x + (double)this.w * 0.85D);
      int y12 = (int)((double)(this.y + this.h / 2.0F) - (double)this.h * 0.05D);
      int y3 = (int)((double)(this.y + this.h / 2.0F) + (double)this.h * 0.05D);
      if (!this.opened) {
         g2d.fillPolygon(new int[]{x1, x2, x3}, new int[]{y12, y12, y3}, 3);
      } else {
         g2d.fillPolygon(new int[]{x1, x2, x3}, new int[]{y3, y3, y12}, 3);
         if (this.listHeight == 0.0F) {
            this.listHeight = (float)((this.fm.getHeight() + this.fm.getDescent()) * this.list.size()) + (float)offSet * 1.8F;
         }

         g2d.setColor(this.insideColor);
         g2d.fillRoundRect((int)this.x, (int)this.listY, (int)this.w, (int)this.listHeight, this.arcW, this.arcH);
         if (this.listHeight != 0.0F) {
            float itemsHeight = 0.0F;

            int i;
            for(i = this.listStartingItem; i < this.list.size() && itemsHeight < this.listHeight - (float)(this.fm.getHeight() + this.fm.getDescent()); ++i) {
               Item item = (Item)this.list.get(i);
               item.isDisplay(true);
               item.render(g);
               itemsHeight += item.getH();
            }

            if (this.visibleItems == 0) {
               this.visibleItems = i - this.listStartingItem;
            }
         }

         g2d.setColor(this.color);
         g2d.drawRoundRect((int)this.x, (int)this.listY, (int)this.w, (int)this.listHeight, this.arcW, this.arcH);
      }

      if (this.selectedText != null) {
         g2d.setColor(this.color);
         g2d.drawString(this.selectedText, (int)(this.x + this.w * 0.05F), (int)(this.y + this.h / 2.0F - (float)(this.fm.getHeight() / 2) + (float)this.fm.getAscent()));
      }

      g2d.drawRoundRect((int)this.x, (int)this.y, (int)this.w, (int)this.h, this.arcW, this.arcH);
   }

   public void setBorderSize(int borderSize) {
      this.borderSize = borderSize;
   }

   public void setArcs(int arcW, int arcH) {
      this.arcW = arcW;
      this.arcH = arcH;
   }

   public void setOpened(boolean opened) {
      this.opened = opened;
   }

   public boolean getOpened() {
      return this.opened;
   }

   public LinkedList<Item> getList() {
      return this.list;
   }

   public void setTextSize(int textSize) {
      this.textSize = textSize;
   }

   public void setFontType(String fontType) {
      this.fontType = fontType;
   }

   public void selectItem(Item item) {
      this.selectedText = Utilities.limitString(item.getText(), this.fm, this.w * 0.6F);
      this.setOpened(false);
   }

   public void setListHeight(float height) {
      this.listHeight = height;
   }

   public float[] getListTrans() {
      return new float[]{this.x, this.listY, this.w, this.listHeight};
   }

   public void scrollDown() {
      if (this.listStartingItem < this.list.size() - 1 - this.visibleItems) {
         ++this.listStartingItem;
         Iterator var1 = this.list.iterator();

         while(var1.hasNext()) {
            Item item = (Item)var1.next();
            item.isDisplay(false);
            item.setY(item.getY() - item.getH());
         }
      }

   }

   public void scrollUp() {
      if (this.listStartingItem > 0) {
         --this.listStartingItem;
         Iterator var1 = this.list.iterator();

         while(var1.hasNext()) {
            Item item = (Item)var1.next();
            item.isDisplay(false);
            item.setY(item.getY() + item.getH());
         }
      }

   }
}
