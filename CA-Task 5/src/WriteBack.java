
public class WriteBack {
	public static String writeData;

	public static void writeBack(String ALUres, String readData, char memToReg, char regDst) 
	{
		int rd;
		if(regDst=='0')
			rd=ProgramExecuter.binToDec(Decode.rsNo);
		else
			rd=ProgramExecuter.binToDec(ALUres);
		System.out.println("rd: "+rd);
		ProgramExecuter.registerFile.write(rd, ALUres);
		ProgramExecuter.registerFile.readOne(rd);
		
	}
}
