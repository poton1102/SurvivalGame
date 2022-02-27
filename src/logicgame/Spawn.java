package logicgame;
import GUI.*;
import java.util.Random;
//xử lý sinh ra kẻ địch theo mỗi level
public class Spawn {
    private Handler handler;
    private HUD hud;
    private SpriteSheet ss;
    private Random r=new Random();
    private Game game;
    private int scoreKeep=0;
    public Spawn(Handler handler, HUD hud, Game game){
        this.handler=handler;
        this.hud=hud;
        this.game=game;
    }
    //hàm xử lý sản sinh đối tượng theo level
    public void tick(){
        scoreKeep++;
        if(scoreKeep>=300){
            scoreKeep=0;//restart lại điểm
            hud.setLevel(hud.getLevel()+1);
            //nếu độ khó là normal
            if(game.diff==0)
            {
                if(hud.getLevel()==2){
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.SmartEnemy, handler));
                }
                else if(hud.getLevel()==3){
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
                }
                else if(hud.getLevel()==5){
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler));
                }
                else if(hud.getLevel()==6){
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
                }
                else if(hud.getLevel()==7){
                    handler.clearEnemys();
                    handler.addObject(new BossEnemy(275, -120, ID.BossEnemy,handler));
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.SmartEnemy, handler));
                }
                else if(hud.getLevel()==12){
                    handler.clearEnemys();
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler));
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.SmartEnemy, handler));
                }
                else if(hud.getLevel()==14){
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler));
                }
                else if(hud.getLevel()==15){
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler));
                }
                else if(hud.getLevel()==16){
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler));
                }
                else if(hud.getLevel()==17){
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler));
                }
                else if(hud.getLevel()==18){
                    handler.clearEnemys();
                    handler.addObject(new BossEnemy(275, -120, ID.BossEnemy,handler));
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.SmartEnemy, handler));
                }
            }
            //nếu độ khó là hard
            else if(game.diff==1){
                if(hud.getLevel()==2){
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.SmartEnemy, handler));
                }
                else if(hud.getLevel()==3){
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
                }

                else if(hud.getLevel()==5){
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.SmartEnemy, handler));
                }
                else if(hud.getLevel()==6){
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler));

                }
                else if(hud.getLevel()==7){
                    handler.clearEnemys();
                    handler.addObject(new BossEnemy(275, -120, ID.BossEnemy,handler));
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.SmartEnemy, handler));

                }
                else if(hud.getLevel()==12){
                    handler.clearEnemys();
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler));
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.SmartEnemy, handler));
                }
                else if(hud.getLevel()==14){
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler));
                }
                else if(hud.getLevel()==15){
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler));
                }
                else if(hud.getLevel()==16){
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler));
                }
                else if(hud.getLevel()==17){
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler));
                }
                else if(hud.getLevel()==18){
                    handler.clearEnemys();
                    handler.addObject(new BossEnemy(275, -120, ID.BossEnemy,handler));
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.SmartEnemy, handler));
                }
            }
        }

    }
}


