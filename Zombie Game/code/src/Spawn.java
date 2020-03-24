import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.sun.javafx.geom.Vec2d;

@SuppressWarnings({ "restriction", "unused" })
public class Spawn {
	private Random r = new Random();
	private Vec2d[] spawnSpots;
	private Handler handler;
	private SpriteSheet spz;
	public Spawn(Handler handler,SpriteSheet spz,Vec2d... spawnSpots) {
		this.spawnSpots = new Vec2d[spawnSpots.length];
		int i = 0;
		for (Vec2d vec : spawnSpots) {
			this.spawnSpots[i] = vec;
			i++;
		}
		this.handler=handler;
		this.spz=spz;
	}
	public void tick() {
		int spotNumber=Math.abs(r.nextInt())%spawnSpots.length;
		int xSpawn=(int)spawnSpots[spotNumber].x;
		int ySpawn=(int)spawnSpots[spotNumber].y;
		
		handler.addObject(new Zombie(xSpawn, ySpawn, handler, ID.Zombie, this.spz));
	}
	

}
