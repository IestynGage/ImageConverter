package sample;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageConverter {

    public boolean convert(File imageFile, String imageOutputType){
        try {
            BufferedImage image = ImageIO.read(imageFile);
            switch(imageOutputType){
                case "jpg":
                    File outputFile = new File(imageFile.getParent()+"/"+imageFile.getName().substring(0, imageFile.getName().length() - 4) +".jpg");
                    ImageIO.write(image,"jpg",outputFile);
                    return true;
                case "png":
                    outputFile = new File(imageFile.getParent()+"/"+imageFile.getName().substring(0, imageFile.getName().length() - 4) +".png");
                    ImageIO.write(image,"png",outputFile);
                    return true;
                case "bmp":
                    outputFile = new File(imageFile.getParent()+"/"+imageFile.getName().substring(0, imageFile.getName().length() - 4) +".bmp");
                    ImageIO.write(image,"bmp",outputFile);
                    return true;
                case "wbmp":
                    outputFile = new File(imageFile.getParent()+"/"+imageFile.getName().substring(0, imageFile.getName().length() - 5) +".wbmp");
                    ImageIO.write(image,"wbmp",outputFile);
                    return true;
                default:
                    System.err.println("ImageConverter convert Error: Reached default case");
                    return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
