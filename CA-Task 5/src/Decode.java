
public class Decode {

	/*public static String[] intDecode(String inst)
	{
		
	}*/
	public static void decode(String s) {
		String opCode = s.substring(0, 6);
		int opCodeInt = ProgramExecuter.binToDec(opCode);
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
		if (opCodeInt == 2)
		// J instruction
		{
			System.out.println("Executing J.....");
			ProgramExecuter.registerFile.setWrite(false);
			decodeJ(s);

		}
	}

	private static void decodeJ(String s) {

		String imm = s.substring(6);
		ProgramExecuter.pc=Fetch.progCount(ProgramExecuter.pc);
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

	public String signExtend(String s) {
		String sign = s.substring(0, 1);

		while (s.length() != 32)
			s = sign + s;
		return s;
	}

	private static void decodeLWSW(String s) {
		int rs = ProgramExecuter.binToDec(s.substring(6, 11));
		int rt = ProgramExecuter.binToDec(s.substring(11, 16));
		int imm = ProgramExecuter.binToDec(s.substring(16));
		ProgramExecuter.registerFile.readTwo(rs, rt);
	}

	private static void decodeR(String s) {
		int funct = ProgramExecuter.binToDec(s.substring(26));
		System.out.println("funct code:" + funct);
		int rs = ProgramExecuter.binToDec(s.substring(6, 11));
		int rt = ProgramExecuter.binToDec(s.substring(11, 16));
		int rd = ProgramExecuter.binToDec(s.substring(16, 21));
		String[] req = ProgramExecuter.registerFile.readTwo(rs, rt);
		String res = "";
		if (funct == 34) {
			System.out.println("Executing SUB.....");
			int tmp = ProgramExecuter.binToDec(req[0]) - ProgramExecuter.binToDec(req[1]);
			res = ProgramExecuter.decToBin(tmp);

		}
		if (funct == 32) {
			System.out.println("Executing ADD.....");
			int tmp = ProgramExecuter.binToDec(req[0]) + ProgramExecuter.binToDec(req[1]);
			res = ProgramExecuter.decToBin(tmp);
		}
		ProgramExecuter.registerFile.write(rd, res);

	}

}
