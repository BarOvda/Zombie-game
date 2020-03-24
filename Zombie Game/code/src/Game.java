import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.sun.javafx.geom.Vec2d;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static int WIDTH = 1500, HIGHT = 2000;
	public String title = "Zombie Game";
	private Handler handler;
	private Thread thread;
	private boolean isRunning = false;
	
	private int stableize = 0;
	
	private Spawn spawn;
	private BufferedImage level = null;
	private BufferedImage playerSheet = null;
	private BufferedImage blockSheet = null;
	private BufferedImage crateSheet=null;
	private BufferedImage floor = null;
	private BufferedImage zombieSheet = null;
	public int hp=100;
	public int ammo=50;
	private Camera camera;
	private SpriteSheet sps = null;
	private SpriteSheet spsblock = null;
	private SpriteSheet spscrate=null;
	private SpriteSheet spsz = null;
	private Level gameLevel;
	private KeyInput myKeyInput;
	public Game() {
		camera = new Camera(0, 0);
		new Window(WIDTH, HIGHT, title, this);
		start();
		this.handler = new Handler();
		Vec2d spawnSpot1=new Vec2d(200.0,500.0);
		Vec2d spawnSpot2=new Vec2d(2000.0,500.0);
	
		gameLevel = new Level();
		
		// TODO zombie image
		this.myKeyInput=new KeyInput(handler, sps,this);
		this.addKeyListener(this.myKeyInput);
		BuffredImageLoder loader = new BuffredImageLoder();

		level = loader.loadImage("/level1.png");
		playerSheet = loader.loadImage("/player_sheet.png");
		blockSheet = loader.loadImage("/block.jpg");
		crateSheet=loader.loadImage("/RTS_Crate.png");
		zombieSheet = loader.loadImage("/my_zombie_sheet.png");

		sps = new SpriteSheet(playerSheet);
		spsblock = new SpriteSheet(blockSheet);
		spscrate=new SpriteSheet(crateSheet);
		spsz = new SpriteSheet(zombieSheet);
		floor = loader.loadImage("/sand-backround.jpg");
		handler.addObject(new Zombie(600, 150, handler, ID.Zombie, spsz));
		this.spawn=new Spawn(this.handler,spsz,spawnSpot1,spawnSpot2);
		loadLevel(level);
	}

	private synchronized void start() {
		if (isRunning)
			return;
		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}

	private synchronized void stop() {
		if (!isRunning)
			return;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		isRunning = false;
	}

	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}

	private void tick() {
		for (int i = 0; i < handler.objects.size(); i++) {
			if (handler.objects.get(i).getId() == ID.Player) 
				camera.tick(handler.objects.get(i));
		}
		handler.tick();
		gameLevel.tick();
		if(stableize==500) {
			spawn.tick();
			stableize=0;
		}
		stableize++;
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;

		g.setColor(Color.gray);
		g.fillRect(0, 0, WIDTH, HIGHT);

		g2d.translate(-camera.getX(), -camera.getY());

		for (int i = 0; i < 30 * 72; i += 1920) {
			for (int j = 0; j < 30 * 72; j += 1000) {
				g.drawImage(floor, i, j, null);

			}
		}

		handler.render(g);
		
		g2d.translate(camera.getX(), camera.getY());
		g.setColor(Color.GRAY);
		g.fillRect(5, 5, 200, 32);
		g.setColor(Color.green);
		g.fillRect(5, 5, hp*2, 32);
		g.setColor(Color.black);
		g.drawRect(5, 5, 200, 32);
		g.setColor(Color.WHITE);
		
		drawWeaponStatus(g,this.myKeyInput.selectedWeapon());
		gameLevel.render(g);
		bs.show();
		g.dispose();
	}
	private void drawWeaponStatus(Graphics g,ID weapon) {
		
		if(weapon==ID.RPG)
			g.drawString("RPG |  Ammo: "+this.ammo, 5, 50);
		else if(weapon==ID.Bullet)
			g.drawString("Gun", 100, 50);
		
	}
	private void loadLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				int pixel = image.getRGB(i, j);
				int red = (pixel >> 16) & 0Xff;
				int green = (pixel >> 8) & 0xff;
				int blue = pixel & 0xff;
				if (red == 255) 
					handler.addObject(new Block(i * 32, j * 32, handler, ID.Block, spsblock));
				if (blue == 255)
					handler.addObject(new Player(i * 32, j * 32, handler, ID.Player, sps,this));
				if(green==255)
					handler.addObject(new Crate(i * 32, j * 32, handler, ID.Crate, spscrate));
			}
		}
	}

	public static void main(String[] args) {
		new Game();
	}
}
