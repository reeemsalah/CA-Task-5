
public class WriteBack {
	public static String writeData;

	public static void writeBack(String ALUres, String readData, char memToReg, char regDst) 
	{
		int rd;
		if(regDst=='1')
			rd=ProgramExecuter.binToDec(Decode.rd);
		else
			rd=ProgramExecuter.binToDec(ALUres);
		if(Decode.memWrite=='1')
		{
			System.out.println("Writing into data mem........");
			int i=ProgramExecuter.binToDec(ALUres);
			ProgramExecuter.dataMem[i]=readData;
			System.out.println(ProgramExecuter.dataMem[i]);
		}
		if(ProgramExecuter.registerFile.write)	{
		System.out.println("rd: "+rd);
		ProgramExecuter.registerFile.write(rd, ALUres);
		ProgramExecuter.registerFile.readOne(rd);
		}
		
	}
}
