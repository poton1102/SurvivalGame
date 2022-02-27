package logicgame;

import GUI.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BasicEnemy extends GameObject {
    private Handler handler;
    private ID id;
    private BufferedImage enemy_image;

    public BasicEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        velX=5;
        velY=5;
        this.handler=handler;
        SpriteSheet ss=new SpriteSheet(Game.sprite_sheet);
        enemy_image=ss.grabImage(2,1,16,16);
    }

    //Lấy kích cỡ của đối tượng(hình chữ nhật) để xử lý va chạm với con Player
    public  Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,16,16);
    }

    //hàm xử lý logic
    @Override
    public void tick() {
        //Di chuyển đối tượng với x,y là tọa độ và velX, velY là vận tốc
        x+=velX;
        y+=velY;
        //xử lý chặn, không cho đối tượng di chuyển ra ngoài khung frame
        if(y<=0 ||y>= Game.HEIGHT-50) velY =velY*-1;
        if(x<=0 ||x>= Game.WIDTH-32) velX =velX*-1;
        //thêm hiếu ứng ảnh khi di chuyển
        handler.addObject(new Trail((int)x,(int)y, ID.Trail,handler,Color.red,16,16,0.03f));
    }

    //hàm vẽ
    @Override
    public void render(Graphics g) {
//        g.setColor(Color.red);
//        g.fillRect((int)x  ,(int)y,16,16);
        g.drawImage(enemy_image,(int) x,(int)y, null);

    }

}
