package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageBox extends Object {
   private BufferedImage image;
   private int borderSize = 0;
   private Color borderColor;

   public ImageBox(String imagePath, float x, float y, float w, float h, int layer) {
      super(x, y, w, h, Color.white, ID.ImageBox, layer);
      File imageFile = new File(imagePath);

      try {
         this.image = ImageIO.read(imageFile);
      } catch (IOException var9) {
         var9.printStackTrace();
      }

   }

   public Rectangle getBounds() {
      return null;
   }

   public void tick() {
   }

   public void render(Graphics g) {
      if (this.display) {
         Graphics2D g2d = (Graphics2D)g.create();
         g2d.drawImage(this.image, (int)this.x, (int)this.y, (int)this.w, (int)this.h, (ImageObserver)null);
         if (this.borderSize != 0) {
            g2d.setColor(this.borderColor);
            g2d.drawRect((int)this.x, (int)this.y, (int)this.w, (int)this.h);
         }

      }
   }

   public BufferedImage getImage() {
      return this.image;
   }

   public void addBorder(int borderSize, Color borderColor) {
      this.borderSize = borderSize;
      this.borderColor = borderColor;
   }
}
