
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StreamTokenizer;
import java.nio.Buffer;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

import javax.imageio.ImageIO;

import org.omg.CORBA.portable.InputStream;

public class Labelling1 {
	public int number_of_objects;

	public static void main(String[] args) throws IOException{
		 try{
	 BufferedImage img =ImageIO.read(new File("/home/ali/Desktop/test.jpg"));
	 countObjects(mapBitwiththreshold(img,152));
	 
		 }catch(IOException e){
		      System.out.println(e);
		 }
		
	}
	
	private static void countObjects(Point[][] mapBitwiththreshold) throws FileNotFoundException {
		
		Point [][]label= new Point[mapBitwiththreshold.length][mapBitwiththreshold[0].length];
		HashMap<Integer, Integer> unique= new HashMap<>();
		int groupno=0;
		//System.out.println("ok");
		for(int y = 0; y < mapBitwiththreshold[0].length-1; y++){
		      for(int x = 0; x < mapBitwiththreshold.length-1; x++){
		    	  //if(!mapBitwiththreshold[0][1].visited &&) traverse()
		    	  if(!mapBitwiththreshold[x][y].visited && mapBitwiththreshold[x][y].value==1)traceAndGroup(mapBitwiththreshold[x][y], groupno++);
		    	  else mapBitwiththreshold[x][y].visited=true;
		    	  System.out.print(mapBitwiththreshold[x][y].group+" ");	
		    	  
		    	  }
		      System.out.println();
		   }
		System.out.println(groupno);
		}
	
	public static void traceAndGroup(Point p,int groupno) {
		Stack<Point> traceGroup=new Stack<>();
		traceGroup.addAll(p.neighbour);
		while(!traceGroup.isEmpty()) {
			Point current= traceGroup.pop();
			current.group=groupno;
			
			for (Point point : current.neighbour) {
				if(!point.visited) {
					if(point.value==1)traceGroup.push(point);
				}	
			}
			
			current.visited=true;
			
		}
		
		
	}
	

public static Point[][] mapBitwiththreshold(BufferedImage img, int threshold) throws FileNotFoundException{
		int width = img.getWidth();
	    int height = img.getHeight();
	    Point labelled[][]=new Point[width][height];
		for(int y = 0; y < height; y++){
		      for(int x = 0; x < width; x++){
		        int p = img.getRGB(x,y);

		        int a = (p>>24)&0xff;
		        int r = (p>>16)&0xff;
		        int g = (p>>8)&0xff;
		        int b = p&0xff;
		        int avg = (r+g+b)/3;
		        if(avg>threshold) labelled[x][y]= new Point(x, y,1);
		        else labelled[x][y]=new Point(x, y,0);
		       }
		     }
		for(int y = 0; y < height; y++){
		      for(int x = 0; x < width; x++){
		    	  labelled[x][y].setNegighbours(labelled);
		      }     
		}
		
		return labelled;
	}

}
