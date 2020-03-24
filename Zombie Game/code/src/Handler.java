import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	LinkedList<GameObject> objects = new LinkedList<>();
	private boolean up = false, down = false, right = false, left = false;
	private String playerDiraction;
	
	
	public void tick() {
		for (int i = 0; i < objects.size(); i++) {
			GameObject tmp = objects.get(i);
			tmp.tick();
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			GameObject tmp = objects.get(i);
			tmp.render(g);
		}
	}

	public void addObject(GameObject gameObj) {
		this.objects.add(gameObj);
	}

	public void removeObject(GameObject gameObj) {
		this.objects.remove(gameObj);
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}


	public String getPlayerDiraction() {
		return playerDiraction;
	}

	public void setPlayerDiraction(String playerDiraction) {
		this.playerDiraction = playerDiraction;
	}

	
	
}

