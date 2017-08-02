
public class GradientDescent {

	private double learning_rate_alpha;
	private int number_of_features;
	private int number_of_train_data;
	private double [][] train_x_data;//feature data
	private double [] train_y_data;//actual outputs
	private double []theta;
	private int iterations=0;
	

	public GradientDescent(double[][] train_X_data,double []train_Y_data,double learning_rate) {
		// TODO Auto-generated constructor stub
		train_x_data=train_X_data;
		train_y_data=train_Y_data;
		number_of_features=train_x_data[0].length;
		number_of_train_data=train_x_data.length;
		learning_rate_alpha=learning_rate;
		theta = new double[number_of_features+1];
	}
	
	public void getConvergencePoint(){
		boolean converged=false;
		double []thetatemp=new double[number_of_features+1];
		while(!converged){
		 final float acceptederror = 0.0000001f;
		 for(int i=0;i<number_of_features+1;i++)
	     thetatemp[i] = theta[i] - learning_rate_alpha*this.gradientDescent(i-1);
	        
		 converged = true;
	     for (int i = 0; i < theta.length; i++) {
	          if(Math.abs(theta[i] - thetatemp[i]) > acceptederror)  converged = false;
	            theta[i] = thetatemp[i];
	     }		
		 iterations++;
		}
	}
	
	public void printThetaValues(){
		for(int i=0;i<theta.length;i++){
			System.out.println("Theta"+i+" :"+theta[i]);
		}
		System.out.println("Number of Iterations "+iterations);
	}
	
	private double gradientDescent(int feature) {
		// TODO Auto-generated method stub
      double sum=0;
    	//sum of difference between{ (difference between estimated cost and actual cost)* feature}
       for (int i = 0; i <number_of_train_data; i++) {
             if(feature==-1) sum +=  (this.estimateCost(train_x_data[i])-train_y_data[i])*1;
             else sum +=  (this.estimateCost(train_x_data[i])-train_y_data[i])*train_x_data[i][feature];
       }
       sum=sum/number_of_train_data;
      return sum;
	}

	private double estimateCost(double []row){
		double estimated_cost=theta[0];
		for(int i=1;i<theta.length;i++){
			estimated_cost+=row[i-1]*theta[i];
		}
		//represents hθ(xi)=θ+θ1X1+θ2X2
		return estimated_cost;
	}
}
