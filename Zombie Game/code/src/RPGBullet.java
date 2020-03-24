import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class RPGBullet extends Bullet {
	
	public RPGBullet(int x, int y, Handler handler, ID id, SpriteSheet sps, int velocity) {
		super(x, y, handler, id, sps, velocity);
	}
	@Override
	public void collision() {
		for (int i = 0; i < handler.objects.size(); i++) {
			GameObject tmpobj = handler.objects.get(i);
			if (tmpobj.id == ID.Block)
				if (getBounds().intersects(tmpobj.getBounds())) {
					handler.removeObject(this);
				}
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, 10,15);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 15, 5);
	}

}
