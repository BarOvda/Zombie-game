import java.awt.Color;
import java.awt.Graphics;

public class Level {
	private double score;
	private int level;

	public Level() {
		this.score=0.0;
		this.level=0;
		
	}

	public void tick() {

			score+=0.1;

		if (score > 10000) {
			level += 1;
			score = 0;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("Level: " + this.level, 5, 70);
		g.setColor(Color.WHITE);
		g.drawString("Score: " + (int)this.score, 5, 60);

	}

}
