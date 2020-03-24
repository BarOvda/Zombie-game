import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	Handler handler;
	private SpriteSheet sps;
	private Game game;
	private ID bulletType;
	public KeyInput(Handler handler, SpriteSheet sps, Game game) {
		this.handler = handler;
		this.sps = sps;
		this.game = game;
		this.bulletType = ID.Bullet;
	}
	public ID selectedWeapon() {
		return this.bulletType;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < handler.objects.size(); i++) {
			GameObject tmpobj = handler.objects.get(i);
			if (tmpobj.getId() == ID.Player) {
				if(key==KeyEvent.VK_2) 
					this.bulletType=ID.RPG;
				if(key==KeyEvent.VK_1)
					this.bulletType=ID.Bullet;
				if (key == KeyEvent.VK_W)
					handler.setUp(true);

				if (key == KeyEvent.VK_S)
					handler.setDown(true);

				if (key == KeyEvent.VK_D)
					handler.setRight(true);

				if (key == KeyEvent.VK_A)
					handler.setLeft(true);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < handler.objects.size(); i++) {
			GameObject tmpobj = handler.objects.get(i);
			if (tmpobj.getId() == ID.Player) {
				
				if (key == KeyEvent.VK_SPACE) {
					if(this.bulletType==ID.Bullet) {
						handler.addObject(new RegularBullet(tmpobj.getX() + 16, tmpobj.getY() + 24, handler, ID.Bullet, sps,10));
					}else {
						if (game.ammo > 0) {
							handler.addObject(new RPGBullet(tmpobj.getX() + 16, tmpobj.getY() + 24, handler, ID.RPG, sps,6));
							game.ammo--;
						}
					}
				}
				if (key == KeyEvent.VK_W)
					handler.setUp(false);

				if (key == KeyEvent.VK_S)
					handler.setDown(false);

				if (key == KeyEvent.VK_D)
					handler.setRight(false);

				if (key == KeyEvent.VK_A)
					handler.setLeft(false);
			}

		}
	}
}
