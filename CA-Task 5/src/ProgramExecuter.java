

public class ProgramExecuter {
	public static String[]  instMem;
	public static String[] dataMem;
	public static RegisterFile registerFile;
	public static String pc;

	public static int binToDec(String s) {
		int res = 0;
		int power = s.length() - 1;
		for (int i = 0; i < s.length(); i++) {
			//System.out.println(res);
			res += Integer.parseInt(s.substring(i, i + 1)) * (int) (Math.pow(2, power));
			
			power--;
		}

		return res;
	}

	public static String decToBin(int n) {
		String sign = "";
		if (n < 0) {
			sign = "1";
			n = n * -1;
		} else
			sign = "0";

		String res = "";
		while (n != 0) {
			int d = n % 2;
			n = n / 2;
			res = d + res;

		}

		while (res.length() != 32)
			res = "0" + res;
		if (sign == "1") {
			String temp = "";
			int c = 0;
			int i;
			for (i = 31; i >= 0; i--) {
				temp = res.charAt(31) + "" + temp;
				if (res.charAt(i) == '1')
					c++;

				if (c == 1) {
					i--;
					break;
				}
			}
			if (c == 1) {
				while (i >= 0) {
					if (res.charAt(i) == '1')
						temp = "0" + temp;
					else
						temp = "1" + temp;
					i--;
				}
			}
			res = temp;
		}

		return res;
	}

	public static void loadProgram() {
		// add $1,$2,$3
		instMem[0] = "00000000010000110000100000100000";
		// sub $4,$5,$6
		instMem[1] = "00000000101001100010000000100010";
		// lw $7,(0)$8
		instMem[2] = "10001100111010000000000000000000";
		// sw $9,(0)$10
		instMem[3] = "10101101001010100000000000000000";
		/*// beq $5,$4,16
		instMem[4] = "00010000100001010000000000000110";
		// and $11,$12,$13
		instMem[5] = "00000001100011010101100000100100";
		// or $14,$15,$16
		instMem[6] = "00000001111100000111000000100101";
		// slt $17,$18,$19
		instMem[7] = "00000010010100111000100000101010";*/
		pc=decToBin(0);
	}
	public static void startExecution()
	{
		int i=binToDec(pc);
		while (i<instMem.length)
		{
			Fetch.instFetch(pc);
			i=binToDec(pc);

		}
	}
	public static void main(String[] args) {
		instMem=new String [4];
		dataMem=new String [2];
		dataMem[0]="00000000000000000000000000000010";
		dataMem[1]="00000000000000000000000000000001";
		registerFile=new RegisterFile();
		loadProgram();
		startExecution();
	}

}
