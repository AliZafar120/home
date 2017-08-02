import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	static ArrayList<ArrayList<Double>> featuresData;
	static ArrayList<Double> outputData;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		readfile("/home/ali/Desktop/saj.txt");
		GradientDescent gr =  new GradientDescent(getTrain_X_Data(), getTest_Y_Data(), 0.000001);
		gr.getConvergencePoint();
		gr.printThetaValues();
	}
	
	public static void readfile(String filename){
		

		BufferedReader br = null;
		FileReader fr = null;

		try {

			fr = new FileReader(filename);
			br = new BufferedReader(fr);

			String currentLine;

			br = new BufferedReader(new FileReader(filename));

			featuresData= new ArrayList<ArrayList<Double>> ();
			outputData= new ArrayList<Double>();
			while ((currentLine = br.readLine()) != null) {
				String[] parts = currentLine.split(",");
				ArrayList<Double> x= new ArrayList<>();
				for(int i=0;i<parts.length-1;i++){
					x.add(Double.parseDouble(parts[i]));
				}
				featuresData.add(x);
				outputData.add(Double.parseDouble(parts[parts.length-1]));
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		
	}
	
	public static double[] getTest_Y_Data(){
		 double[] row = new double[outputData.size()];
		    for (int j = 0; j < row.length; j++) {
		       row[j] = outputData.get(j);  // java 1.4 style
		     }
		return row;
	}
	
	public static double[][] getTrain_X_Data(){
		double[][] array = new double[featuresData.size()][];
		for (int i = 0; i < featuresData.size(); i++) {
		   // ArrayList<Double> row = featuresData.get(i);
		    double[] row = new double[featuresData.get(i).size()];
		    for (int j = 0; j < row.length; j++) {
		       row[j] = featuresData.get(i).get(j);  // java 1.4 style
		     }
		    array[i] = row;
		}
		return array;
	}

}
