package UI;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Iterator;
import javax.swing.JFrame;

public class MouseInput implements MouseListener, MouseMotionListener, MouseWheelListener {
   private Handler handler;
   private JFrame frame;

   public MouseInput(Handler handler) {
      this.handler = handler;
      this.frame = handler.getFrame();
   }

   public void mouseClicked(MouseEvent e) {
   }

   public void mousePressed(MouseEvent e) {
      for (int i = 0; i < this.handler.objects.size(); i++) {
         Object o = this.handler.objects.get(i);
         if (o.getId() != ID.DropdownList){

            if (o.getId() == ID.ActionButton) {
               ActionButton btn = (ActionButton)o;
               if (btn.mouseOver((float)e.getX(), (float)e.getY()) && btn.isDisplay()) {
                  btn.getAction().action();
               }
            }

            if (o.getId() == ID.ToggleButton) {
               ToggleButton btn = (ToggleButton)o;
               if (btn.mouseOver((float)e.getX(), (float)e.getY()) && btn.isDisplay()) {
                  if (btn.isToggle()) {
                     btn.isToggle(false);
                  } else {
                     btn.isToggle(true);
                  }
               }
            }

            if (o.getId() == ID.Checkbox) {
               Checkbox cb = (Checkbox)o;
               if (cb.mouseOver((float)e.getX(), (float)e.getY()) && cb.isDisplay()) {
                  if (cb.isChecked()) {
                     cb.isChecked(false);
                  } else {
                     cb.isChecked(true);
                  }
               }
            }

            if (o.getId() == ID.TextField) {
               TextField tf = (TextField)o;
               if (tf.mouseOver((float)e.getX(), (float)e.getY()) && tf.isDisplay()) {
                  tf.isSelected(true);
               } else {
                  tf.isSelected(false);
               }
            }

            if (o.getId() == ID.Slider) {
               Slider s = (Slider)o;
               if (s.isMouseOver() && s.isDisplay()) {
                  s.isSelected(true);
               }
            }
         }else{
            DropdownList dd = (DropdownList)o;
            for (int j = 0; j < dd.getList().size(); j++) {
               Item item = dd.getList().get(j);
               if (item.mouseOver((float)e.getX(), (float)e.getY()) && item.isDisplay() && dd.getOpened()) {
                  dd.selectItem(item);
               }
            }

            if (dd.mouseOver((float)e.getX(), (float)e.getY()) && dd.isDisplay()) {
               dd.setOpened(!dd.getOpened());
            }
         }
      }
   }

   public void mouseReleased(MouseEvent e) {
      for (int i = 0; i < handler.objects.size(); i++) {
         Object o = handler.objects.get(i);
         if (o.getId() == ID.Slider) {
            Slider s = (Slider)o;
            s.isSelected(false);
         }
      }

   }

   public void mouseEntered(MouseEvent e) {
   }

   public void mouseExited(MouseEvent e) {
   }

   public void mouseDragged(MouseEvent e) {
      for (int i = 0; i < handler.objects.size(); i++) {
         Object o = handler.objects.get(i);
         if (o.getId() == ID.Slider) {
            Slider s = (Slider)o;
            if (s.isSelected()) {
               float value = (float)e.getX() - s.getX();
               s.setValueX(Utilities.clamp(value, 0.0F, s.getW()));
            }
         }
      }

   }

   public void mouseMoved(MouseEvent e){
      for (int i = 0; i < handler.objects.size(); i++) {
         Object o = handler.objects.get(i);
         float[] trans;
         boolean mouseOver;
         if (o.getId() == ID.ActionButton || o.getId() == ID.ToggleButton || o.getId() == ID.Checkbox || o.getId() == ID.DropdownList || o.getId() == ID.TextField) {
            if (o.mouseOver((float)e.getX(), (float)e.getY()) && o.isDisplay()) {
               if (o.getId() == ID.TextField) {
                  this.mouseText();
               } else {
                  this.mouseHand();
               }
               break;
            }

            this.mouseNormal();
            if (o.getId() == ID.DropdownList) {
               DropdownList dd = (DropdownList)o;
               trans = dd.getListTrans();
               mouseOver = dd.mouseOver(trans[0], trans[1], trans[2], trans[3], (float)e.getX(), (float)e.getY());
               if (mouseOver && dd.isDisplay() && dd.getOpened()) {
                  this.mouseHand();
                  break;
               }

               this.mouseNormal();
               for (int j = 0; j < dd.getList().size(); j++) {
                  Item item = dd.getList().get(j);
                  if (item.mouseOver((float)e.getX(), (float)e.getY())) {
                     this.mouseHand();
                     break;
                  }
               }
            }
         }

         if (o.getId() == ID.Slider) {
            Slider s = (Slider)o;
            trans = s.getTrans();
            mouseOver = s.mouseOver(trans[0], trans[1], trans[2], trans[2], (float)e.getX(), (float)e.getY());
            if (mouseOver && s.isDisplay()) {
               this.mouseHand();
               break;
            }

            this.mouseNormal();
         }
      }
   }

   public void mouseWheelMoved(MouseWheelEvent e) {
      for (int i = 0; i < handler.objects.size(); i++) {
      Object o = handler.objects.get(i);
         if (o.getId() == ID.DropdownList) {
            DropdownList dd = (DropdownList)o;
            float[] trans = dd.getListTrans();
            boolean mouseOver = dd.mouseOver(trans[0], trans[1], trans[2], trans[3], (float)e.getX(), (float)e.getY());
            if (mouseOver && dd.isDisplay() && dd.getOpened()) {
               if (e.getWheelRotation() > 0) {
                  dd.scrollDown();
               } else {
                  dd.scrollUp();
               }
            }
         }
      }

   }

   private void mouseHand() {
      this.frame.setCursor(new Cursor(12));
   }

   private void mouseNormal() {
      this.frame.setCursor(new Cursor(0));
   }

   private void mouseText() {
      this.frame.setCursor(new Cursor(2));
   }
}
