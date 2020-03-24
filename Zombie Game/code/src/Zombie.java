import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.sun.javafx.geom.Vec2d;

public class Zombie extends GameObject {
	private int hp = 100;
	private float speed = (float) 1.5;

	private String zombieDiraction;
	private BufferedImage zombieImage[] = new BufferedImage[32];
	Animation animUp, animDown, animRight, animLeft, animleftUp, animRightUp, animLeftDown, animRightDown;;

	public Zombie(int x, int y, Handler handler, ID id, SpriteSheet sps) {
		super(x, y, handler, id, sps);
//		for (int i = 0; i < handler.objects.size(); i++)
//			if (handler.objects.get(i).id == ID.Player)
//				this.player = handler.objects.get(i);
		this.zombieDiraction = "down";
		fillImageArry();
		animLeft = new Animation(8, zombieImage[8], zombieImage[9], zombieImage[10], zombieImage[11]);
		animUp = new Animation(8, zombieImage[4], zombieImage[5], zombieImage[6], zombieImage[7]);
		animDown = new Animation(8, zombieImage[0], zombieImage[1], zombieImage[2], zombieImage[3]);
		animRight = new Animation(8, zombieImage[12], zombieImage[13], zombieImage[14], zombieImage[15]);
		animleftUp = new Animation(8, zombieImage[28], zombieImage[29], zombieImage[30], zombieImage[31]);
		animRightUp = new Animation(8, zombieImage[24], zombieImage[25], zombieImage[26], zombieImage[27]);
		animLeftDown = new Animation(8, zombieImage[16], zombieImage[17], zombieImage[18], zombieImage[19]);
		animRightDown = new Animation(8, zombieImage[20], zombieImage[21], zombieImage[22], zombieImage[23]);
	}

	private void fillImageArry() {
		int z = 0;
		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 4; j++) {
				zombieImage[z++] = sps.grabImage(j, i, 75, 90);
			}
		}
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;//30 pix from player
		for (int i = 0; i < handler.objects.size(); i++) {
			GameObject go = handler.objects.get(i);

			if (go.id == ID.Player) {
				float diffX = x - go.getX() ;
				float diffY = y - go.getY() ;

				float distance = (float) Math
						.sqrt((diffX * diffX) + (diffY * diffY));
					 
				velX = (float) ((-1.0 / distance) * diffX)*speed;
				velY = (float) ((-1.0 / distance) * diffY)*speed;
			        
			}

			if (getBoundsBig().intersects(go.getBounds())) {
				if (go.getId() == ID.Block) {

				}
				else if (go.getId() == ID.Bullet) {
					hp -= 25;
					handler.removeObject(go);
				}
				else if(go.getId() == ID.RPG) {
					hp -= 50;
					handler.removeObject(go);
				}
				else if (go.getId() == ID.Player) {
					velX = 0;
					velY = 0;
				}

			}
		}
		if (hp <= 0)
			handler.removeObject(this);

		animUp.runAnimation();
		animDown.runAnimation();
		animRight.runAnimation();
		animLeft.runAnimation();

		animLeftDown.runAnimation();
		animRightDown.runAnimation();
		animleftUp.runAnimation();
		animRightUp.runAnimation();

	}

	/*
	 * if (velY < 0) handler.setZombieDiraction("up"); else if (velY > 0)
	 * handler.setZombieDiraction("down");
	 * 
	 * if (velX <= 0) handler.setZombieDiraction("right"); else if (velX > 0)
	 * handler.setZombieDiraction("left");
	 */
	@Override
	public void render(Graphics g) {

		if (velX == 0 && velY == 0) {
			int offset=0;
			if (this.zombieDiraction == "up")
				g.drawImage(zombieImage[4], x-offset, y-offset, null);
			else if (this.zombieDiraction == "down")
				g.drawImage(zombieImage[0], x-offset, y-offset, null);
			else if (this.zombieDiraction== "right")
				g.drawImage(zombieImage[12], x-offset, y-offset, null);
			else if (this.zombieDiraction == "left")
				g.drawImage(zombieImage[8], x-offset, y-offset, null);
			else if (this.zombieDiraction == "leftUp")
				g.drawImage(zombieImage[28], x-offset, y-offset, null);
			else if (this.zombieDiraction == "leftDown")//chek
				g.drawImage(zombieImage[16], x-offset, y-offset, null);
			else if (this.zombieDiraction == "rightDown")
				g.drawImage(zombieImage[20], x-offset, y-offset, null);
			else 
				g.drawImage(zombieImage[24], x-offset, y-offset, null);

		} else if (velY < 1 && velY >= 0) {
			if (velX >= 0) {
				animRight.drawAnimation(g, x, y, 0);
				this.zombieDiraction="right";
			}
			else {
				animLeft.drawAnimation(g, x, y, 0);
				this.zombieDiraction="left";
			}
		} else if (velX < 1 && velX > 0 ) {
			
			if (velY > 0) {
				animDown.drawAnimation(g, x, y, 0);
				this.zombieDiraction="down";
			}
			else {
				animUp.drawAnimation(g, x, y, 0);
				this.zombieDiraction="up";
			}
		} else {
			if (velX >= 0 && velY >= 0) {
				animRightDown.drawAnimation(g, x, y, 0);
				this.zombieDiraction="rightDown";
			}
			else if (velX <= 0 && velY >= 0) {
				animLeftDown.drawAnimation(g, x, y, 0);
				this.zombieDiraction="leftDown";
			}
			else if (velX >= 0 && velY <= 0) {
				animRightUp.drawAnimation(g, x, y, 0);
				this.zombieDiraction="rightUp";
			}
			else{
				animleftUp.drawAnimation(g, x, y, 0);
				this.zombieDiraction="leftUp";
			}
		}

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

	public Rectangle getBoundsBig() {
		return new Rectangle(x, y, 30, 30);
	}
}
