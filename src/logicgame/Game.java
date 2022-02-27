package logicgame;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import GUI.*;
import GUI.Menu;
import GUI.Window;
//import database.connect;

public class Game extends Canvas implements  Runnable {
    private Handler handler;
    public static final int WIDTH=640, HEIGHT=480;
    private Thread  thread;
    private SpriteSheet ss;
    public int diff=0;
    public static BufferedImage sprite_sheet;
    public static BufferedImage sprite_sheet1;
    public static BufferedImage background;
    public static boolean paused=false;//key input
    private  boolean running=false;
    private Random r;
    public HUD hud;
    private Spawn spawner;
    private Menu menu;
  //  private connect cn;

    public Game(){
        handler= new Handler();
        hud= new HUD();
        menu=new Menu(this,handler, hud);
        BufferedImageLoader loader=new BufferedImageLoader();
        sprite_sheet =loader.loadImage("/sprite_sheet12.png");
        sprite_sheet1=loader.loadImage("/sprite_sheet13.png");
        background= loader.loadImage("/sprite_sheet14.png");
        spawner= new Spawn(handler, hud,this);
        this.addKeyListener(new KeyInput(handler,this));
        this.addMouseListener(menu);
        r=new Random();
    //   cn = new connect();
    //    cn.extractPoint();
        new Window(WIDTH, HEIGHT,"SurvivalGame",this);
    }

    //tạo 1 enum STATE để làm menu
    public enum STATE{
        Menu,
        Select,
        Help,
        End,
        Game
    }

    public static STATE gameSTATE= STATE.Menu;//giao diện đầu tiên thấy được khi khởi động game

    //Hàm xử lý logic trong từng STATE
    public  void  tick(){
        if(gameSTATE== STATE.Game){
            if(!paused) {
                hud.tick();
                spawner.tick();
                handler.tick();
                //chơi lại
                if (HUD.HEALTH <= 0) {
                    HUD.HEALTH = 100;
                    //cn.checkScore();
                    //cn.extractPoint();
                    gameSTATE = STATE.End;
                    handler.object.clear();
                }
            }
        }
        else if(gameSTATE==STATE.Menu||gameSTATE==STATE.End||gameSTATE==STATE.Select){
            menu.tick();
            handler.tick();
            if(HUD.HEALTH<=0){
                HUD.HEALTH=100;
                //cn.checkScore();
            }
        }
    }

    //hàm xử lý kết xuất ra màn hình(GUI)
    public  void render(){
        BufferStrategy bs=this.getBufferStrategy();//Hiện tượng nháy, giật hình là do chúng ta vẽ đi vẽ lại nhiều lần trực tiếp trên 1 Form.Giả sử chúng ta có nhiều Object cần vẽ trong game,mỗi lần vẽ thì Form đc repaint lại--> nhiều lần như vậy gây ra hiện tượng giật hình.
        //Java API cũng cung cấp cho chúng ta 1 lớp để thực hiện kĩ thuật chống nháy hình: đó chính là class BufferStrategy(giống như bộ nhớ lưu trữ tạm thời cho display)
        if(bs==null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g=bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);
        g.drawImage(background,0,0,null);
        handler.render(g);

        for(int i=0; i<handler.object.size(); i++){
            GameObject tempObject=handler.object.get(i);
            if(tempObject.getId()==ID.Player||tempObject.getId()==ID.BasicEnemy||tempObject.getId()==ID.FastEnemy||tempObject.getId()==ID.BossEnemy){
                tempObject.render(g);
            }
        }

        if(paused){
            g.setColor(Color.white);
            g.drawString("PAUSED",280,250);
        }
        if(gameSTATE==STATE.Game){
            hud.render(g);
        }
        else if(gameSTATE==STATE.Menu||gameSTATE==STATE.Help||gameSTATE==STATE.End||gameSTATE==STATE.Select) {
            menu.render(g);
        }
            g.dispose();
            bs.show();
    }

    // khởi động thread
    public synchronized void  start(){
        thread=new Thread(this);
        thread.start();
        running=true;
    }
    //dừng thread
    public synchronized void stop(){
        try{
            thread.join();
            running=false;
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    //game loop, với tickrate=60(60 update/ 1s), tickrate(update) khác với fps(frames fer second) và 2 phần này được xử lý riêng biệt trong hàm run
    @Override
    public void run() {
        long lastTime=System.nanoTime();
        double amountOfTick=60.0;
        double ns=1_000_000_000/amountOfTick;//16,67mili giây cho 1 lần update, 60 lần update cho 1s
        double delta=0;
        long timer=System.currentTimeMillis();
        int frames=0;
        int updates=0;
        while (running){
            long now=System.nanoTime();
            //tính delta ra  một phân số cực nhỏ
            //Nó tính toán thời gian cần để hoàn thành một vòng lặp (tính bằng nano giây) sau đó chia nó cho 60.
            //Giả sử (now - lastTime) bằng X và không đổi.
            //Delta sẽ được tăng ns / X lần trước khi nó đạt đến giá trị 1. Khi delta bằng 1, có nghĩa là 1/60 giây đã trôi qua.
            delta +=(now-lastTime)/ns;
            lastTime=now;
            while (delta>=1){//đợi cho đến khi 1/60 giây trôi rồi cập nhật trò chơi.
                tick() ;
                updates++;
                delta--;
            }
            if(running)
                render();
            frames++;
            if(System.currentTimeMillis()-timer>1000){
                timer+=1000;
               // System.out.println("FPS "+ frames + "tick"+ updates);
                frames=0;
                updates=0;
            }
        }
        stop();
    }

    //chặn con Player ko ra khỏi khung frame
    public static int clamp(int var,int min, int max){
        if(var>=max)
            return var=max;
        if(var<=min)
            return var=min;
        else
            return var;
    }

    public static void main(String[] args) {
        new Game();

    }

}

