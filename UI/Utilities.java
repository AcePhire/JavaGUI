package UI;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.MouseInfo;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.JFrame;

public class Utilities {
   public static Color inverseColor(Color color) {
      return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
   }

   public static Color addAlpha(Color color, int alpha) {
      int r = color.getRed();
      int g = color.getGreen();
      int b = color.getBlue();
      return new Color(r, g, b, alpha);
   }

   public static float clamp(float value, float min, float max) {
      if (value >= max) {
         return max;
      } else if (value <= min){
         return min;
      } else{
         return value;
      }
   }

   public static Color clamp(Color value, Color min, Color max) {
      int vr = (int)clamp((float)value.getRed(), (float)min.getRed(), (float)max.getRed());
      int vg = (int)clamp((float)value.getGreen(), (float)min.getGreen(), (float)max.getGreen());
      int vb = (int)clamp((float)value.getBlue(), (float)min.getBlue(), (float)max.getBlue());
      return new Color(vr, vg, vb);
   }

   public static int linearInterpolation(int value, int target, float range) {
      int diff = target - value;
      float ratio = (float)diff * range;
      int result = (int)Math.abs(ratio + (float)value);
      return result;
   }

   public static Color colorInterpolation(Color color1, Color color2, float range) {
      int red = linearInterpolation(color1.getRed(), color2.getRed(), range);
      int green = linearInterpolation(color1.getGreen(), color2.getGreen(), range);
      int blue = linearInterpolation(color1.getBlue(), color2.getBlue(), range);
      int alpha = linearInterpolation(color1.getAlpha(), color2.getAlpha(), range);
      Color color = new Color(red, green, blue, alpha);
      return color;
   }

   public static boolean inRange(float value, float min, float max) {
      return value >= min && value <= max;
   }

   public static float[] getMousePos(JFrame frame) {
      return new float[]{(float)(MouseInfo.getPointerInfo().getLocation().getX() - frame.getLocationOnScreen().getX()), (float)(MouseInfo.getPointerInfo().getLocation().getY() - frame.getLocationOnScreen().getY())};
   }

   public static LinkedList<Object> SortLayer(LinkedList<Object> objects) {
      LinkedList<Object> objectsList = objects;
      LinkedList<Object> sortedObjects = new LinkedList();
      boolean notSorted = true;
      if (objects.size() == 1) {
         return objects;
      } else {
         while(true) {
            while(notSorted) {
               if (sortedObjects.size() != 0) {
                  objectsList = sortedObjects;
               }

               sortedObjects = new LinkedList();

               while(objectsList.size() > 1) {
                  if ((objectsList.get(0)).getLayer() > (objectsList.get(1)).getLayer()) {
                     sortedObjects.add(objectsList.get(1));
                     objectsList.remove(objectsList.get(1));
                  } else {
                     sortedObjects.add(objectsList.get(0));
                     objectsList.remove(objectsList.get(0));
                  }
               }

               sortedObjects.add(objectsList.get(0));
               objectsList.remove(objectsList.get(0));

               for (int i = 0; i < sortedObjects.size(); i++){
                  Object object = sortedObjects.get(i);
                  if (sortedObjects.size() - 1 != sortedObjects.indexOf(object)) {
                     if (object.getLayer() > (sortedObjects.get(sortedObjects.indexOf(object) + 1)).getLayer()) {
                        notSorted = true;
                        break;
                     }

                     notSorted = false;
                  }
               }
            }

            return sortedObjects;
         }
      }
   }

   public static Color hex2Rgb(String hex) {
      int r = Integer.valueOf(hex.substring(1, 3), 16);
      int g = Integer.valueOf(hex.substring(3, 5), 16);
      int b = Integer.valueOf(hex.substring(5, 7), 16);
      return new Color(r, g, b);
   }

   public static Color randomColor() {
      Random r = new Random();
      return new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
   }

   public static String limitString(String text, FontMetrics fm, float maxWidth) {
      String maxStr = "";
      char[] textArray = text.toCharArray();

      for(int i = 0; (float)fm.stringWidth(maxStr) < maxWidth && i < textArray.length; ++i) {
         maxStr = maxStr + textArray[i];
      }

      return maxStr;
   }
}
