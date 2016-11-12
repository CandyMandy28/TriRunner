import java.util.Random;

public class Runner {

	private String classname;
	private double swimspeed;
	private double runspeed;
	private double bikespeed;
	public double position;
	private double t1time;
	private double t2time;
	public double nextTravel;	//How much the runner will travel in the next step
	private int t1count;		//How many steps the runner stays at transition 1
	private int t2count;		//How many steps the runner stays at transition 2
	
	
	
	
	private double lloDensityRun = 1.6;	//What Density of people/m do we start getting slowdown (assuming 20 foot wide road)
	private double lloDensitySwim = 0.8;
	private double lloDensityBike = 0.8;
	private double uloDensityRun = 6;		//What Density of people/m do can we not traverse (assuming 20 foot wide road)) 
	private double uloDensitySwim = 3;
	private double uloDensityBike = 3;
	private int step;			//Length of time between updates
	
	
	private int stepNumber;
	private int expectedStepNumber;
	
	//Need to add timers and shit in order to measure the time spent and the distance lost in congestion yeah...
	
	
	
	Random rand =  new Random();
	
	public Runner(String classname, int steplength){	//Initializes all the speed variables and transition times randomly based on class average and standard deviation
		
		double d1, d2, d3, d4, d5, d6, d7, d8, d9, d10;
		
		if(classname.equals("ATH")){
			d1 = 1404.9375;
			d2 = 210.132092;
			d3 = 697.96875;
			d4 = 215.3605239;
			d5 = 6539.03125;
			d6 = 1143.254168;
			d7 = 270.875;
			d8 = 110.5202894;
			d9 = 4645.536875;
			d10 = 823.6366583;
		}
		else if(classname.equals("CLY")){
			d1 = 1537.616667;
			d2 = 228.0364073;
			d3 = 482.6666667;
			d4 = 190.4020016;
			d5 = 5296.25;
			d6 = 766.3613296;
			d7 = 208.05;
			d8 = 126.4455647;
			d9 = 4280.767167;
			d10 = 817.0947949;
		}
		else if(classname.equals("FOPEN")){
			d1 = 1349.106904;
			d2 = 201.2769749;
			d3 = 601.8674833;
			d4 = 206.066424;
			d5 = 5897.638085;
			d6 = 892.6504087;
			d7 = 222.045657;
			d8 = 122.5521167;
			d9 = 3741.610457;
			d10 = 704.0984312;
		}
		else if(classname.equals("FPREMIER")){
			d1 = 1021.777778;
			d2 = 81.42928463;
			d3 = 297.1111111;
			d4 = 37.01784688;
			d5 = 4576.222222;
			d6 = 268.4895106;
			d7 = 86.33333333;
			d8 = 27.95432783;
			d9 = 2647.816111;
			d10 = 218.4670151;
		}
		else if(classname.equals("FPRO")){
			d1 = 778.1666667;
			d2 = 19.59946144;
			d3 = 218.1666667;
			d4 = 9.06305075;
			d5 = 4058;
			d6 = 133.0776716;
			d7 = 54.83333333;
			d8 = 8.091490729;
			d9 = 2298.666667;
			d10 = 108.6549073;
		}
		else if(classname.equals("MOPEN")){
			d1 = 1400.34653;
			d2 = 256.449216;
			d3 = 457.5328365;
			d4 = 180.2161519;
			d5 = 5090.950163;
			d6 = 699.3881369;
			d7 = 180.4415463;
			d8 = 90.73903324;
			d9 = 3559.92463;
			d10 = 694.8203805;
		}
		else if(classname.equals("MPREMIER")){
			d1 = 1000.306122;
			d2 = 84.67662475;
			d3 = 263.755102;
			d4 = 33.19609678;
			d5 = 4117.734694;
			d6 = 279.7328008;
			d7 = 75.32653061;
			d8 = 19.16351524;
			d9 = 2384.832857;
			d10 = 209.4799308;
		}
		else{
			d1 = 788.4285714;
			d2 = 67.8293176;
			d3 = 196.1428571;
			d4 = 18.63505584;
			d5 = 3730.428571;
			d6 = 162.3451853;
			d7 = 51.42857143;
			d8 = 4.92391084;
			d9 = 2119.714286;
			d10 = 103.3022117;
		}
		
		double swimtime = rand.nextGaussian()*d2+d1;
		swimspeed = 1500 / swimtime;
		
		t1time = rand.nextGaussian()*d4+d3;
		if(t1time < 0){
			t1time = 0;
		}
		t1count = (int)Math.round(t1time/step);
		
		double biketime = rand.nextGaussian()*d6+d5;
		bikespeed = 40000 / biketime;
		
		t2time = rand.nextGaussian()*d8+d7;
		if(t2time < 0){
			t2time = 0;
		}
		t2count = (int)Math.round(t2time/step);
		
		double runtime = rand.nextGaussian()*d10+d9;
		runspeed = 10000 / runtime;
		step = steplength;
		
	}
	
	public void printStats(){	//Prints the speeds, class, and position
		System.out.println(classname + " " + swimspeed + " " + bikespeed + " " + runspeed + " " + position);
	}

	public double calcTravel(double[][]runners){	//Calculate how far this will move in the next step, stores and returns it. If at a transition, remains stationary but decreases number of the step count by 1.
		double travelDistance = 0;
		if(position < 1500){
			double speed = Math.min(swimspeed,maxCongestionSpeed(runners));
			travelDistance = Math.min(step*speed, 1500-position);
		}
		else if(1500 < position && position < 41500){
			double speed = Math.min(bikespeed,maxCongestionSpeed(runners));
			travelDistance = Math.min(step*speed, 41500-position);
		}
		else if(41500 < position && position < 51500){
			double speed = Math.min(runspeed,maxCongestionSpeed(runners));
			travelDistance = Math.min(step*speed, 51500-position);
		}
		else if(position == 1500){
			if(t1count > 0){
				t1count--;
				travelDistance = 0;
			}
			else{
				double speed = Math.min(bikespeed,maxCongestionSpeed(runners));
				travelDistance = Math.min(step*speed, 41500-position);
			}
		}
		else if(position == 41500){
			if(t2count > 0){
				t2count--;
				travelDistance = 0;
			}
			else{
				double speed = Math.min(runspeed,maxCongestionSpeed(runners));
				travelDistance = Math.min(step*speed, 51500-position);
			}
		}
		return travelDistance;
	}
	
	public void timestep(){		//Advances the position of the runner
		position += nextTravel;
		stepNumber++;
	}
	
	private double maxCongestionSpeed(double[][]runners){	//calculates the maximum speed a runner can travel given the congestion, relates to average running speed of those in front of them.
		double[]temp = runnerDensitySpeed(runners);
		double density = temp[0];
		double avgvelocity = temp[1];
		double maxSpeed = 100;
		if(position > 41500 && position < 51500){
			maxSpeed = avgvelocity + 3.9;
			if(density < uloDensityRun && density > lloDensityRun){
				maxSpeed = avgvelocity + 3.9 * Math.pow((uloDensityRun-density)/(uloDensityRun-lloDensityRun),2);
			}
			else if (density >= uloDensityRun){
				maxSpeed = avgvelocity;
			}
		}
		else if(position > 1500 && position < 41500){
			maxSpeed = avgvelocity + 1.7;
			if(density < uloDensityRun && density > lloDensityRun){
				maxSpeed = avgvelocity + 1.7 * Math.pow((uloDensityRun-density)/(uloDensityRun-lloDensityRun),2);
			}
			else if (density >= uloDensityRun){
				maxSpeed = avgvelocity;
			}
		}
		else if(position > 41500 && position < 51500){
			maxSpeed = avgvelocity + 9;
			if(density < uloDensityRun && density > lloDensityRun){
				maxSpeed = avgvelocity + 9 * Math.pow((uloDensityRun-density)/(uloDensityRun-lloDensityRun),2);
			}
			else if (density >= uloDensityRun){
				maxSpeed = avgvelocity;
			}
		}
		return maxSpeed;
	}
	
	public double[] runnerDensitySpeed(double[][]runners){	//returns the density of runners and their average speed in the step seconds in front of the runner. Runners lists all runners' positions and run speeds.
		double scanningDistance = 0;
		if(position < 1500){
			scanningDistance = Math.min(step * swimspeed, 1500-position);	
		}
		else if(position > 1500 && position < 41500){
			scanningDistance = Math.min(step * bikespeed, 1500-position);	
		}
		else if(position > 41500 && position < 541500){
			scanningDistance = Math.min(step * runspeed, 1500-position);	
		}
		int runnerCounter = 0;
		double runnerSpeed = 0;
		for(int counter = 0; counter < runners.length; counter++){
			if (runners[counter][0] > position && runners[counter][0] < position + scanningDistance){
				runnerCounter ++;
				runnerSpeed += runners[counter][1];
			}
		}
		double density = runnerCounter/scanningDistance;
		double averageSpeed = 10000000;
		if(runnerCounter != 0){
			averageSpeed = runnerSpeed/runnerCounter;
		}
		double[]returnThis = new double[2];
		returnThis[0] = density;
		returnThis[1] = averageSpeed;
		return returnThis;
	}
}
