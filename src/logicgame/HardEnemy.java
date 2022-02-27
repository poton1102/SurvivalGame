package logicgame;

import java.awt.*;

public class HardEnemy extends GameObject {
    private Handler handler;
    private ID id;

    public HardEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        velX=11;
        velY=11;
        this.handler=handler;
    }
    //Lấy kích cỡ của đối tượng(hình chữ nhật) để xử lý va chạm với con Player
    public  Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,16,16);
    }
    //hàm xử lý logic
    @Override
    public void tick() {
        //di chuyển đối tượng trong khung frame
        x+=velX;
        y+=velY;
        //cho nó nảy bật lại
        if(y<=0 ||y>= Game.HEIGHT-50) velY =velY*-1;//nếu y<0 cho nhân với -1 để ra dương, để nó bật lại trong khung frame
        if(x<=0 ||x>= Game.WIDTH-32) velX =velX*-1;//nếu y<0 cho nhân với -1 để ra dương, để nó bật lại trong khung frame
        //chèn đối tượng trail
        handler.addObject(new Trail((int)x,(int)y, ID.Trail,handler,Color.yellow,16,16,0.05f));
    }
    //hàm vẽ
    @Override
    public void render(Graphics g) {

        g.setColor(Color.yellow);
        g.fillRect((int)x  ,(int)y,16,16);
    }

}
