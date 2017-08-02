package Lab1;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Grayscale{
  public static void main(String args[])throws IOException{
    BufferedImage img = null;
    File f = null;

    //read image
    try{
      f = new File("/home/ali/Downloads/images.jpg");
      img = ImageIO.read(f);
    }catch(IOException e){
      System.out.println(e);
    }

    //get image width and height
    int width = img.getWidth();
    int height = img.getHeight();
    String test="";
    //convert to grayscale
    for(int y = 0; y < height; y++){
      for(int x = 0; x < width; x++){
        int p = img.getRGB(x,y);

        int a = (p>>24)&0xff;
        int r = (p>>16)&0xff;
        int g = (p>>8)&0xff;
        int b = p&0xff;

        //calculate average
        int avg = (r+g+b)/3;
        if(avg>100)System.out.printf("1 ",avg);
        else System.out.printf("0 ",avg);
        //replace RGB value with avg
        p = (a<<24) | (avg<<16) | (avg<<8) | avg;
        
        img.setRGB(x, y, p);
      }
      System.out.println();
    }

    //write image
    try{
      f = new File("/home/ali/Downloads/Output.jpg");
      ImageIO.write(img, "jpg", f);
    }catch(IOException e){
      System.out.println(e);
    }
  }//main() ends here
}//class ends here