import java.io.*;
import java.lang.Runtime;
import java.util.Scanner;

public class CPU_Test {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		if (args.length < 2) {
			System.out.println("too few args");
			System.exit(1);
		}
		ProcessBuilder pb = new ProcessBuilder("java", "Memory_Test", args[0], args[1]);
		Process memory = pb.start();
		
//		Runtime rt = Runtime.getRuntime();
//		Process memory = rt.exec("java Memory_Test");
		
		InputStream is = memory.getInputStream();
		OutputStream os = memory.getOutputStream();
		
		PrintWriter pw = new PrintWriter(os);
		pw.printf("Send it back!%n");
		pw.flush();
			
		Scanner sc = new Scanner(is);
		
		while (sc.hasNext()) {
			String line = sc.nextLine();
			if (line.equals("exit")) {
				break;
			}
				
			System.out.println(line);
		}
		memory.waitFor();
		int exitVal = memory.exitValue();

        System.out.println("Process exited: " + exitVal);
		//sc.close();
		//pw.close();
	}

	private static void CPUexecute(int instruction) {
		InstructionSet doThis = InstructionSet.getByValue(instruction);
		switch (doThis){
			case Load_value: 		//  1	Load the value into the AC
			case Load_addr:			//	2	Load the value at the address into the AC
			case LoadInd_addr:		//	3   Load the value from the address found in the given address into the AC
	    							//		(for example, if LoadInd 500, and 500 contains 100, then load from 100).
			case LoadIdxX_addr:		//	4	Load the value at (address+X) into the AC
	    							//		(for example, if LoadIdxX 500, and X contains 10, then load from 510).
			case LoadIdxY_addr:		//	5	Load the value at (address+Y) into the AC
			case LoadSpX:			//	6	Load from (Sp+X) into the AC (if SP is 990, and X is 1, load from 991).
			case Store_addr:		//	7	Store the value in the AC into the address
			case Get:				//	8	Gets a random int from 1 to 100 into the AC
			case Put_port:			//	9	If port=1, writes AC as an int to the screen
	    							//		If port=2, writes AC as a char to the screen
			case AddX:				//	10	Add the value in X to the AC
			case AddY:				//	11	Add the value in Y to the AC
			case SubX:				//	12	Subtract the value in X from the AC
			case SubY:				//	13	Subtract the value in Y from the AC
			case CopyToX:			//	14	Copy the value in the AC to X
			case CopyFromX:			//	15	Copy the value in X to the AC
			case CopyToY:			//	16	Copy the value in the AC to Y
			case CopyFromY:			//	17	Copy the value in Y to the AC
			case CopyToSp:			//	18	Copy the value in AC to the SP
			case CopyFromSp:		//	19	Copy the value in SP to the AC   
			case Jump_addr:			//	20	Jump to the address
			case JumpIfEqual_addr:	//	21	Jump to the address only if the value in the AC is zero
			case JumpIfNotEqual_addr:	//	22	Jump to the address only if the value in the AC is not zero
			case Call_addr:				//	23	Push return address onto stack, jump to the address
			case Ret:					//	24	Pop return address from the stack, jump to the address
			case IncX:					//	25	Increment the value in X
			case DecX:					//	26	Decrement the value in X
			case Push:					//	27	Push AC onto stack
			case Pop:					//	28	Pop from stack into AC
			case Int:					//	29	Perform system call 
			case IRet:					//	30	Return from system call
			case End:
				System.out.println(doThis.name());
	    	//
		}
			
	}
}
