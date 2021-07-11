/*
 * runs a number of collatz sequences either to completion or to a user-specified end point
 */
package collatz;
public class main {
	public static void MultiCollatz (int min, int max, int maxSteps) {
		/* 
		 * run multiple collatz sequences for a range of starting values, summarizing the results
		 */
		for (int i = min; i <= max; i++) {
			CollatzSeq(i, maxSteps, false);
		}
	}
	
	public static void CollatzSeq(int startNum, int maxSteps, boolean show) {
		/* 
		 * calculate and output the collatz sequence parameters based on starting value and maximum number of steps   
		 */
		
		//declare/define local variables
		int initValue = startNum;
		int maxValue = startNum;
		int steps = 0;
		
		//until end value is 1, or max steps reached...
		while (startNum > 1 && steps < maxSteps) {
			if (show == true) {//output number if allowed
				System.out.println(Integer.toString(startNum));
			}
			
			//calculate next number in sequence
			startNum = calcCollatz(startNum);
			maxValue = Math.max(startNum, maxValue);
			steps++;
		}
		
		if (show == true) {
			System.out.println(Integer.toString(startNum));
		}
		
		//output end point values
		if (show == true) {
			System.out.println("");
			System.out.print("Start,");
			System.out.print("Maximum,");
			System.out.println("Stopping_Time,");
			System.out.print(Integer.toString(initValue));
			System.out.print(",");
			System.out.print(Integer.toString(maxValue));
			System.out.print(",");
			System.out.print(Integer.toString(steps));
			System.out.println(",");
		} else {
			System.out.print(Integer.toString(initValue));
			System.out.print(",");
			System.out.print(Integer.toString(maxValue));
			System.out.print(",");
			System.out.print(Integer.toString(steps));
			System.out.println(",");
		}
	}
	
	public static int calcCollatz(int a) {
		/* 
		 * calculate the next steps of a collatz sequence based on the number given   
		 */
		if (a % 2 == 0) {//if number is even...
			return (a / 2);
		} else {//if number is odd...
			return ((3 * a) + 1);
		}
	}
	
	public static void main(String[] args) {
		//declare variables
		int startNum;
		int maxSteps;
		boolean multiCollatz = true; //set to either true or false depending on need for single sequence or multiple summaries
		int min;
		int max;
		
		//assign values
		if (args.length > 0) {//if first string argument is valid as integer, use it for startNum, otherwise pick a number between 2 and 20000
			try {
				startNum = Integer.parseInt(args[0]);
			} catch(NumberFormatException e) {
				startNum = (int) (2 + Math.random() * 19999);
			}
			if (args.length > 1) {//if second string argument is valid as integer, use it for maxSteps, otherwise set to MAX_INT
				try {
					maxSteps = Integer.parseInt(args[1]);
				} catch(NumberFormatException e) {
					maxSteps = Integer.MAX_VALUE;
				}
			} else {
				maxSteps = Integer.MAX_VALUE;
			}
		} else {
			startNum = (int) (2 + Math.random() * 19999);
			maxSteps = Integer.MAX_VALUE;
		}
		if (maxSteps <= 0) {//if maxSteps is zero or less, set it to the highest value possible
			maxSteps = Integer.MAX_VALUE;
		}
		
		//start collatz sequence
		if (multiCollatz == true) {//if multicollatz is true, summarize multiple sequences, otherwise output single summary and sequence
			System.out.println("MultiCollatz() test");
			if (args.length > 3) {
				try {//if fourth string argument is valid as integer, use it for min, otherwise use a random number from 2 to 10
					min = Integer.parseInt(args[3]);
				} catch(NumberFormatException e) {
					min = (int) (2 + Math.random() * 9);
					min = 1;
				}
				if (args.length > 4) {
					try {//if fifth string argument is valid as integer, use it for min, otherwise use a random number from min+2 to min+10
						max = Integer.parseInt(args[4]);
					} catch(NumberFormatException e) {
						max = (int) (min + 2 + Math.random() * 9);
						max = 100;
					}
				} else {
					max = (int) (min + 2 + Math.random() * 9);
					max = 100;
				}
			} else {
				min = (int) (2 + Math.random() * 9);
				min = 1;
				max = (int) (min + 2 + Math.random() * 9);
				max = 100;
			}
			System.out.print("Start,");
			System.out.print("Maximum,");
			System.out.println("Stopping_Time,");
			MultiCollatz (min, max, maxSteps);
		} else {
			System.out.println("CollatzSeq() test");
			System.out.println("");
			CollatzSeq(startNum, maxSteps, true);
		}
		
		//done
	}
}