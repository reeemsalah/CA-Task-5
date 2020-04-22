
public class Execute {
	public static String[] execute(String ALUOp, String ALUSrc, String readData1, String readData2, String signExtend,
			String newPC) {
		String [] res= new String [5];
		res[0]=ALUEvaluator(ALUOp, readData1, readData2);
		return res;

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

}
