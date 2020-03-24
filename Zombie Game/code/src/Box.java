import java.awt.Color;
import java.awt.Graphics;

public class Box extends GameObject {

	public Box(int x, int y,Handler handler, ID id,SpriteSheet sps) {
		super(x, y,handler, id, sps);

	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, 50, 50);

	}

}
