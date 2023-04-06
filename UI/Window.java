package UI;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;

public class Window extends Canvas {
   public JFrame frame = new JFrame();
   public Handler handler;

   public Window(int width, int height, String title, App app) {
      this.frame.add(app);
      this.frame.pack();
      int x = this.frame.getInsets().left + this.frame.getInsets().right;
      int y = this.frame.getInsets().top + this.frame.getInsets().bottom;
      this.frame.setPreferredSize(new Dimension(width + x, height + y));
      this.frame.setMaximumSize(new Dimension(width + x, height + y));
      this.frame.setMinimumSize(new Dimension(width + x, height + y));
      this.frame.setDefaultCloseOperation(3);
      this.frame.setTitle(title);
      this.frame.setResizable(false);
      this.frame.setLocationRelativeTo((Component)null);
      this.frame.setVisible(true);
      this.handler = new Handler(this.frame);
      app.start();
   }

   public Window(String title, App app) {
      this.frame.setUndecorated(true);
      this.frame.add(app);
      this.frame.pack();
      GraphicsEnvironment gfxEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
      GraphicsDevice gfxDev = gfxEnv.getDefaultScreenDevice();
      Dimension screen = new Dimension(gfxDev.getDisplayMode().getWidth(), gfxDev.getDisplayMode().getHeight());
      this.frame.setPreferredSize(screen);
      this.frame.setMaximumSize(screen);
      this.frame.setMinimumSize(screen);
      this.frame.setDefaultCloseOperation(3);
      this.frame.setTitle(title);
      this.frame.setResizable(true);
      this.frame.setLocationRelativeTo((Component)null);
      this.frame.setVisible(true);
      app.start();
   }
}
