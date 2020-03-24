import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	protected int x, y;
	protected float velX = 0, velY = 0;
	protected Handler handler;
	protected ID id;
	protected Rectangle bounds;
	protected SpriteSheet sps;
	public GameObject(int x,int y,Handler handler,ID id,SpriteSheet sps) {
		this.x=x;
		this.y=y;
		this.handler=handler;
		this.id=id;
		this.sps=sps;
	}
	public ID getId() {
		return id;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public abstract void tick();
	public abstract void render(Graphics g);
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}
	public Rectangle getBounds() {
		return bounds;
	}
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
}
