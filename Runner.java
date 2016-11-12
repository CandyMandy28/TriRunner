import java.util.Random;

public class Runner {

	String classname;
	double swimspeed;
	double runspeed;
	double bikespeed;
	double position;
	double t1time;
	double t2time;
	
	Random rand =  new Random();
	
	public Runner(String classname){
		
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
		
		double biketime = rand.nextGaussian()*d6+d5;
		bikespeed = 40000 / biketime;
		
		t2time = rand.nextGaussian()*d8+d7;
		if(t2time < 0){
			t2time = 0;
		}
		
		double runtime = rand.nextGaussian()*d10+d9;
		runspeed = 10000 / runtime;
	}
	
	public void printStats(){
		System.out.println(classname + "" );
	}
	
}