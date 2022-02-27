package GUI;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BufferedImageLoader {
    BufferedImage image;
    //lấy ảnh ra từ thư viện
    public BufferedImage loadImage(String path){
        try {
            image= ImageIO.read(getClass().getResource(path));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }
    public BufferedImage getImage() {
        return image;
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
