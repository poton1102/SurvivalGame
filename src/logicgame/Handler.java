package logicgame;
import java.awt.*;
import java.util.ArrayList;


//biểu diễn gameobject,
public class Handler {
    //tạo ra mảng đối tượng biểu diễn của lớp GameObject
    ArrayList<GameObject> object=new ArrayList<>();

    //xử lý logic
    public void tick(){
        for(int i=0; i< object.size(); i++){
            GameObject tempObject= object.get(i);
            tempObject.tick();
        }
    }
    //xử lý vẽ
    public void render(Graphics g){
        for(int i=0; i<object.size(); i++){
            GameObject tempObject= object.get(i);
            tempObject.render(g);
        }
    }
    //thêm đối tượng vào mảng
    public void addObject(GameObject object){
        this.object.add(object);
    }
    //xóa đối tượng vào mảng
    public void removeObject(GameObject object){
        this.object.remove(object);
    }

    //xóa tất cả đối tượng xuất hiện trong game
    public void clearEnemys() {
        for(int i=0; i<object.size(); i++){
            GameObject tempObject=this.object.get(i);
            if(tempObject.getId()== ID.Player){
                object.clear();
                if(Game.gameSTATE != Game.STATE.End)
                    addObject(new Player((int)tempObject.getX(),(int)tempObject.getY(), ID.Player,this ));
            }
        }
    }

}

