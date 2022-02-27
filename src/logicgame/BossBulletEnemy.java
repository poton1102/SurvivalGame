package logicgame;
import java.awt.*;
import java.util.Random;

public class BossBulletEnemy extends GameObject {
    private Handler handler;
    Random r=new Random();
    private ID id;

    public BossBulletEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        velX=(r.nextInt(10)+ -5);//random number from -5 to +5
        velY=5;
        this.handler=handler;
    }
    //Lấy kích cỡ của đối tượng(hình chữ nhật) để xử lý va chạm với con Player
    public  Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,16,16);
    }
    //hàm xử lý logic
    @Override
    public void tick() {
        x+=velX;
        y+=velY;
        if(y>=Game.HEIGHT) handler.removeObject(this);//bullet xuyên qua frame thì xóa
        //thêm đối tượng trail
        handler.addObject(new Trail((int)x,(int)y, ID.Trail,handler,Color.yellow,16,16,0.03f));
    }
    //hàm vẽ
    @Override
    public void render(Graphics g) {

        g.setColor(Color.yellow);
        g.fillRect((int)x  ,(int)y,16,16);
    }

}
