import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Memory {
	/**
	 * Memory Instructions:
   	 *	It will consist of 2000 integer entries, 
   	 *		0-999 for the user program, 
   	 *		1000-1999 for system code.
   	 *	It will support two operations:
   	 *		read(address) -  returns the value at the address
   	 *		write(address, data) - writes the data to the address
   	 *	Memory will initialize itself by reading a program file.
	 */
	
	// The address array holds main memory 0-999 for user program, 1000-1999 for system code
	private static int[] address = new int[2000]; 
	private static boolean debug = false;
	
 	public static void main(String[] args) throws FileNotFoundException {
 		if (args.length < 1) {
 			System.out.println("You must enter filename program to run");
 			System.out.println("exit");
 		} else {
 			
 			if (args.length >= 2 && args[1] == "debug") {
 				debug = true;
 			}
 		}
 		loadProgram(args[0]);
 		if (debug) System.out.println("return from loadProgram(filename)");
 		Scanner fetch = new Scanner(System.in);
 		
 		if (debug) {	// read past any non-instruction communications
 			System.out.println("debugging..");
 			while (fetch.hasNext()) { 
 				String line = fetch.nextLine();
 				if (line.contains("fetch")) {
 					break;
 				}
 				System.out.println(line);			
 			}
 				System.out.println("exit"); 		
 				System.out.println("fetch instructions");
 		}
 		// read instructions
 		System.out.println("ready");
 		
 		while (fetch.hasNext()) {
 			String line = fetch.nextLine();
 			
 			if (line.startsWith("read")) {
 				String[] variables = line.split(" ");
 				int index = Integer.valueOf(variables[1]);
 				System.out.println(address[index]);
 			}
 			if (line.startsWith("exit")) break;
 		}
 		
 		if (debug)
 			System.out.println("mem out of fetch loop...");
 		fetch.close();
 		System.exit(0);
	}

 	public static void loadProgram(String filename) {
 		Scanner pgm = null;
		try {
			pgm = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			System.out.println("could not open file " + filename);
			System.exit(2);
		}
		if (debug)
			System.out.println("file opened");
		int counter = 0;
		while (pgm.hasNextLine()) {
 			int i = pgm.nextInt();
 			String line = pgm.nextLine();
 			if (debug) System.out.println(i + " " + line);
 			address[counter] = i;
 			counter++;
 			if (i == 50) break;
 		}
 		if (debug) System.out.println("exit");
 		pgm.close();
 		/** end init */
 	}
}
