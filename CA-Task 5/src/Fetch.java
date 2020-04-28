
public class Fetch {
	public static String instFetch(String pc)
	{
		 int i=ProgramExecuter.binToDec(pc);
		 i++;
		progCount(ProgramExecuter.decToBin(i));
		 return ProgramExecuter.instMem[i];
	}
	public static void progCount(String pc)
	{
		ProgramExecuter.pc=pc;
	}

}
