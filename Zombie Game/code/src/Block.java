import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Block extends GameObject {
	private BufferedImage blockImage;

	public Block(int x, int y, Handler handler, ID id, SpriteSheet sps) {
		super(x, y, handler, id, sps);
		blockImage = sps.grabImage(1, 1, 64, 64);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(blockImage, x, y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 64, 64);
	}
}
