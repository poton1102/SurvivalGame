package logicgame;

import GUI.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject {

    private Handler handler;
    private BufferedImage player_image;
    public Player(float x, float y, ID id, Handler handler){
        super(x,y,id);
        this.handler=handler;

        SpriteSheet ss= new SpriteSheet(Game.sprite_sheet);
        player_image =ss.grabImage(1,1,32,32);
    }
    //hàm xử lý logic
    @Override
    public void tick() {
        //Di chuyển Player với x,y là tọa độ và velX, velY là vận tốc
        x+=velX;
        y+=velY;
        //ngăn không cho player ra khỏi khung frame, hàm clamp ở lớp Game
        x= Game.clamp((int) x,0, Game.WIDTH-46);
        y= Game.clamp((int)y,0, Game.HEIGHT-68);

        //thêm hiếu ứng ảnh khi di chuyển
        handler.addObject(new Trail((int)x,(int)y, ID.Trail,handler,Color.green,32,32,0.07f));

        //hàm xử lý va chạm
        collision();

    }
    //hàm vẽ
    @Override
    public void render(Graphics g) {
        g.drawImage(player_image,(int)x,(int)y,null);
    }

    //Lấy kích cỡ của đối tượng(hình chữ nhật) để xử lý va chạm
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,32,32);
    }

    //hàm xử lý va chạm giữa các đối tượng
    private void collision(){
        for(int i=0; i<handler.object.size(); i++){
            GameObject tempObject= handler.object.get(i);
            if(tempObject.getId()== ID.BasicEnemy|| tempObject.getId()== ID.FastEnemy||tempObject.getId()== ID.SmartEnemy||tempObject.getId()==ID.BossEnemy||tempObject.getId()==ID.HardEnemy){
                if(getBounds().intersects(tempObject.getBounds())){
                    HUD.HEALTH-=2;
                }
            }
        }
    }

}
