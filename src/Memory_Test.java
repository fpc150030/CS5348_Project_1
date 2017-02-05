
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Memory_Test {
	/**
	 * Test round trip communications...
	 * @throws FileNotFoundException 
	 */
 	public static void main(String[] args) throws FileNotFoundException {
 		if (args.length < 2) {
 			System.out.println("mem not enough args");
 			System.out.println("exit");
 		} 
 			System.out.println("mem " + args[0]);
 	
 		Scanner pgm = new Scanner(new File(args[0]));
 		while (pgm.hasNextLine()) {
 			System.out.println(pgm.nextLine());
 		}
 		System.out.println("exit");
 		// ...
 		Scanner sc = new Scanner(System.in);
 		//System.out.println("" + sc.hasNextLine());
 		String line = null;
 		//System.out.println("" + sc.hasNextLine());
 		System.out.println("okay");
 	    if (sc.hasNextLine())
 	         line = sc.nextLine(); 
 	  


 	     
 		System.out.println("okay");
 	
 		
 			System.out.println(line);
 		
 		//sc.close();
	}

 	
}
