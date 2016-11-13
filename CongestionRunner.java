public class CongestionRunner {

	public static void main(String[] args) {
		
		int time = 0;										//Tracks total time
		int step = 5;										//How long each time jump is
		
		int wave1size = 50;
		
		double [][] runners = new double[wave1size][2];			//Array of all runners' position and velocity
		Runner[]wave1 = new Runner[wave1size];						//Array of first wave of Runners
		for(int index = 0; index < wave1size; index++) {			//Assigns values for all the things
			wave1[index] = new Runner("FPRO",step);
			runners[index][0] = 0;
			runners[index][1] = 0;
		}
		boolean finished = false;							//Tests if everyone finished yet
		while(!finished){
			finished = true;
			double[]moveDist = new double[wave1size];
			for(int index = 0; index < wave1size; index++) {		//Gets data from every runner
				moveDist[index] = wave1[index].calcTravel(runners);
			}
			for(int index = 0; index < wave1size; index++) {
				if(wave1[index].position < 51500){
					wave1[index].timestep();
					runners[index][0] += moveDist[index];
					runners[index][1] = moveDist[index]/5;
					finished = false;
				}
			}
			time += step;
			
		}
		System.out.println(time);
		for(int index = 0; index < wave1size; index++){
			System.out.println("Time Difference:\t"+ wave1[index].stepDifference() * 5 + "\nActual Time:\t" + wave1[index].getStepNumber()*5);
		}
		
		
	}

}
