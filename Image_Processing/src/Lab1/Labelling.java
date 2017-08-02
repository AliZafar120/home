package Lab1;

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
import java.util.HashMap;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.omg.CORBA.portable.InputStream;

public class Labelling {

	public static void main(String[] args) throws IOException{
		 try{
		 BufferedImage img =ImageIO.read(new File("/home/ali/Downloads/test.jpg"));
			 //returnPGMbitMap("/home/ali/Downloads/test.pgm");
		 //mapBitwiththreshold(img, 120);
		 countObjects(mapBitwiththreshold(img,152));
		 writeImage("/home/ali/Downloads/coins.jpg","jpg", bitmapImage(img, 100));
	     writeImage("/home/ali/Downloads/coinsgrey.jpg","jpg",getGreyScale(img));
		 }catch(IOException e){
		      System.out.println(e);
		 }
		
	}
	
	private static void countObjects(int[][] mapBitwiththreshold) throws FileNotFoundException {
		// TODO Auto-generated method stub
		/*for(int y = 0; y < mapBitwiththreshold[0].length; y++){
		      for(int x = 0; x < mapBitwiththreshold.length; x++){
		    	  System.out.print(mapBitwiththreshold[x][y]+" ");
		      }
		      System.out.println();
		   }*/
		
		String [][]label= new String[mapBitwiththreshold.length+1][mapBitwiththreshold[0].length];
		HashMap<String, Integer> unique= new HashMap<>();
		int groupno=1;
		String result="";
		for(int y = 0; y <  mapBitwiththreshold[0].length; y++){
		      for(int x = 0; x < mapBitwiththreshold.length; x++){
		    	  //result+=mapBitwiththreshold[x][y]+" ";
		        if((x-1)>=0){
		        	if(mapBitwiththreshold[x-1][y]==1){
		        		if(mapBitwiththreshold[x][y]==1){
		        		if(unique.keySet().contains(label[x-1][y]+"")){
		        			unique.put(label[x-1][y],unique.get(label[x-1][y])+1);
		        			label[x][y]=label[x-1][y];
		        		}
		        		else 	{
		        			label[x][y]=""+groupno;
		        			unique.put(label[x][y],1);
		        			groupno++;
		        			
		        			}
		        		//result+= label[x][y];
		        	     
		        		
		        		}
		        	}
		       }
		        
		        if((y-1)>=0){
		        	if(mapBitwiththreshold[x][y-1]==1){
		        	if(mapBitwiththreshold[x][y]==1){
		        	if(unique.keySet().contains(label[x][y-1])){
		        		unique.put(label[x][y-1],unique.get(label[x][y-1])+1);
		        		label[x][y]=label[x][y-1];
		        		boolean found= false;
		        		String labelname="";
		        		for(int n=x;n>=0;n--){
		        			if(mapBitwiththreshold[n][y]==1 && unique.keySet().contains(label[n][y])){
		        				labelname=label[n][y];
		        				label[n][y]=label[x][y-1];
		        				found=true;
		        			}
		        		}
		        		if(found){
		        			unique.keySet().remove(labelname);
		        			groupno--;
		        			}
		        	}
				      else {
				        label[x][y]=""+groupno;
				        unique.put(label[x][y],1);
				        groupno++;
				       
				       }
		        	//result+= label[x][y];
	        	     
		        	}
		          }
		        }
		   
		        label[x][y]="0";
		        
		        result+= label[x][y];
		   }
		      result+= "\n";
		     
		}
		printArraytofile("/home/ali/Downloads/test.txt",label);
		System.out.println(groupno);
		System.out.println(unique.keySet().size());
		try (PrintStream out = new PrintStream(new FileOutputStream("/home/ali/Downloads/test.txt"))) {
		    out.print(result);
		}

	}

	/*public int countNumberofObjects(int[][] bitarray){
		for (int row = 0; row < picHeight; row++) {
		     for (int col = 0; col < picWidth; col++) {
		         data2D[row][col] = dis.readUnsignedByte();
		         //System.out.print(data2D[row][col] + " ");
		         if(data2D[row][col]>40)System.out.print("1 ");
		         else System.out.print("0 ");
		     }
		     System.out.println();
		 }
		
	}*/
	
	
	/*
	public int[][]returnPGMbitMap(String filename) throws IOException{
		
		FileInputStream fileInputStream = new FileInputStream(filename);
		Scanner scan = new Scanner(fileInputStream);
		// Discard the magic number
		scan.nextLine();
		// Discard the comment line
		scan.nextLine();
		// Read pic width, height and max value
		int picWidth = scan.nextInt();
		int picHeight = scan.nextInt();
		int maxvalue = scan.nextInt();
		
		fileInputStream.close();

		 // Now parse the file as binary data
		 fileInputStream = new FileInputStream(filename);
		 DataInputStream dis = new DataInputStream(fileInputStream);

		 // look for 4 lines (i.e.: the header) and discard them
		 int numnewlines = 4;
		 while (numnewlines > 0) {
		     char c;
		     do {
		         c = (char)(dis.readUnsignedByte());
		     } while (c != '\n');
		     numnewlines--;
		 }

		 HashMap<String, Integer> uniques= new HashMap<>();
		 String [][]lables=new String[picWidth][picHeight];
		 // read the image data
		 int[][] data2D = new int[picHeight][picWidth];
		 for (int row = 0; row < picHeight; row++) {
		     for (int col = 0; col < picWidth; col++) {
		         //data2D[row][col] = dis.readUnsignedByte();
		         //System.out.print(data2D[row][col] + " ");
		    	 data2D[row][col] = dis.readUnsignedByte();
		    	 if((row-1)!=-1){
		    		 
		    	 }
		    	 if((col-1)!=-1){
		    		 
		    	 }
		         if(data2D[row][col]>40)System.out.print("0 ");
		         else System.out.print("1 ");
		     }
		     System.out.println();
		 }

		return data2D;

		
	}
	*/
	public void countNumberofCoins(BufferedImage img){
		//int bitlabel[][]=mapBitwiththreshold(img, 100);
		
		
		
	}
	
	
	public static int labelled[][];
	
	public static int[][] mapBitwiththreshold(BufferedImage img, int threshold) throws FileNotFoundException{
		
		int width = img.getWidth();
	    int height = img.getHeight();
	    int labelled[][]=new int[width+1][height+1];
	    String result="";
		
		for(int y = 0; y < height; y++){
		      for(int x = 0; x < width; x++){
		        int p = img.getRGB(x,y);

		        int a = (p>>24)&0xff;
		        int r = (p>>16)&0xff;
		        int g = (p>>8)&0xff;
		        int b = p&0xff;

		        //calculate average
		        int avg = (r+g+b)/3;
		        if(avg>threshold)labelled[x][y]=1;
		        else labelled[x][y]=0;
		        //System.out.printf("%d ",labelled[width][height]);
		        result+=labelled[width][height]+" ";
		        //System.out.printf(avg+" ");
		      }
		      //System.out.println();
		       result+="\n";
		    }

		//System.out.println(result);
		
		
		return labelled;
		
	}
	

	public static BufferedImage bitmapImage(BufferedImage img, int threshold){
		
		int width = img.getWidth();
	    int height = img.getHeight();
	    BufferedImage bitmappedImage=img;
		
		for(int y = 0; y < height; y++){
		      for(int x = 0; x < width; x++){
		        int p = img.getRGB(x,y);

		        int a = (p>>24)&0xff;
		        int r = (p>>16)&0xff;
		        int g = (p>>8)&0xff;
		        int b = p&0xff;

		        //calculate average
		        int avg = (r+g+b)/3;
		        if(avg>threshold){
		        	bitmappedImage.setRGB(x, y, (new Color(255, 255, 255)).getRGB());
		        }
		        else bitmappedImage.setRGB(x, y, (new Color(0, 0, 0)).getRGB());;
		        
		      }
		    }
		
		return bitmappedImage;
		
	}
	
	public static BufferedImage readImage(String filename)throws IOException
	{
		BufferedImage given_image=ImageIO.read(new File(filename));
		return given_image;
	}
	
	
	
	public static BufferedImage getGreyScale(BufferedImage img){
		BufferedImage greyScaleImage= img;
		int width = img.getWidth();
	    int height = img.getHeight();
	
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

	        //replace RGB value with avg
	        p = (a<<24) | (avg<<16) | (avg<<8) | avg;
	        greyScaleImage.setRGB(x, y, p);
	      }
	    }

		
		return greyScaleImage;
	}
	
	
	public static void printArraytofile(String filename,String [][]arry) throws FileNotFoundException{
		int width = arry.length-1;
	    int height = arry[0].length-1;
	    String result="";
		
		for(int y = 0; y < height; y++){
		      for(int x = 0; x < width; x++){
	
		        result+=arry[x][y]+" ";
		        //System.out.printf(avg+" ");
		      }
		    
		      result+="\n";
		    }
		
		try (
			PrintStream out = new PrintStream(new FileOutputStream(filename))) {
		    out.print(result);
		}
		
	}
	
	//"/home/ali/Downloads/Output.jpg"
	public static void writeImage(String filename,String format, BufferedImage img){
		try{
			File f = new File(filename);
		    ImageIO.write(img, format, f);
		    }catch(IOException e){
		      System.out.println(e);
		    }
		
	}
}
