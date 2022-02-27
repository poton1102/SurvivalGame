package logicgame;

import GUI.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FastEnemy extends GameObject {
    private Handler handler;
    private ID id;
    private BufferedImage fast_image;
    public FastEnemy(float x, float y, ID id, Handler handler) {
        super(x, y,id);
        velX=8;
        velY=8;
        this.handler=handler;
        SpriteSheet ss= new SpriteSheet(Game.sprite_sheet);

        fast_image =ss.grabImage(4,1,16,16);
    }
    //Lấy kích cỡ của đối tượng(hình chữ nhật) để xử lý va chạm với con Player
    public Rectangle getBounds(){
        return new Rectangle((int) x,(int)y,16,16);
    }
    //hàm xử lý logic
    @Override
    public void tick() {
        x+=velX;
        y+=velY;
        //cho nó nảy bật lại
        if(y<=0 ||y>= Game.HEIGHT-50) velY =velY*-1;//nếu y<0 cho nhân với -1 để ra dương, để nó bật lại trong khung frame
        if(x<=0 ||x>= Game.WIDTH-32) velX =velX*-1;//nếu y<0 cho nhân với -1 để ra dương, để nó bật lại trong khung frame

        handler.addObject(new Trail((int) x,(int) y, ID.Trail,handler,Color.blue,16,16,0.04f));

    }
    //hàm vẽ
    @Override
    public void render(Graphics g) {

//        g.setColor(Color.red);
//        g.fillRect((int) x,(int) y,16,16);
        g.drawImage(fast_image,(int)x,(int)y,null );
    }

}
