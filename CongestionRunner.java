public class CongestionRunner {

	public static void main(String[] args) {
		
		//INPUT VALUES HERE
		
		double step = 1.0;									//How long each time jump is
		
		int numWaves = 3;									//Number of Waves
		
		int[]wavesizes = new int[numWaves];
		String[]wavetypes = new String[numWaves];
		int[]interWaveSteps = new int[numWaves - 1];
	
		wavesizes[0] = 50;									//Size of Each Wave
		wavesizes[1] = 50;
		wavesizes[2] = 50;
		//wavesizes[3] = 50;
		//wavesizes[4] = 50;
		
		wavetypes[0] = "FPRO";								//Type of Each Wave
		wavetypes[1] = "ATH";
		wavetypes[2] = "FOPEN";
		//wavetypes[3] = "CLY";
		//wavetypes[4] = "MOPEN";
		
		interWaveSteps[0] = 300;							//Time between Waves
		interWaveSteps[1] = 300;
		//interWaveSteps[2] = 300;
		//interWaveSteps[3] = 10;
		
		int swimDistance = 1500;
		int bikeDistance = 40000;
		int runDistance = 10000;
		
		//interWaveSteps[1] = 60;
		
		
		//ACTUAL CODE NOW
		
		
		int roadStart = 0;									//Time road enters use
		int time = 0;										//Tracks total time
		int bikeSumDistance = swimDistance + bikeDistance;
		int runSumDistance = bikeSumDistance + runDistance;
		
		
		int totalsize = 0;
		for(int i = 0; i < numWaves; i++){
			totalsize += wavesizes[i];
		}
		
		double [][] runners = new double[totalsize][2];			//Array of all runners' position and velocity
		Runner[]runnerList = new Runner[totalsize];						//Array of first wave of Runners
		
		int startIndex = 0;												//Keeps track of how many indices in we are
		for(int waveIndex = 0; waveIndex < numWaves; waveIndex++){
			for(int index = startIndex; index < startIndex + wavesizes[waveIndex]; index++) {			//Assigns values for waves
				runnerList[index] = new Runner(wavetypes[waveIndex],step,swimDistance,bikeSumDistance,runSumDistance);
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
						if(runnerList[index].position < runSumDistance){
							if(roadStart == 0){
								if(runnerList[index].position > swimDistance){
									roadStart = time;
								}
							}
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
				if(runnerList[index].position < runSumDistance){
					runnerList[index].timestep();
					runners[index][0] += moveDist[index];
					runners[index][1] = moveDist[index]/step;
					finished = false;
					//System.out.println(runnerList[index].position);
				}
			}
			time += step;
		}
		System.out.println(time);
		for(int index = 0; index < totalsize; index++){
			//System.out.println(runnerList[index].stepDifference());
			System.out.println(runnerList[index].classname + "   " + runnerList[index].stepDifference() * step + "   " + runnerList[index].getStepNumber()*step);
		}
		System.out.println(time-roadStart);
		
	}

}
