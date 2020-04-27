
public class Decode {
		public static char  regDst;
		public static char  ALUSrc;
		public static char  regWrite;
		public static char  memRead;
		public static char  memWrite;
		public static char  branch;
		public static char  memToReg;
		public static char PCSrc;
		public static String ALUOp;
		public static String readData1;
		public static String readData2;
		public static String funct;
		public static String signExtend;
		public static String rd;

	public static void instDecode(String inst, String newPC)
	{
		System.out.println("new PC: "+newPC);
		decode(inst,newPC);
		
	}
	public static void decode(String s,String newPC) {
		String opCode = s.substring(0, 6);
		int opCodeInt = ProgramExecuter.binToDec(opCode);
		contUnit(opCodeInt);
		if (opCodeInt == 0)
		// R Type instruction
		{
			
			System.out.println("Executing R type.....");
			ProgramExecuter.registerFile.setWrite(true);
			decodeR(s);
			ProgramExecuter.pc=Fetch.progCount(ProgramExecuter.pc);
		}
		if (opCodeInt == 35) {
			// LW instruction
			System.out.println("Executing LW .....");
			ProgramExecuter.registerFile.setWrite(true);
			decodeLWSW(s);
			ProgramExecuter.pc=Fetch.progCount(ProgramExecuter.pc);
		}
		if (opCodeInt == 43) {
			// SW instruction
			System.out.println("Executing SW.....");
			ProgramExecuter.registerFile.setWrite(false);
			decodeLWSW(s);
			ProgramExecuter.pc=Fetch.progCount(ProgramExecuter.pc);
		}
		if (opCodeInt == 4)
		// BEQ instruction
		{
			System.out.println("Executing BEQ.....");
			ProgramExecuter.registerFile.setWrite(false);
			decodeBEQ(s);

		}
		Execute.execute(opCode, ALUSrc,funct, readData1, readData2, signExtend, newPC);
		
	}

	private static void contUnit(int opCodeInt) {
		if (opCodeInt == 0)
			// R Type instruction
			{
			  regDst='1';
			  ALUSrc='0';
			 regWrite='1';
			  memRead='0';
			 memWrite='0';
			  branch='0';
			  memToReg='0';
			  PCSrc='0';
			  ALUOp="10";

			}
			if (opCodeInt == 35) {
				// LW instruction
				  regDst='0';
				  ALUSrc='1';
				 regWrite='1';
				  memRead='1';
				 memWrite='0';
				  branch='0';
				  memToReg='1';
				  PCSrc='0';
				  ALUOp="00";

			}
			if (opCodeInt == 43) {
				// SW instruction
				  regDst='X';
				  ALUSrc='1';
				 regWrite='0';
				  memRead='0';
				 memWrite='1';
				  branch='0';
				  memToReg='X';
				  PCSrc='0';
				  ALUOp="00";

			}
			if (opCodeInt == 4)
			// BEQ instruction
			{  regDst='X';
			  ALUSrc='0';
			 regWrite='0';
			  memRead='0';
			 memWrite='0';
			  branch='1';
			  memToReg='X';
			  PCSrc='1';
			  ALUOp="01";

			}
			System.out.println("PCSrc: "+PCSrc);
			System.out.println("RegDst: "+regDst);
			System.out.println("ALUSrc: "+ALUSrc);
			System.out.println("RegWrite: "+regWrite);
			System.out.println("MemRead: "+memRead);
			System.out.println("MemWrite: "+memWrite);
			System.out.println("Branch: "+branch);
			System.out.println("MemToReg: "+memToReg);
			System.out.println("ALUOp: "+ALUOp);
			
			
	}
	
	private static void decodeBEQ(String s) {

		int rs = ProgramExecuter.binToDec(s.substring(6, 11));
		int rt = ProgramExecuter.binToDec(s.substring(11, 16));
		String[] req = ProgramExecuter.registerFile.readTwo(rs, rt);
		String imm = s.substring(16);
		if (req[0] == req[1]) {
			System.out.println("Branching.....");
			//ProgramExecuter.pc = ProgramExecuter.binToDec(signExtend(imm));
			ProgramExecuter.pc=Fetch.progCount(ProgramExecuter.pc);
		} else {
			System.out.println("Not Branching.....");
			ProgramExecuter.pc=Fetch.progCount(ProgramExecuter.pc);
		}
	}

	public static String signExtend(String s) {
		String sign = s.substring(0, 1);

		while (s.length() != 32)
			s = sign + s;
		return s;
	}

	private static void decodeLWSW(String s) {
		int rs = ProgramExecuter.binToDec(s.substring(6, 11));
		int rt = ProgramExecuter.binToDec(s.substring(11, 16));
		int imm = ProgramExecuter.binToDec(s.substring(16));
		signExtend=signExtend(s.substring(16));
		Decode.rd=s.substring(11, 16);
		ProgramExecuter.registerFile.readTwo(rs, rt);
	}

	private static void decodeR(String s) {
		funct=s.substring(26);
		int functCode = ProgramExecuter.binToDec(s.substring(26));
		System.out.println("funct code:" + funct);
		int rs = ProgramExecuter.binToDec(s.substring(6, 11));
		int rt = ProgramExecuter.binToDec(s.substring(11, 16));
		int rd = ProgramExecuter.binToDec(s.substring(16, 21));
		String[] req = ProgramExecuter.registerFile.readTwo(rs, rt);
		String res = "";
		if (functCode == 32) {
			System.out.println("Executing ADD.....");
			int tmp = ProgramExecuter.binToDec(req[0]) - ProgramExecuter.binToDec(req[1]);
			res = ProgramExecuter.decToBin(tmp);

		}
		if (functCode == 34) {
			System.out.println("Executing SUB.....");
			int tmp = ProgramExecuter.binToDec(req[0]) + ProgramExecuter.binToDec(req[1]);
			res = ProgramExecuter.decToBin(tmp);
		}
		if (functCode == 36) {
			System.out.println("Executing AND.....");
			int tmp = ProgramExecuter.binToDec(req[0]) + ProgramExecuter.binToDec(req[1]);
			res = ProgramExecuter.decToBin(tmp);
		}
		if (functCode == 37) {
			System.out.println("Executing OR.....");
			int tmp = ProgramExecuter.binToDec(req[0]) + ProgramExecuter.binToDec(req[1]);
			res = ProgramExecuter.decToBin(tmp);
		}
		if (functCode == 42) {
			System.out.println("Executing SLT.....");
			int tmp = ProgramExecuter.binToDec(req[0]) + ProgramExecuter.binToDec(req[1]);
			res = ProgramExecuter.decToBin(tmp);
		}
		ProgramExecuter.registerFile.write(rd, res);

	}

}
