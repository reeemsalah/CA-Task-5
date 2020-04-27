
public class Fetch {
	public static String instFetch(String pc)
	{
		 int i=ProgramExecuter.binToDec(pc);
		 i++;
		 pc=progCount(pc);
		 return ProgramExecuter.instMem.get(i);
	}
	public static String progCount(String pc)
	{
		int i=ProgramExecuter.binToDec(pc)+1;
		return ProgramExecuter.decToBin(i);
	}

}
