package logicgame;
import java.awt.*;

public class Trail extends GameObject {
    private float alpha=1;
    private Handler handler;
    private  Color color;
    private  int width, height;//fetch cho từng đối tựợng
    private float life;
    public Trail(int x, int y, ID id, Handler handler, Color color, int width, int height, float life ) {
        super(x, y, id);
        this.handler=handler;
        this.color=color;
        this.width=width;
        this.height=height;
        this.life=life;//life 0.001-0.1
    }


    //hàm tick xử lý để xóa dần các điểm ảnh cũ qua thời gian
    //giá trị life càng lớn tỷ lệ nghịch với giá trị alpha ==> ảnh đích và ảnh nguồn sẽ ngắn lại
    @Override
    public void tick() {
        if(alpha>life){//alpha có value từ 0.0f là vô hình đến 1.0f là hiển thị rõ hoàn toàn
            alpha-=(life-0.00001f);
        }
        else  handler.removeObject(this);
    }

    //Hàm render tạo hiệu ứng để lại điểm ảnh từ điểm nguồn tới điểm đích mỗi khi đối tượng di chuyển
    // ==> Phải xử lý ở hàm tick để xóa dần điểm ảnh cũ qua thời gian
    @Override
    public void render(Graphics g) {
        Graphics2D g2d =(Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));
        g.setColor(color);
        g.fillRect((int)x,(int)y,width,height);
            g2d.setComposite(makeTransparent(1));
    }

    // lớp AlphaComposite thuộc lớp Graphics2D dùng để biểu diễn độ trong suốt được tính toán bằng cách pha trộn điểm ảnh cần làm trong suốt với điểm ảnh nền, các điểm ảnh nền được gọi chung là kênh alpha (alpha channel)
    // và value (từ 0.0f là vô hình đến 1.0f là hiển thị rõ hoàn toàn),
    private AlphaComposite makeTransparent(float alpha){
        int type=AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type,alpha));
    }

    //do ko có va chạm ở trail nên set null
    @Override
    public Rectangle getBounds() {
        return null;
    }

}

