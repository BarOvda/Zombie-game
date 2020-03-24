import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Crate extends GameObject {

	private BufferedImage crateImage;

	public Crate(int x, int y, Handler handler, ID id, SpriteSheet sps) {
		super(x, y, handler, id, sps);
		this.crateImage = sps.grabImage(1, 1, 32, 32);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(this.crateImage, x, y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32,32);
	}

}
