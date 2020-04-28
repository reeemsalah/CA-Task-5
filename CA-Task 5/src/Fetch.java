
public class Fetch {
	public static void instFetch(String pc) {
		int i = ProgramExecuter.binToDec(pc);
		String instruction = ProgramExecuter.instMem[i];
		i++;
		progCount(ProgramExecuter.decToBin(i));
		Decode.instDecode(instruction, ProgramExecuter.pc);
		
	}

	public static void progCount(String pc) {
		ProgramExecuter.pc = pc;
	}

}
