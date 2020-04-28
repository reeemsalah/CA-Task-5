public class MemoryAccess {

	public static void memAccess(String ALUres, String readData2, String signExtend, char zeroFlag,
			String branchAddressRes, char memWrite, char memRead, char branch)

	{
		
		if(Decode.memToReg=='1')
			ProgramExecuter.registerFile.write(ProgramExecuter.binToDec(Decode.writeData), ALUres);
		else
			ProgramExecuter.registerFile.write(ProgramExecuter.binToDec(Decode.writeData), readData2);

		if(memRead=='1')
		{
			ProgramExecuter.registerFile.write(ProgramExecuter.binToDec(Decode.readData1), ProgramExecuter.dataMem[ProgramExecuter.binToDec(signExtend)]);

		}
		
		
	}
}
