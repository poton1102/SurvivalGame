package logicgame;

import GUI.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class BossEnemy extends GameObject {
    private Handler handler;
    Random r=new Random();
    private int timer=100;
    private int timer2=50;
    private BufferedImage boss_image;
    private ID id;
    public BossEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        velX=0;
        velY=2;
        this.handler=handler;
        SpriteSheet ss=new SpriteSheet(Game.sprite_sheet1);
        boss_image=ss.grabImage(1,1,100,100);
    }
    //Lấy kích cỡ của đối tượng(hình chữ nhật) để xử lý va chạm với con Player
    public  Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,100,100);
    }
    //hàm xử lý logic
    @Override
    public void tick() {
        x+=velX;
        y+=velY;
        //Khởi tạo velX=0, velY=2 con boss sẽ di chuyển xuống từ góc 12h
        //Nếu timer<=0 thì velY sẽ dừng
        timer--;
        if(timer<=0) velY=0;
        else timer--;

        //Khi con boss dừng vì velY=0, tạo thêm giá trị timer2 và cấp phát velX để nó di chuyển ngang màn hình
        if(timer<=0) timer2--;
        if(timer2<=0){
           if(velX==0) velX=2;

            int spawn=r.nextInt(10);//trả về 1 số nguyên nằm trong phạm vi [0,10)
            //Đặt điều kiện if(spawn==0) xác xuất để giải phóng ra BossBulletEnemy
            //Nếu không đặt điều kiện thì với tickrate=60 nó sẽ giải phóng BossBulletEnemy như mưa
            if(spawn==0)
                handler.addObject(new BossBulletEnemy((int)x+48,(int)y+95, ID.BasicEnemy,handler));
        }
        //xử lý chặn, không cho đối tượng di chuyển ra ngoài khung frame
        if(x<=0 ||x>= Game.WIDTH-120) velX =velX*-1;
    }
    //hàm vẽ
    @Override
    public void render(Graphics g) {

//        g.setColor(Color.red);
//        g.fillRect((int)x  ,(int)y,100,100);
        g.drawImage(boss_image,(int)x,(int)y,null);

    }

}
