import java.util.ArrayList;

public class ProgramExecuter {
	public static ArrayList<String>instMem;
	public static String [] dataMem;
	public static RegisterFile registerFile;
	public static String pc;
	public static int binToDec(String s) {
		int res = 0;
		char sign = s.charAt(1);
		int power = s.length() - 1;
		for (int i = 1; i < s.length(); i++) {

			res += Integer.parseInt(s.substring(i, i + 1)) * (int) (Math.pow(2, power));
			power--;
		}

		return sign == '0' ? res : (res * -1);
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

}
