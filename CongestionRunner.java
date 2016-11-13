public class CongestionRunner {

	public static void main(String[] args) {
		
		//INPUT VALUES HERE
		
		int time = 0;										//Tracks total time
		double step = 1.0;									//How long each time jump is
		
		int numWaves = 2;									//Number of Waves
		
		int[]wavesizes = new int[numWaves];
		String[]wavetypes = new String[numWaves];
		int[]interWaveSteps = new int[numWaves - 1];
	
		wavesizes[0] = 50;									//Size of Each Wave
		wavesizes[1] = 50;
		//wavesizes[2] = 50;
		
		wavetypes[0] = "ATH";								//Type of Each Wave
		wavetypes[1] = "MPRO";
		//wavetypes[2] = "MPRO";
		
		
		interWaveSteps[0] = 300;							//Time between Waves
		//interWaveSteps[1] = 60;
		
		
		//ACTUAL CODE NOW
		int totalsize = 0;
		for(int i = 0; i < numWaves; i++){
			totalsize += wavesizes[i];
		}
		
		double [][] runners = new double[totalsize][2];			//Array of all runners' position and velocity
		Runner[]runnerList = new Runner[totalsize];						//Array of first wave of Runners
		
		int startIndex = 0;												//Keeps track of how many indices in we are
		for(int waveIndex = 0; waveIndex < numWaves; waveIndex++){
			for(int index = startIndex; index < startIndex + wavesizes[waveIndex]; index++) {			//Assigns values for waves
				runnerList[index] = new Runner(wavetypes[waveIndex],step);
				runners[index][0] = 0;
				runners[index][1] = 0;
			}
			
			
			if(waveIndex != numWaves - 1){
				for(int intercounter = interWaveSteps[waveIndex]; intercounter >= 0; intercounter--){		//runs waves for intersteps time
					double[]moveDist = new double[startIndex + wavesizes[waveIndex]];
					for(int index = 0; index < startIndex + wavesizes[waveIndex]; index++) {		//Gets data from every runner
						moveDist[index] = runnerList[index].calcTravel(runners);
					}
					for(int index = 0; index < startIndex + wavesizes[waveIndex]; index++) {
						if(runnerList[index].position < 51500){
							runnerList[index].timestep();
							runners[index][0] += moveDist[index];
							runners[index][1] = moveDist[index]/5;
						}
					}
					time += step;
				}
				startIndex += wavesizes[waveIndex];
			}
			
			
			
			
		}
		
		
		boolean finished = false;							//Tests if everyone finished yet
		while(!finished){
			finished = true;
			double[]moveDist = new double[totalsize];
			for(int index = 0; index < totalsize; index++) {		//Gets data from every runner
				moveDist[index] = runnerList[index].calcTravel(runners);
			}
			for(int index = 0; index < totalsize; index++) {
				if(runnerList[index].position < 51500){
					runnerList[index].timestep();
					runners[index][0] += moveDist[index];
					runners[index][1] = moveDist[index]/step;
					finished = false;
				}
			}
			time += step;
			
		}
		System.out.println(time);
		for(int index = 0; index < totalsize; index++){
			System.out.println("Time Difference:\t"+ runnerList[index].stepDifference() * step + "\nActual Time:\t" + runnerList[index].getStepNumber()*step);
		}
		
		
	}

}
