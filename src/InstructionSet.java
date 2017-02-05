import java.util.HashMap;
import java.util.Map;

public enum InstructionSet {
    Load_value(1), 			//  1	Load the value into the AC
    Load_addr(2),			//	2	Load the value at the address into the AC
    LoadInd_addr(3),		//	3   Load the value from the address found in the given address into the AC
    						//		(for example, if LoadInd 500, and 500 contains 100, then load from 100).
    LoadIdxX_addr(4),		//	4	Load the value at (address+X) into the AC
    						//		(for example, if LoadIdxX 500, and X contains 10, then load from 510).
    LoadIdxY_addr(5),		//	5	Load the value at (address+Y) into the AC
    LoadSpX(6),				//	6	Load from (Sp+X) into the AC (if SP is 990, and X is 1, load from 991).
    Store_addr(7),			//	7	Store the value in the AC into the address
    Get(8),					//	8	Gets a random int from 1 to 100 into the AC
    Put_port(9),			//	9	If port=1, writes AC as an int to the screen
    						//		If port=2, writes AC as a char to the screen
    AddX(10),				//	10	Add the value in X to the AC
    AddY(11),				//	11	Add the value in Y to the AC
    SubX(12),				//	12	Subtract the value in X from the AC
    SubY(13),				//	13	Subtract the value in Y from the AC
    CopyToX(14),			//	14	Copy the value in the AC to X
    CopyFromX(15),			//	15	Copy the value in X to the AC
    CopyToY(16),			//	16	Copy the value in the AC to Y
    CopyFromY(17),			//	17	Copy the value in Y to the AC
    CopyToSp(18),			//	18	Copy the value in AC to the SP
    CopyFromSp(19),			//	19	Copy the value in SP to the AC   
    Jump_addr(20),			//	20	Jump to the address
    JumpIfEqual_addr(21),	//	21	Jump to the address only if the value in the AC is zero
    JumpIfNotEqual_addr(22),//	22	Jump to the address only if the value in the AC is not zero
    Call_addr(23),			//	23	Push return address onto stack, jump to the address
    Ret(24),				//	24	Pop return address from the stack, jump to the address
    IncX(25),				//	25	Increment the value in X
    DecX(26),				//	26	Decrement the value in X
    Push(27),				//	27	Push AC onto stack
    Pop(28),				//	28	Pop from stack into AC
    Int(29),				//	29	Perform system call 
    IRet(30),				//	30	Return from system call
    End(50);				//	50	End execution
   
	public final int value;
	
	private static final Map<Integer, InstructionSet> typesByValue = new HashMap<Integer, InstructionSet>();

    static {
        for (InstructionSet type : InstructionSet.values()) {
            typesByValue.put(type.value, type);
        }
    }

    InstructionSet(int value){
    	this.value = value;
    }

    public static InstructionSet getByValue(int value) {  	
    	return typesByValue.get(value);
    }
}


