
public class Fetch {
	public static String instFetch(String pc)
	{
		 int i=ProgramExecuter.binToDec(pc);
		 if(i!=0)
			 i=(i/4)+1;
		 pc=progCount(pc);
		 return ProgramExecuter.instMem.get(i);
	}
	public static String progCount(String pc)
	{
		int i=ProgramExecuter.binToDec(pc)+4;
		return ProgramExecuter.decToBin(i);
	}

}
