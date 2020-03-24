import java.awt.Graphics;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Player extends GameObject {

	private BufferedImage playerImage[] = new BufferedImage[32];
	Animation animUp, animDown, animRight, animLeft, animleftUp, animRightUp, animLeftDown, animRightDown;
	private Game game;
	private int count;

	public Player(int x, int y, Handler handler, ID id, SpriteSheet sps, Game game) {
		super(x, y, handler, id, sps);
		fillImageArry();
		handler.setPlayerDiraction("down");
		animLeft = new Animation(8, playerImage[0], playerImage[1], playerImage[2], playerImage[3]);
		animUp = new Animation(8, playerImage[4], playerImage[5], playerImage[6], playerImage[7]);
		animDown = new Animation(8, playerImage[8], playerImage[9], playerImage[10], playerImage[11]);
		animRight = new Animation(8, playerImage[12], playerImage[13], playerImage[14], playerImage[15]);
		animleftUp = new Animation(8, playerImage[16], playerImage[17], playerImage[18], playerImage[19]);
		animRightUp = new Animation(8, playerImage[20], playerImage[21], playerImage[22], playerImage[23]);
		animLeftDown = new Animation(8, playerImage[24], playerImage[25], playerImage[26], playerImage[27]);
		animRightDown = new Animation(8, playerImage[28], playerImage[29], playerImage[30], playerImage[31]);
		this.game = game;
		this.count = 0;
		
	}

	private void fillImageArry() {
		int z = 0;
		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 4; j++) {
				playerImage[z++] = sps.grabImage(i, j, 64, 64);
			}
		}
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		

		collision();

		if (handler.isUp())
			velY = -3;
		else if (!handler.isDown())
			velY = 0;

		if (handler.isDown())
			velY = 3;
		else if (!handler.isUp())
			velY = 0;

		if (handler.isRight())
			velX = 3;
		else if (!handler.isLeft())
			velX = 0;

		if (handler.isLeft())
			velX = -3;
		else if (!handler.isRight())
			velX = 0;

		animUp.runAnimation();
		animDown.runAnimation();
		animRight.runAnimation();
		animLeft.runAnimation();
		animLeftDown.runAnimation();
		animRightDown.runAnimation();
		animleftUp.runAnimation();
		animRightUp.runAnimation();

	}

	private void collision() {
		for (int i = 0; i < handler.objects.size(); i++) {
			GameObject tmpobj = handler.objects.get(i);

			if (getBounds().intersects(tmpobj.getBounds())) {
				if (tmpobj.id == ID.Block) {
					x += velX * -1;
					y += velY * -1;
				} else if (tmpobj.id == ID.Crate) {
					game.ammo += 10;
					handler.removeObject(tmpobj);
				}
			
			/*if (getBoundsBig().intersects(tmpobj.getX(),tmpobj.getY(),1,1)) {*/
				if (tmpobj.id == ID.Zombie) {

					count++;
					if (count > 10) {
						count = 0;
						game.hp -= 1;
					}
				}
			}
			/*}*/
		}
	}

	@Override
	public void render(Graphics g) {
		if (velX == 0 && velY == 0) {
			if (handler.getPlayerDiraction() == "up")
				g.drawImage(playerImage[4], x, y, null);
			else if (handler.getPlayerDiraction() == "down")
				g.drawImage(playerImage[8], x, y, null);
			else if (handler.getPlayerDiraction() == "right")
				g.drawImage(playerImage[12], x, y, null);
			else if (handler.getPlayerDiraction() == "left")
				g.drawImage(playerImage[0], x, y, null);
			else if (handler.getPlayerDiraction() == "leftUp")
				g.drawImage(playerImage[16], x, y, null);
			else if (handler.getPlayerDiraction() == "leftDown")
				g.drawImage(playerImage[24], x, y, null);
			else if (handler.getPlayerDiraction() == "rightDown")
				g.drawImage(playerImage[28], x, y, null);
			else if (handler.getPlayerDiraction() == "rightUp")
				g.drawImage(playerImage[20], x, y, null);
		} else if (velY == 0) {
			if (velX > 0) {
//				Graphics2D g2d = (Graphics2D) g;
//				g2d.rotate(Math.toRadians(-30), x, y);
//				g2d.drawImage(playerImage[8], x, y);
//				animRight.drawAnimation(g, x, y, 0);
//				
//				g2d.rotate(Math.toRadians(30), x, y);

//				g.rotate(Math.toRadians(50), 100, 100);
//				g.drawImage(image2, 100, 100, this);
//				g.rotate(Math.toRadians(-50), 50, 50);

				animRight.drawAnimation(g, x, y, 0);
				handler.setPlayerDiraction("right");
			}
			if (velX < 0) {
				animLeft.drawAnimation(g, x, y, 0);
				handler.setPlayerDiraction("left");
			}
		} else if (velX == 0) {
			if (velY > 0) {
				animDown.drawAnimation(g, x, y, 0);
				handler.setPlayerDiraction("down");
			}
			if (velY < 0) {
				animUp.drawAnimation(g, x, y, 0);
				handler.setPlayerDiraction("up");
			}
		} else {
			if (velX > 0 && velY > 0) {
				animRightDown.drawAnimation(g, x, y, 0);
				handler.setPlayerDiraction("rightDown");
			}
			if (velX < 0 && velY > 0) {
				animLeftDown.drawAnimation(g, x, y, 0);
				handler.setPlayerDiraction("leftDown");
			}
			if (velX > 0 && velY < 0) {
				animRightUp.drawAnimation(g, x, y, 0);
				handler.setPlayerDiraction("rightUp");
			}
			if (velX < 0 && velY < 0) {
				animleftUp.drawAnimation(g, x, y, 0);
				handler.setPlayerDiraction("leftUp");
			}
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 64, 64);
	}

	public Rectangle getBoundsBig() {
		return new Rectangle(x, y, 120, 120);//chek zombie location
	}

}
