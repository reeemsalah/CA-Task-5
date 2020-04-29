
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
		
		public static String rs;
		public static String rt;
		public static String rd;
		
		public static String rsNo;
		public static String rtNo;


		public static String funct;
		public static String signExtend;
		public static String newPC;
	public static void instDecode(String inst, String newPC)
	{
		System.out.println("new PC: "+newPC);
		Decode.newPC=newPC;
		decode(inst);
		
	}
	/**
	 * 
	 * @param s the instruction to be decoded according to the type
	
	 */
	private static void decode(String s) {
		String opCode = s.substring(0, 6);
		int opCodeInt = ProgramExecuter.binToDec(opCode);
		System.out.println("instOPcode: "+opCodeInt);
		contUnit(opCodeInt);
		if (opCodeInt == 0)
		// R Type instruction
		{
			
			System.out.println("Executing R type.....");
			ProgramExecuter.registerFile.setWrite(true);
			decodeR(s);
		}
		if (opCodeInt == 35) {
			// LW instruction
			System.out.println("Executing LW .....");
			ProgramExecuter.registerFile.setWrite(true);
			decodeI(s);
		}
		if (opCodeInt == 43) {
			// SW instruction
			System.out.println("Executing SW.....");
			ProgramExecuter.registerFile.setWrite(false);
			decodeI(s);
		}
		if (opCodeInt == 4)
		// BEQ instruction
		{
			System.out.println("Executing BEQ.....");
			ProgramExecuter.registerFile.setWrite(false);
			decodeI(s);

		}

		Execute.execute(ALUOp, ALUSrc,funct, rs, rt, signExtend, newPC);
		
	}
	/**
	 * 
	 * @param opCodeInt sets the control signals according to the opcode
	 */
	private static void contUnit(int opCodeInt) {
		System.out.println("Assigning contSignals");
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
	

	/**
	 * 
	 * @param s a 16-bit binary representation of a number
	 * @return 32-bit representation of the number with extended sign
	 */
	public static String signExtend(String s) {
		String sign = s.substring(0, 1);

		while (s.length() != 32)
			s = sign + s;
		return s;
	}
	/**
	 * 
	 * @param s decodes an I-type instruction following the format:
	 * opcode	rs     	rt	    IMM
	   6 bits	5 bits	5 bits	16 bits
	 */

	private static void decodeI(String s) {
		int n1=ProgramExecuter.binToDec(s.substring(6, 11));
		int n2=ProgramExecuter.binToDec(s.substring(11, 16));
		rtNo=s.substring(6, 11);
		rsNo=s.substring(11, 16);
		rs= ProgramExecuter.registerFile.readOne(n1);
		rt = ProgramExecuter.registerFile.readOne(n2);
		signExtend=signExtend(s.substring(16));

	}
	/**
	 * 
	 * @param s decodes an R-type instruction following the format:
	 * opcode	rs	    rt	    rd	    shift (shamt)	funct
	   6 bits	5 bits	5 bits	5 bits	5 bits	        6 bits

	 */

	private static void decodeR(String s) {
		funct=s.substring(26);
		System.out.println("funct code:" + funct);
		int n1=ProgramExecuter.binToDec(s.substring(6, 11));
		int n2=ProgramExecuter.binToDec(s.substring(11, 16));
		rs= ProgramExecuter.registerFile.readOne(n1);
		rt = ProgramExecuter.registerFile.readOne(n2);
		rd  = (s.substring(16, 21));
		rsNo=s.substring(6, 11);
		rtNo=s.substring(11, 16);
	/*	if (functCode == 32) {
			System.out.println("Executing ADD.....");
		}
		if (functCode == 34) {
			System.out.println("Executing SUB.....");
		}
		if (functCode == 36) {
			System.out.println("Executing AND.....");
		}
		if (functCode == 37) {
			System.out.println("Executing OR.....");
		}
		if (functCode == 42) {
			System.out.println("Executing SLT.....");
		}*/

	}

}
