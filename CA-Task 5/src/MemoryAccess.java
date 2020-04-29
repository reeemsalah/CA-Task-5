public class MemoryAccess {
	public static String ALUres;
	public static String readData2;
	public static String writeData;
	public static void memAccess(String ALUres, String readData2, String signExtend, char zeroFlag,
			String branchAddressRes, char memWrite, char memRead, char branch)

	{
		
		if(Decode.memToReg=='1')
			writeData=ALUres;
		else {
			if(memRead=='1')
			{
				int rs=ProgramExecuter.binToDec(ALUres);
				writeData=ProgramExecuter.dataMem[rs];
			}
			
		}

		WriteBack.writeBack(ALUres, writeData, Decode.memToReg, Decode.regDst);
		
	
		
		
	}
}
