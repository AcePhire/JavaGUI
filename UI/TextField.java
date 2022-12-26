package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.LinkedList;

public class TextField extends Object {
   private boolean selected = false;
   private boolean maxLetters = false;
   private boolean maxLines = false;
   private boolean flashOn = false;
   private String defualtText;
   private int textSize = 15;
   private Color textColor;
   private String fontType = "arial";
   private int tickTimer;
   private int tickTimerInverse = 0;
   private final int OFFSET = 10;
   private LinkedList<String> lines = new LinkedList();
   private FontMetrics fm;

   private boolean isPassword;

   public TextField(float x, float y, float w, Color color, int layer) {
      super(x, y, color, ID.TextField, layer);
      this.w = w;
      this.textColor = color;
      this.lines.add("");
   }

   public TextField(float x, float y, float w, float h, Color color, int layer) {
      super(x, y, w, h, color, ID.TextField, layer);
      this.maxLines = true;
      this.lines.add("");
   }

   public Rectangle getBounds() {
      return null;
   }

   public void tick() {
      if ((this.lines.getLast()).length() == 0 && this.lines.getLast() != "") {
         this.lines.set(this.lines.size() - 1, "");
      }

      if (this.selected) {
         if (this.flashOn) {
            ++this.tickTimerInverse;
            if (this.tickTimerInverse >= 40) {
               this.flashOn = false;
               this.tickTimerInverse = 0;
            }
         } else {
            ++this.tickTimer;
            if (this.tickTimer >= 50) {
               this.flashOn = true;
               this.tickTimer = 0;
            }
         }
      }

   }

   public void render(Graphics g) {
      if (this.display) {
         Font fnt = new Font(this.fontType, 1, this.textSize);
         g.setFont(fnt);
         this.fm = g.getFontMetrics(fnt);
         g.setColor(Color.lightGray);
         int flashPosX;
         if (this.defualtText != null && this.lines.getFirst() == "") {
            if (!this.maxLines) {
               this.h = (float)this.fm.getHeight();
            }

            g.drawString(this.defualtText, (int)this.x + 10, (int)this.y + this.fm.getHeight());
         } else {
            g.setColor(this.textColor);
            if (!this.maxLines) {
               this.h = (float)(this.fm.getHeight() * this.lines.size());
            } else if ((float)(this.fm.getHeight() * this.lines.size()) > this.h && this.maxLines) {
               this.maxLetters = true;
               this.lines.removeLast();
            }

            if (!isPassword) {
               for (flashPosX = 1; flashPosX < this.lines.size() + 1; ++flashPosX) {
                  g.drawString(this.lines.get(flashPosX - 1), (int) this.x + 10, (int) (this.y + (float) (this.fm.getHeight() * flashPosX)));
               }
            }else{
               LinkedList<String> hiddenLines = new LinkedList<>();
               for (int i = 0; i < lines.size(); i++){
                  String line = lines.get(i);
                  line = line.replaceAll(".", "*");
                  hiddenLines.add(line);
               }
               for (flashPosX = 1; flashPosX < hiddenLines.size() + 1; ++flashPosX) {
                  g.drawString(hiddenLines.get(flashPosX - 1), (int) this.x + 10, (int) (this.y + (float) (this.fm.getHeight() * flashPosX)));
               }
            }
         }

         if (this.selected && !this.flashOn) {
            flashPosX = 0;
            if (this.lines.getLast() != "") {
               flashPosX = this.fm.stringWidth(this.lines.getLast());
            }

            int flashPosY1 = this.fm.getHeight() * (this.lines.size() - 1);
            int flashPosY2 = this.fm.getHeight() * this.lines.size() + this.fm.getDescent();
            g.setColor(Color.black);
            g.drawLine((int)(this.x + 10.0F + (float)flashPosX), (int)(this.y + (float)flashPosY1 + 4.0F), (int)(this.x + 10.0F + (float)flashPosX), (int)(this.y + (float)flashPosY2 - 4.0F));
         }

         g.setColor(this.color);
         g.drawRect((int)this.x, (int)this.y, (int)this.w, (int)this.h + this.fm.getDescent());
      }
   }

   public String getText() {
      String text = "";

      for(int i = 0; i < this.lines.size(); i++)
      {
         String str = lines.get(i);
         text += str;
      }

      return text;
   }

   public void addLetter(char letter) {
      this.addLine();
      if (!this.maxLetters) {
         LinkedList var10000 = this.lines;
         int var10001 = this.lines.indexOf(this.lines.getLast());
         String var10002 = (String)this.lines.getLast();
         var10000.set(var10001, var10002 + letter);
      }

   }

   public void removeLetter() {
      if ((this.lines.getLast()).length() > 1) {
         this.lines.set(this.lines.size() - 1, (this.lines.getLast()).substring(0, ((String)this.lines.getLast()).length() - 1));
         this.maxLetters = false;
      } else if ((this.lines.getLast()).length() == 1) {
         if (this.lines.size() > 1) {
            this.lines.removeLast();
         } else {
            this.lines.set(this.lines.size() - 1, (this.lines.getLast()).substring(0, ((String)this.lines.getLast()).length() - 1));
         }
      }

   }

   public void setFixedText(String defualtText) {
      this.defualtText = defualtText;
   }

   public void isSelected(boolean selected) {
      this.selected = selected;
   }

   public boolean isSelected() {
      return this.selected;
   }

   public void setTextSize(int textSize) {
      this.textSize = textSize;
   }

   public void setTextColor(Color textColor) {
      this.textColor = textColor;
   }

   public void setFontType(String fontType) {
      this.fontType = fontType;
   }

   public void setAsPassword(boolean isPassword){
      this.isPassword = isPassword;
   }
   private void addLine() {
      if ((float)this.fm.stringWidth((String)this.lines.getLast()) > this.w - 30.0F && !this.maxLetters) {
         if (((String)this.lines.getLast()).charAt(((String)this.lines.getLast()).length() - 1) != ' ' && ((String)this.lines.getLast()).contains(" ")) {
            String[] words = ((String)this.lines.getLast()).split(" ");
            String line = "";

            for(int i = 0; i < words.length - 1; ++i) {
               line = line + words[i] + " ";
            }

            this.lines.set(this.lines.size() - 1, line);
            this.lines.add(words[words.length - 1]);
         } else {
            this.lines.add("");
         }
      }

   }
}
