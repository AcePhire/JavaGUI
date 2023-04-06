package UI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JFrame;

public class Handler {
   private JFrame frame;
   private boolean aa;
   public LinkedList<Object> objects = new LinkedList();

   public Handler(JFrame frame) {
      this.frame = frame;
   }

   public void tick() {
      for (int i = 0; i < this.objects.size(); i++) {
         Object o = this.objects.get(i);
         o.tick();
      }
   }

   public void render(Graphics g) {
      Graphics2D g2d = (Graphics2D)g.create();
      if (this.aa) {
         RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
         g2d.setRenderingHints(qualityHints);
      }

      for (int i = 0; i < this.objects.size(); i++) {
         Object o = this.objects.get(i);
         o.render(g2d);
      }

   }

   public void addObject(Object object) {
      this.objects.add(object);
      this.objects = Utilities.SortLayer(this.objects);
   }

   public void addObjects(Object[] objectList) {
      this.objects.addAll(Arrays.asList(objectList));
      this.objects = Utilities.SortLayer(this.objects);
   }

   public void addObjects(LinkedList<Object> objectList) {
      this.objects.addAll(objectList);
      this.objects = Utilities.SortLayer(this.objects);
   }

   public void removeObject(Object object) {
      this.objects.remove(object);
   }

   public void removeObjects(Object[] objectList) {
      this.objects.removeAll(Arrays.asList(objectList));
   }

   public void removeObjects(LinkedList<Object> objectList) {
      this.objects.removeAll(objectList);
   }

   public void setAntiAliasing(boolean antiAliasing) {
      this.aa = antiAliasing;
   }

   public void displayAll() {
      for (int i = 0; i < this.objects.size(); i++) {
         Object o = this.objects.get(i);
         o.isDisplay(true);
      }

   }

   public void hideAll() {
      for (int i = 0; i < this.objects.size(); i++) {
         Object o = this.objects.get(i);
         o.isDisplay(false);
      }

   }

   public void displayObject(java.lang.Object object) {
      for (int i = 0; i < this.objects.size(); i++) {
         Object o = this.objects.get(i);
         if (o == object) {
            o.isDisplay(true);
         }
      }

   }

   public void hideObject(java.lang.Object object) {
      for (int i = 0; i < this.objects.size(); i++) {
         Object o = this.objects.get(i);
         if (o == object) {
            o.isDisplay(false);
         }
      }

   }

   public JFrame getFrame() {
      return this.frame;
   }
}
