
public class Execute {
	public static String ALUres;
	public static char zeroFlag;
	public static String branchAddressRes;
	public static String readData22;
	public static String newPc;

	public static void execute(String ALUOp, char ALUSrc, String funct, String readData1, String readData2,
			String signExtend, String newPC) {
		String op = "";
		if (ALUOp.equals("10")) {

			switch (funct) {
			case "100000":
				op = "0010";
				break;
			case "100010":
				op = "0110";
				break;
			case "100100":
				op = "0000";
				break;
			case "100101":
				op = "0001";
				break;
			case "101010":
				op = "0111";
				break;
			}

		}
		if (ALUOp.equals("00")) {
			op = "0010";
		}

		if (ALUOp.equals("10")) {
			op = "0110";
		}
		ALUres = ALUEvaluator(op, readData1, readData2);
		setZeroFlag();
		if (zeroFlag == '1' && ALUOp.equals("01")) {
			branchAddressRes = signExtend;
			Decode.PCSrc = '1';
		}

		readData22 = readData2;
		MemoryAccess.memAccess(ALUres, readData2, signExtend, zeroFlag, branchAddressRes, Decode.memWrite,
				Decode.memRead, Decode.branch);

	}

	/**
	 * 
	 * @param Op  the operation code AND 0000 Output = A & B (Bitwise AND); OR 0001
	 *            Output = A | B (Bitwise OR); add 0010 Output = A + B; sub 0110
	 *            Output = A - B; slt 0111 Output = (A <B)? 1 : 0; NOR 1100 Output =
	 *            (A | B) (Bitwise NOR);
	 * 
	 * @param op1 String as operand 1
	 * @param op2 String as operand 2
	 */
	public static String ALUEvaluator(String op, String op1, String op2) {
		if (op1.length() != 32 && op2.length() != 32) {
			return null;
		} else {
			int n1 = ProgramExecuter.binToDec(op1);
			int n2 = ProgramExecuter.binToDec(op2);
			switch (op) {

			case "0001":
				return or(n1, n2);
			case "0010":
				return add(n1, n2);

			case "0110":
				return sub(n1, n2);
			case "0111":
				return slt(n1, n2);
			case "1100":
				return nor(n1, n2);
			default:
				return null;
			}
		}
	}

	private static String nor(int op1, int op2) {
		int res1 = ~(op1 | op2);
		String res2 = ProgramExecuter.decToBin(res1);
		return res2;

	}

	private static String slt(int op1, int op2) {
		int res1 = (op1 < op2) ? 1 : 0;
		String res2 = ProgramExecuter.decToBin(res1);
		return res2;

	}

	private static String sub(int op1, int op2) {
		int res1 = op1 - op2;
		String res2 = ProgramExecuter.decToBin(res1);
		return res2;

	}

	private static String add(int op1, int op2) {
		int res1 = op1 + op2;
		String res2 = ProgramExecuter.decToBin(res1);
		return res2;

	}

	private static String or(int op1, int op2) {
		int res1 = (op1 | op2);
		String res2 = ProgramExecuter.decToBin(res1);
		return res2;

	}

	private static String and(int op1, int op2) {
		int res1 = (op1 & op2);
		String res2 = ProgramExecuter.decToBin(res1);
		return res2;

	}

	public static void setZeroFlag() {
		zeroFlag = ALUres.equals("0000000000000000000000000000000") ? '1' : '0';
	}

}
