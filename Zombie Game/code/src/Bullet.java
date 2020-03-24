import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Bullet extends GameObject{
	private int velocity;
	

	public Bullet(int x, int y, Handler handler, ID id, SpriteSheet sps,int velocity) {
		super(x, y, handler, id, sps);
				
		if (handler.getPlayerDiraction() == "up")
			velY = -1*velocity;
				
		if (handler.getPlayerDiraction() == "down")
			velY = velocity;
	
		if (handler.getPlayerDiraction() == "right")
			velX = velocity;
						
		if (handler.getPlayerDiraction() == "left")
			velX = -1*velocity;
		
		if (handler.getPlayerDiraction() == "leftUp") {
			velY = -1*velocity;
			velX = -1*velocity;
		}
		if (handler.getPlayerDiraction() == "rightUp") {
			velY = -1*velocity;
			velX = velocity;
		}
		if (handler.getPlayerDiraction() == "leftDown") {
			velY = velocity;
			velX = -1*velocity;
		}
		if (handler.getPlayerDiraction() == "rightDown") {
			velY = velocity;
			velX = velocity;
		}
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		collision();
	}
	
	public abstract void collision();
	
	@Override
	public abstract void render(Graphics g);
	@Override
	public abstract Rectangle getBounds();

}


