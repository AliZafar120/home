package Lab_1;
import java.util.ArrayList;

public class Point  {
	public int x , y,value,group;
	public boolean visited=false;
	public ArrayList<Point> neighbour;	
	public Point(int x, int y,int val) {
		this.x = x;
		this.y = y;
		this.value=val;
		visited=false;
		neighbour=new ArrayList<Point>();
	}
	
	public void setNegighbours(Point [][] allNeighbours) {
		if(this.x-1>=0) {//left side
			if(this.y-1>=0) {//up
				//   ***-
				//   ****-
				neighbour.add(allNeighbours[x-1][y-1]);
			}
			neighbour.add(allNeighbours[x-1][y]);//mid
			if(this.y+1<allNeighbours[0].length) {//down
				//   ***-
				//   **-
				neighbour.add(allNeighbours[x-1][y+1]);
			}
			
			
		}
		if(this.x+1<allNeighbours.length) {//right
			if(this.y-1>=0) {//up
				//   ***-
				//   ****-
				neighbour.add(allNeighbours[x+1][y-1]);
			}
			neighbour.add(allNeighbours[x+1][y]);//mid
			if(this.y+1<allNeighbours[0].length) {//down
				//   ***-
				//   **-
				neighbour.add(allNeighbours[x+1][y+1]);
			}
			
		}
		if(this.y+1<allNeighbours[0].length) {
			neighbour.add(allNeighbours[x][y+1]);
		}
		if(this.y-1>=0) {
			neighbour.add(allNeighbours[x][y-1]);
		}
		
	}
	
	public String toString() {
        return this.x+" "+this.y+" ";
    } 
	
	
}
