public class MemoryAccess {
	public static String ALUres;
	public static String readData2;
	public static void memAccess(String ALUres, String readData2, String signExtend, char zeroFlag,
			String branchAddressRes, char memWrite, char memRead, char branch)

	{
		MemoryAccess.ALUres=ALUres;
		MemoryAccess.readData2=readData2;
		if(memRead=='1'&&memWrite=='1')
		{
			int index=ProgramExecuter.binToDec(ALUres);
			ProgramExecuter.dataMem[index]=readData2;
		}
	}
}
