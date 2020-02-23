import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		while(T-->0) {
			char[] data=br.readLine().toCharArray();
			int val=(data[0]-'0')+(data[2]-'0');
			sb.append(val+"\n");
		}
		System.out.println(sb.toString());
	}
}
