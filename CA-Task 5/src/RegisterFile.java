

public class RegisterFile {

	private final String zero="0000000000000000000000000000000";
	private String [] registers;
	public boolean write;
	public RegisterFile()
	{
	
		registers=new String[31];
		registers[0]="00000000000000000000000000000010";
		registers[1]="00000000000000000000000000000011";
		registers[2]="00000000000000000000000000000110";
		registers[3]="00000000000000000000000000000011";
		registers[4]="00000000000000000000000000000100";
		registers[5]="00000000000000000000000000000001";
		registers[6]="00000000000000000000000000000000";
		registers[7]="00000000000000000000000000000001";
		registers[8]="00000000000000000000000000000101";
		registers[9]="00000000000000000000000000000000";
		for(int i=10;i<registers.length;i++)
			registers[i]="00000000000000000000000000000000";
	}
	public String [] readTwo(int rs,int rt)
	{
		
		
		String [] res= {this.readOne(rs),this.readOne(rt)};
		return res;
	}
	public String readOne(int rs)
	{
		if(rs==0)
		{
			System.out.println("rs:"+zero);
			return zero;
			
		}
		else
		{
		System.out.println("rs:"+registers[rs-1]);
		return registers[rs-1];
		}
	}
	public void write(int rd, String s)
	{
		if(write) {
			if(rd==0)
				System.out.println("Register zero is Read only...");
			else
			{
		registers[rd-1]=s;
		System.out.println("rd:"+s);
			}
		}
		else
			System.out.println("Writing is register file is disabled....");
	}
	public void setWrite(boolean write) {
		this.write = write;
	}
}
