package GUI;

import java.awt.*;

public class HUD {
    public static int HEALTH=100;

    public static int score=0;
    public static int  highscore =0;
    private int level=1;

    //tăng điểm
    public void tick(){
        score++;
    }

    //vẽ ra thanh máu, điểm, level, highscore
    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(15,15,200,32);
        g.setColor(Color.getHSBColor( (1f * HEALTH) / 360, 1f, 1f));
        g.fillRect(15,15,HEALTH*2,32);
        g.setColor(Color.white);

        //border
        g.drawRect(15,15,200,32);

        g.drawString("Score: "+score,15,60);
        g.drawString("Level: "+level,15,75);
        g.drawString("Highscore: "+highscore,15,90);
    }

    public int getHighScore() {
        return highscore;
    }
    public void setHighScore(int highScore) {
        this.highscore = highScore;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
}
