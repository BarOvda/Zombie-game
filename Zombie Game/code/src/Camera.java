
public class Camera {
	private float x, y;

	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
	}
	public void tick(GameObject go) {
		x+=((go.getX()-x)-1200/2)*0.05f;
		y+=((go.getY()-y)-800/2)*0.05f;
		
		if(x<=0)x=0;
		if(x>=700)x=700;
		if(y<=0)y=0;
		if(y>=1200)y=1200;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
}
