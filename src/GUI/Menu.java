package GUI;

import logicgame.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import static logicgame.Game.*;

public class Menu extends MouseAdapter {
    private Game game;
    private Handler handler;

    private HUD hud;
    private Random r=new Random();
    public Menu(Game game, Handler handler, HUD hud){
        this.game=game;
        this.handler=handler;
        this.hud=hud;
    }
    //hàm vẽ
    public void render(Graphics g){
        if(game.gameSTATE== Game.STATE.Menu){
            Font fnt=new Font("arial",1,50);
            Font fnt2=new Font("arial",1,30);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Survial Game",150,70);

            g.setFont(fnt2);
            g.drawRect(210,100,200,50);
            g.drawString("Play",270,135);

            g.drawRect(210,170,200,50);
            g.drawString("Help",270,205);

            g.drawRect(210,240,200,50);
            g.drawString("Quit",270,275);
        }
        else if(game.gameSTATE== Game.STATE.Select){
            Font fnt=new Font("arial",1,50);
            Font fnt2=new Font("arial",1,30);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Select difficulty",140,70);

            g.setFont(fnt2);
            g.drawRect(210,100,200,50);
            g.drawString("Normal",270,135);

            g.drawRect(210,170,200,50);
            g.drawString("Hard",270,205);

            g.drawRect(210,240,200,50);
            g.drawString("Back ",270,275);
        }
        else if(game.gameSTATE== Game.STATE.Help){
            Font fnt=new Font("arial",1,50);
            Font fnt2=new Font("arial",1,30);
            Font fnt3=new Font("arial",1,20);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Help",240,70);

            g.setFont(fnt3);
            g.drawString("Use arrow keys to move, P button to pause",100,200);

            g.setFont(fnt2);
            g.drawRect(210,350,200,64);
            g.drawString("Back",270,390);


        }
        else if(game.gameSTATE== Game.STATE.End){
            Font fnt=new Font("arial",1,50);
            Font fnt3=new Font("arial",1,20);
            Font fnt4=new Font("arial",1,20);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Game over",180,70);

            g.setFont(fnt3);
            g.drawString("You died with score: "+hud.getScore(),200,200);

            g.setFont(fnt4);
            g.drawRect(210,340,200,64);
            g.drawString("Try again",260,380);

        }

    }
    public void  tick(){}

    //kiểm tra xem click có chính xác vào phạm vi từng ô
    public boolean mouseOver( int mx, int my, int x, int y, int width, int height){
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else return false;
        } else return false;
    }
    //Được triệu hồi khi nút chuột đã được nhấn trên một thành phần
    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        int mx = e.getX();//click sẽ lấy giá trị của x
        int my = e.getY();//click sẽ lấy giá trị của y

        if(game.gameSTATE== Game.STATE.Menu){
            //play button
            if (mouseOver(mx, my, 210, 100, 200, 50)) {
                game.gameSTATE=STATE.Select;
                return;
            }
            //help button
            if(mouseOver(mx,my,210,170,200,50)){
                game.gameSTATE  = Game.STATE.Help;
            }
            //quit buttion
            if(mouseOver(mx,my,210,240,200,50)){
                System.exit(1);
            }
        }

        if(game.gameSTATE== STATE.Select){
            //normal button
            if (mouseOver(mx, my, 210, 100, 200, 50)) {
                 game.gameSTATE= Game.STATE.Game;
                 handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
                 handler.clearEnemys();
                 handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
                 handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
                 game.diff=0;//độ khó
            }
            //hard button
            if(mouseOver(mx,my,210,170,200,50)){
                game.gameSTATE= Game.STATE.Game;
                handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
                handler.clearEnemys();
                handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.HardEnemy, handler));
                handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.HardEnemy, handler));
                game.diff=1;//độ khó
            }
            //back buttion
            if(mouseOver(mx,my,210,240,200,50)){
                game.gameSTATE= Game.STATE.Menu;
                return;
            }
        }

        //back button for help
        if(game.gameSTATE== Game.STATE.Help){
            if(mouseOver(mx,my,210,350,200,64)){
                game.gameSTATE= Game.STATE.Menu;
                return;
            }
        }
        if(game.gameSTATE== Game.STATE.End){
            if(mouseOver(mx,my,210,340,200,64)){
                game.gameSTATE= STATE.Menu;
                //luu diem khi chet
                hud.setLevel(1);
                hud.setScore(0);
            }
//            if(mouseOver(mx,my,210,240,200,64)){
//                game.gameSTATE=Game.STATE.Menu;
//                hud.setLevel(1);
//                hud.setScore(0);
//                return;
//
//            }

        }
    }

}
