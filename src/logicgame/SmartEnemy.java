package logicgame;

import java.awt.*;

public class SmartEnemy extends GameObject {
    private Handler handler;
    private ID id;
    private GameObject player;

    public SmartEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler=handler;
        for(int i = 0;i < handler.object.size(); i++){
            if(handler.object.get(i).getId()== ID.Player) player=handler.object.get(i);
        }
    }
    //Lấy kích cỡ của đối tượng(hình chữ nhật) để xử lý va chạm với con Player
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,16,16);
    }
    private float velX,velY;
    //hàm xử lý logic
    @Override
    public void tick() {
        x+=velX;
        y+=velY;
        float diffX=x-player.getX();
        float diffY=y-player.getY();
        //tính khoảng cách giua con SmartEnemy này và Player
        float distance = (float)(Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2)));

        //công thức để tính velX và velY
        //->update được tọa độ (x,y) mới của con SmartEnemy -> thu hẹp khoảng cách giữa con SmartEnemy và Player
        velX = (float) ((-1.0/distance) * diffX);//có thể thay (-2.0/distanse),-3.0,... để thay đổi tốc độ
        velY = (float) ((-1.0/distance) * diffY);

        //xử lý chặn, không cho đối tượng di chuyển ra ngoài khung frame
        if(y<=0 ||y>= Game.HEIGHT-50) velY =velY*-1;
        if(x<=0 ||x>= Game.WIDTH-32)  velX =velX*-1;

        //thêm hiếu ứng ảnh khi di chuyển
        handler.addObject(new Trail((int)x,(int)y, ID.Trail,handler,Color.green,16,16,0.02f));
    }
    //hàm vẽ
    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x,(int)y,16,16);
    }

}
