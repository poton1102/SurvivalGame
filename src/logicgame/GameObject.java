package logicgame;

import java.awt.*;

public abstract class GameObject {
    protected float x,y;//tọa độ
    protected ID id;
    protected float velX,velY;// vận tốc(velocity)

    public GameObject(float x, float y, ID id){
        this.x=x;
        this.y=y;
        this.id=id;


    }
    //hàm xử lý logic
    public abstract void tick();
    //hàm vẽ
    public abstract void render(Graphics g);//phương thức này trong các ứng dụng để vẽ bất cứ cái gì lên các Component.
    public abstract Rectangle getBounds();

    public void setId(ID id) {
        this.id = id;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public ID getId() {
        return id;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getVelX() {
        return velX;
    }

    public float getVelY() {
        return velY;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
