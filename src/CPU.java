import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class CPU {
	/* Registers */
	private static int PC, SP, IR, AC, X, Y;
	private static int PORT = 1;		// read as integer following the PUT_PORT command (9)  ??
	private static int operand;
	private static int instr;
	
	private static boolean debug = false;
	private static String memDebug = "run";
	
	public static void main(String[] args) throws IOException, InterruptedException {
		if (args.length < 2) {
			System.out.println("You must specify filename and timer");
			System.exit(1);
		} else if (args.length == 3) {
			memDebug = args[2];
			if (memDebug.equals("debug")) debug = true;
		} 
		
		if (debug)	System.out.println("value of debug is " + debug +  " ...memDebug is " + memDebug);
		if (debug)	System.out.println("args: " + args[0] + " " + args[1] + " " + args[2]);
			
		ProcessBuilder pb = new ProcessBuilder("java", "Memory", args[0], memDebug);
		Process memory = pb.start();
//		...the 2 lines above are equivalent to:				
//		Runtime rt = Runtime.getRuntime();
//		Process memory = rt.exec("java Memory " + arg[0] + memDebug);
		
		InputStream is = memory.getInputStream();
		OutputStream os = memory.getOutputStream();
	    PrintWriter pw = new PrintWriter(os);
	    // this initial send is required to clear a scanner block...
	    pw.printf("%s%n", args[0]);
		pw.flush();

	    Scanner sc = new Scanner(is);
	  // print program as it is read into memory  
	    
		while (sc.hasNext() ) {			
			String line = sc.nextLine();
			
			if (line.contains("ready")) {
				if (debug) System.out.println("...Line contains ready");
				break;
			}	
			System.out.println(line);
		}
		if (debug) System.out.println("end of first while loop");
		
		if (debug) 
			System.out.println("now get the instructions...");
		// fetch instructions one at a time ..
		PC = 0;
		pw.printf( "read %d%n", PC++);
		pw.flush();
		
		String line = "";
		String lastline = "";
		// execute is on last line, rather than current to allow for input of data...???
		while (sc.hasNext()) {			
			lastline = line;
			line = sc.nextLine();
			// if line has no comment, assume operand?
//			if (lastline.equals("9")) PORT = Integer.valueOf(line);      //  ???????  ask!
//			else
//				if (!lastline.isEmpty()) {
//					IR = Integer.valueOf(lastline);
//					CPUExecute();
//				}
//				
			System.out.println(line);
			
			if (line.contains("50")) {				
				break;
			}
			// ask for next instruction
			pw.printf( "read %d%n", PC++);			  
			pw.flush();
			
		}
		pw.printf( "exit%n");
		pw.flush();							
		if (debug)	System.out.println("end of instruction fetch");

		// boolean finished = iostat.waitFor(100, TimeUnit.MILLISECONDS);
/**
 * The Process API offers the destroy() and destroyForcibly() methods, 
 * which apply the appropriate platform specific process stopping procedures. 
 * In my experience a utility method that kills a subprocess by its process
 *  ID (PID) is also a good idea.
 */
			memory.waitFor();
			int exitVal = memory.exitValue();
			System.out.println("Memory exit value: " + exitVal);
	}

	private static void CPUExecute() {
		InstructionSet doThis = InstructionSet.getByValue(IR);
		switch (doThis){
			case Load_value: 		//  1	Load the value into the AC
				break;
			case Load_addr:			//	2	Load the value at the address into the AC
				break;
			case LoadInd_addr:		//	3   Load the value from the address found in the given address into the AC	    							//		(for example, if LoadInd 500, and 500 contains 100, then load from 100).
				break;
			case LoadIdxX_addr:		//	4	Load the value at (address+X) into the AC
	    							//		(for example, if LoadIdxX 500, and X contains 10, then load from 510).
				break;
			case LoadIdxY_addr:		//	5	Load the value at (address+Y) into the AC
				break;
			case LoadSpX:			//	6	Load from (Sp+X) into the AC (if SP is 990, and X is 1, load from 991)
				break;
			case Store_addr:		//	7	Store the value in the AC into the address
				break;
			case Get:				//	8	Gets a random int from 1 to 100 into the AC
				Random rand = new Random();
				AC = rand.nextInt(100) + 1;
				//100 is the maximum and the 1 is our minimum
				break;
			case Put_port:			//	9	If port=1, writes AC as an int to the screen
	    							//		If port=2, writes AC as a char to the screen
				//if (PORT == 1) {
					System.out.println(AC);
				//}
				break;
			case AddX:				//	10	Add the value in X to the AC
				AC = AC + X;
				break;
			case AddY:				//	11	Add the value in Y to the AC
				AC = AC + Y;
				break;
			case SubX:				//	12	Subtract the value in X from the AC
				break;
			case SubY:				//	13	Subtract the value in Y from the AC
				break;
			case CopyToX:			//	14	Copy the value in the AC to X
				X = AC;
				break;
			case CopyFromX:			//	15	Copy the value in X to the AC
				AC = X;
				break;
			case CopyToY:			//	16	Copy the value in the AC to Y
				Y = AC;
				break;
			case CopyFromY:			//	17	Copy the value in Y to the AC
				AC = Y;
				break;
			case CopyToSp:			//	18	Copy the value in AC to the SP
				break;
			case CopyFromSp:		//	19	Copy the value in SP to the AC   
				break;
			case Jump_addr:			//	20	Jump to the address
				break;
			case JumpIfEqual_addr:	//	21	Jump to the address only if the value in the AC is zero
				break;
			case JumpIfNotEqual_addr:	//	22	Jump to the address only if the value in the AC is not zero
				break;
			case Call_addr:				//	23	Push return address onto stack, jump to the address
				break;
			case Ret:					//	24	Pop return address from the stack, jump to the address
				break;
			case IncX:					//	25	Increment the value in X
				break;
			case DecX:					//	26	Decrement the value in X
				break;
			case Push:					//	27	Push AC onto stack
				break;
			case Pop:					//	28	Pop from stack into AC
				break;
			case Int:					//	29	Perform system call 
				break;
			case IRet:					//	30	Return from system call
				break;
			case End:
				break;
		}
				System.out.println(doThis.name());
	    	//
	}
}
