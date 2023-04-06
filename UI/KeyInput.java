package UI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;

public class KeyInput implements KeyListener {
   private Handler handler;

   public KeyInput(Handler handler) {
      this.handler = handler;
   }

   public void keyTyped(KeyEvent e) {
      char letter = e.getKeyChar();
      if (letter != '\b') {
         for (int i = 0; i < handler.objects.size(); i++) {
            Object o = handler.objects.get(i);
            if (o.getId() == ID.TextField) {
               TextField tf = (TextField)o;
               if (tf.isSelected()) {
                  tf.addLetter(letter);
               }
            }
         }
      }
   }

   public void keyPressed(KeyEvent e) {
      int key = e.getKeyCode();
      if (key == KeyEvent.VK_BACK_SPACE) {
         for (int i = 0; i < handler.objects.size(); i++) {
            Object o = handler.objects.get(i);
            if (o.getId() == ID.TextField) {
               TextField tf = (TextField)o;
               if (tf.isSelected()) {
                  tf.removeLetter();
               }
            }
         }
      }
   }

   public void keyReleased(KeyEvent e) {
   }
}
