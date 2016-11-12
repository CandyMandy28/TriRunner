import java.util.Scanner;

public class CongestionRunner {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Type in the number of participants: (positive integer)");
		int participants = in.nextInt();
		System.out.println("Type in the updating time: (positive integer)");
		int steplength = in.nextInt();
		System.out.println("Type in the Group Name: (ATH, CLY, FOPEN, FPREMIER, FPRO, MOPEN, MPREMIER, and MPRO)");
		String group = in.next();
		Runner sample = new Runner(group, steplength);
		System.out.println("Expected Swim speed without congestion:\t" + sample.getSwimSpeed());
		System.out.println("Expected Bike speed without congestion:\t" + sample.getBikeSpeed());
		System.out.println("Expected Run  speed without congestion:\t" + sample.getRunSpeed());
		
	}

}
