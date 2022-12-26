package UI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;
import java.util.Random;

public class App extends Canvas implements Runnable {
   public Window window;
   public Color backgroundColor;
   public int width;
   public int height;
   public Handler handler;
   private Thread thread;
   private boolean running;
   private KeyInput ki;
   private MouseInput mi;

   public App(String title, int width, int height, Color backgroundColor) {
      this.backgroundColor = Color.black;
      this.width = width;
      this.height = height;
      this.initialize(title, width, height, backgroundColor);
      this.requestFocusInWindow();
   }

   public App(String title, Color backgroundColor) {
      this.backgroundColor = Color.black;
      this.initialize(title, backgroundColor);
      GraphicsEnvironment gfxEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
      GraphicsDevice gfxDev = gfxEnv.getDefaultScreenDevice();
      Dimension screenSize = new Dimension(gfxDev.getDisplayMode().getWidth(), gfxDev.getDisplayMode().getHeight());
      this.width = (int)screenSize.getWidth();
      this.height = (int)screenSize.getHeight();
      this.requestFocusInWindow();
   }

   public synchronized void start() {
      this.thread = new Thread(this);
      this.thread.start();
      this.running = true;
   }

   public synchronized void stop() {
      try {
         this.thread.join();
         this.running = false;
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public void run() {
      boolean TARGET_FPS = true;
      long var8 = 16666666L;

      while(this.running) {
         long now = System.nanoTime();
         this.tick();
         this.render();
         long updateTime = System.nanoTime() - now;
         long wait = (16666666L - updateTime) / 1000000L;

         try {
            Thread.sleep(Math.abs(wait));
         } catch (Exception var11) {
            var11.printStackTrace();
         }
      }

      this.stop();
   }

   private void tick() {
      if (this.handler != null) {
         this.handler.tick();
      }

   }

   private void render() {
      BufferStrategy bs = this.getBufferStrategy();
      if (bs == null) {
         this.createBufferStrategy(3);
      } else {
         Graphics g = bs.getDrawGraphics();
         g.setColor(this.backgroundColor);
         g.fillRect(0, 0, this.width, this.height);
         if (this.handler != null) {
            this.handler.render(g);
         }

         g.dispose();
         bs.show();
      }
   }

   private void initialize(String title, int width, int height, Color backgroundColor) {
      this.backgroundColor = backgroundColor;
      this.window = new Window(width, height, title, this);
      this.handler = new Handler(this.window.frame);
      this.ki = new KeyInput(this.handler);
      this.mi = new MouseInput(this.handler);
      this.addKeyListener(this.ki);
      this.addMouseListener(this.mi);
      this.addMouseMotionListener(this.mi);
      this.addMouseWheelListener(this.mi);
   }

   private void initialize(String title, Color backgroundColor) {
      this.backgroundColor = backgroundColor;
      this.window = new Window(title, this);
      this.handler = new Handler(this.window.frame);
      this.ki = new KeyInput(this.handler);
      this.mi = new MouseInput(this.handler);
      this.addKeyListener(this.ki);
      this.addMouseListener(this.mi);
      this.addMouseMotionListener(this.mi);
      this.addMouseWheelListener(this.mi);
   }

   private LinkedList<java.lang.Object> showCase(Handler handler) {
      new Random();
      LinkedList<java.lang.Object> objects = new LinkedList();
      Text tilte = new Text("Show Case", 50, 0.0F, 80.0F, Color.darkGray, 2);
      objects.add(tilte);
      TextField tf1 = new TextField(10.0F, 150.0F, 200.0F, Color.darkGray, 2);
      tf1.setFixedText("Text Field");
      TextField tf2 = new TextField(10.0F, 300.0F, 200.0F, 100.0F, Color.darkGray, 1);
      tf2.setFixedText("Fixed Text Field");
      objects.add(tf1);
      objects.add(tf2);
      Text checkBoxTitle = new Text("Checkboxes", 20, 70.0F, 430.0F, Color.darkGray, 2);
      objects.add(checkBoxTitle);

      for(int i = 0; i < 4; ++i) {
         Checkbox cb = new Checkbox((float)(10 + i * 80), 450.0F, 50.0F, 50.0F, Color.darkGray, this.backgroundColor, 1);
         if (i == 1) {
            cb.setBorderSize(4);
            cb.setArcs(10, 10);
         } else if (i == 2) {
            cb.setArcs(20, 50);
         } else if (i == 3) {
            cb.setBorderSize(4);
            cb.setArcs(40, 40);
         }

         objects.add(cb);
      }

      Text sliderTitle = new Text("Sliders", 20, -149.0F, 150.0F, Color.darkGray, 2);
      objects.add(sliderTitle);

      for(int i = 0; i < 2; ++i) {
         Slider s = new Slider((float)(1 + i * 19), (float)(-259 + i * 130), 170.0F, 100.0F, new Color(52, 194, 128), 1, handler);
         objects.add(s);
      }

      Text actionButtonTitle = new Text("Action Buttons", 20, -149.0F, 250.0F, Color.darkGray, 2);
      objects.add(actionButtonTitle);

      for(int i = 0; i < 2; ++i) {
         for(int j = 0; j < 2; ++j) {
            ActionButton btn;
            if (j == 1) {
               btn = new ActionButton((float)(-259 + i * 50), (float)(280 + j * 50), (float)(30 + i * 120), 30.0F, new Color(72, 127, 194), Color.darkGray, 1);
            } else {
               btn = new ActionButton((float)(-259 + i * 50), (float)(280 + j * 50), (float)(30 + i * 120), 30.0F, Color.darkGray, this.backgroundColor, 1);
            }

            if (i == 1) {
               btn.setText("Button", 15);
            }

            if (i == 1 && j == 0) {
               btn.setBorderSize(4);
               btn.setArcs(10, 10);
            } else if (i == 0 && j == 1) {
               btn.setArcs(20, 50);
            } else if (i == 1 && j == 1) {
               btn.setBorderSize(4);
               btn.setArcs(30, 30);
            }

            objects.add(btn);
         }
      }

      ColorGradient cg1 = new ColorGradient(-259.0F, 375.0F, 50.0F, 200.0F, Color.yellow, Color.magenta, 1);
      ColorGradient cg2 = new ColorGradient(-209.0F, 375.0F, 50.0F, 200.0F, Color.yellow, Color.magenta, 1);
      ColorGradient cg3 = new ColorGradient(-159.0F, 375.0F, 100.0F, 100.0F, Color.yellow, Color.magenta, 1);
      ColorGradient cg4 = new ColorGradient(-159.0F, 475.0F, 100.0F, 100.0F, Color.yellow, Color.magenta, 1);
      cg1.rotate();
      cg2.rotate();
      cg2.flip();
      cg3.flip();
      objects.add(cg1);
      objects.add(cg2);
      objects.add(cg3);
      objects.add(cg4);
      Text toggleButtonTitle = new Text("Toggle Buttons", 20, 0.0F, 200.0F, Color.darkGray, 2);
      objects.add(toggleButtonTitle);

      for(int i = 0; i < 4; ++i) {
         ToggleButton btn = new ToggleButton(-75.0F, (float)(250 + i * 50), 150.0F, 30.0F, Color.darkGray, 1);
         if (i == 1) {
            btn.setBorderSize(4);
            btn.setArcs(10, 10);
         } else if (i == 2) {
            btn.setArcs(20, 50);
         } else if (i == 3) {
            btn.setBorderSize(4);
            btn.setArcs(30, 30);
         }

         if (i > 1) {
            btn.setOnColor(new Color(112, 54, 133));
         }

         objects.add(btn);
      }

      return objects;
   }
}
