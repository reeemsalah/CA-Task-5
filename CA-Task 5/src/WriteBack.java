
public class WriteBack {
	public static String writeData;

	public static void writeBack(String ALUres, String readData, char memToReg, char regDst) 
	{
		int index =ProgramExecuter.binToDec(ALUres);
		int rd=ProgramExecuter.binToDec(Decode.writeData);
		ProgramExecuter.registerFile.write(rd, readData);
	}
}
